package com.zacseed.alertapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zacseed.alertapp.sms.MultipleOccurance;
import com.zacseed.alertapp.sms.SingleOccurrence;
import com.zacseed.alertapp.utils.AlarmSoundSingleton;
import com.zacseed.alertapp.utils.PreferenceUtils;
import com.zacseed.alertapp.utils.SmsUtils;

public class AlarmActivity extends AppCompatActivity {
    private TextView headerText;
    private TextView location;
    private TextView alarmIndex;
    private TextView timeStamp;
    private TextView firstGenTime;
    private TextView lastGenTime;
    private TextView firstClearedTime;
    private TextView lastClearedTime;
    private Button btnAcknowledge;
    private ImageView imageView;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null){
            if ("true".equals(extras.getString("EXIT"))){
                finish();
            }
        }

        headerText = findViewById(R.id.header);
        location = findViewById(R.id.textViewLocation);
        alarmIndex = findViewById(R.id.textViewAlarmIndex);
        timeStamp = findViewById(R.id.textViewTimestamp);
        firstGenTime = findViewById(R.id.textViewFirstGenTime);
        lastGenTime = findViewById(R.id.textViewLastGenTime);
        firstClearedTime = findViewById(R.id.textViewFirstClearTime);
        lastClearedTime = findViewById(R.id.textViewLastClearedTime);
        btnAcknowledge = findViewById(R.id.buttonAcknowledge);
        imageView = findViewById(R.id.imageView);

        SingleOccurrence singleOccurrence = SmsUtils.getSingleOccuranceFromString(PreferenceUtils.getCurrentSmsString(AlarmActivity.this));
        MultipleOccurance multipleOccurance = SmsUtils.getMultipleOccurrenceFromString(PreferenceUtils.getCurrentSmsString(AlarmActivity.this));
        final String acknowledgementMessage;


        AlarmSoundSingleton alarmSoundSingleton = AlarmSoundSingleton.getInstance();

        mediaPlayer = MediaPlayer.create(AlarmActivity.this, R.raw.alarm_sound_wav);
        mediaPlayer.setLooping(true);
        alarmSoundSingleton.setMediaPlayer(mediaPlayer);

        if (singleOccurrence != null){
            location.setText(singleOccurrence.getLocation());
            alarmIndex.setText(singleOccurrence.getAlarmId());
            timeStamp.setText(singleOccurrence.getTimestamp());
            firstGenTime.setVisibility(View.INVISIBLE);
            lastGenTime.setVisibility(View.INVISIBLE);
            firstClearedTime.setVisibility(View.INVISIBLE);
            lastClearedTime.setVisibility(View.INVISIBLE);
            findViewById(R.id.firstGenTime).setVisibility(View.INVISIBLE);
            findViewById(R.id.lastGenTime).setVisibility(View.INVISIBLE);
            findViewById(R.id.firstClearedTime).setVisibility(View.INVISIBLE);
            findViewById(R.id.lastClearedTime).setVisibility(View.INVISIBLE);

            if (Integer.parseInt(singleOccurrence.getAlarmType()) < 8){
                headerText.setText("Warning");
                imageView.setImageResource(R.drawable.alarm_card_bg_amber);
                try {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                alarmSoundSingleton.getMediaPlayer().start();
            }

            acknowledgementMessage = SmsUtils.getAcknowledgmentReplyString(singleOccurrence.getLocation(), singleOccurrence.getAlarmIndex(), singleOccurrence.getAlarmRef());
        }else {
            location.setText(multipleOccurance.getLocationId());
            alarmIndex.setText(multipleOccurance.getAlarmId());
            timeStamp.setVisibility(View.INVISIBLE);
            firstGenTime.setText(multipleOccurance.getFirstGeneratedTimestamp());
            lastGenTime.setText(multipleOccurance.getLastGeneratedTimestamp());
            firstClearedTime.setText(multipleOccurance.getFirstClearedTimestamp());
            lastClearedTime.setText(multipleOccurance.getLastClearedTimestamp());

            acknowledgementMessage = SmsUtils.getAcknowledgmentReplyString(multipleOccurance.getLocationId(), multipleOccurance.getAlarmIndex(), multipleOccurance.getAlarmRef());

            if (Integer.parseInt(multipleOccurance.getAlarmType()) < 8){
                headerText.setText("Warning");
                imageView.setImageResource(R.drawable.alarm_card_bg_amber);
                try {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                alarmSoundSingleton.getMediaPlayer().start();
            }
        }



        btnAcknowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mediaPlayer.stop();
//                SmsManager smsManager = SmsManager.getDefault();
//                smsManager.sendTextMessage(PreferenceUtils.getSmsSendNumber(AlarmActivity.this),null,acknowledgementMessage,null,null);
//                Toast.makeText(v.getContext(),"Message Sent",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AlarmActivity.this, PinVerifyActivity.class));
                finish();
            }
        });

//        new AlertDialog.Builder(AlarmActivity.this).setTitle("Security breach")
//                .setMessage("A SMS received with alert")
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Send acknowledgment SMS
//                    }
//                })
//                .setNegativeButton(android.R.string.no, null)
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .show();
    }

}
