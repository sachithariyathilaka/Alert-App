package com.zacseed.alertapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zacseed.alertapp.database.DbHelper;
import com.zacseed.alertapp.sms.MultipleOccurance;
import com.zacseed.alertapp.sms.SingleOccurrence;
import com.zacseed.alertapp.utils.AlarmSoundSingleton;
import com.zacseed.alertapp.utils.PreferenceUtils;
import com.zacseed.alertapp.utils.SmsUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class PinVerifyActivity extends AppCompatActivity {
    private EditText editTextPin;
    private Button buttonLogin;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_verify);
        getSupportActionBar().hide();

        editTextPin = findViewById(R.id.editTextPin);
        buttonLogin = findViewById(R.id.btnLogin);
        dbHelper = new DbHelper(this);

        final SingleOccurrence singleOccurrence = SmsUtils.getSingleOccuranceFromString(PreferenceUtils.getCurrentSmsString(PinVerifyActivity.this));
        final MultipleOccurance multipleOccurance = SmsUtils.getMultipleOccurrenceFromString(PreferenceUtils.getCurrentSmsString(PinVerifyActivity.this));
        final String acknowledgementMessage;

        if (singleOccurrence != null){
            acknowledgementMessage = SmsUtils.getAcknowledgmentReplyString(singleOccurrence.getLocation(), singleOccurrence.getAlarmIndex(), singleOccurrence.getAlarmRef());
        }else {
            acknowledgementMessage = SmsUtils.getAcknowledgmentReplyString(multipleOccurance.getLocationId(), multipleOccurance.getAlarmIndex(), multipleOccurance.getAlarmRef());
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextPin.getText().toString().equals("")){
                    int pin = Integer.parseInt(String.valueOf(editTextPin.getText()));
                }
                if (String.valueOf(editTextPin.getText()).length() < 4){
                    PreferenceUtils.showToast(PinVerifyActivity.this, getString(R.string.pin_length_less_than_6));
                    editTextPin.setError(getString(R.string.pin_length_less_than_6));
                }else {
                    if (Integer.parseInt(String.valueOf(editTextPin.getText())) == PreferenceUtils.getPin(PinVerifyActivity.this)){
                        SmsManager smsManager = SmsManager.getDefault();
                        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss", Locale.getDefault());
                        String currentDateandTime = sdf.format(new Date());
                        smsManager.sendTextMessage(PreferenceUtils.getCurrentPhoneNumber(PinVerifyActivity.this),null,acknowledgementMessage,null,null);
                        if(singleOccurrence!=null){
                            boolean result = dbHelper.insertMessageData(SmsUtils.SMS_TYPE_ACKNOWLEDGEMENT, singleOccurrence.getLocation(),singleOccurrence.getAlarmId(),currentDateandTime,currentDateandTime);
//                            PreferenceUtils.showToast(PinVerifyActivity.this, String.valueOf(result));
                        }
                        if(multipleOccurance!=null){
                            boolean result = dbHelper.insertMessageData(SmsUtils.SMS_TYPE_ACKNOWLEDGEMENT, multipleOccurance.getLocationId(),multipleOccurance.getAlarmId(),currentDateandTime,currentDateandTime);
//                            PreferenceUtils.showToast(PinVerifyActivity.this, String.valueOf(result));
                        }

                        Toast.makeText(v.getContext(),"Message Sent",Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(PinVerifyActivity.this, AlarmActivity.class);
//                        intent.putExtra("EXIT", "true");
//                        startActivity(intent);
//                        finish();
                        AlarmSoundSingleton alarmSoundSingleton = AlarmSoundSingleton.getInstance();
                        alarmSoundSingleton.getMediaPlayer().stop();
                        PreferenceUtils.setAcknowledgedStatus(PinVerifyActivity.this, true);
                        finish();
                    }else {
                        PreferenceUtils.showToast(PinVerifyActivity.this, getString(R.string.pin_invalid));
                    }
                }
            }
        });
    }
}
