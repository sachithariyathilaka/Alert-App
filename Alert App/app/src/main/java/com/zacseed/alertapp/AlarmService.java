package com.zacseed.alertapp;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.zacseed.alertapp.database.DbHelper;
import com.zacseed.alertapp.sms.SmsListener;
import com.zacseed.alertapp.sms.SmsReceiver;
import com.zacseed.alertapp.utils.PreferenceUtils;
import com.zacseed.alertapp.utils.SmsUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AlarmService extends Service {
    DbHelper dbHelper;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Toast.makeText(AlarmService.this, "Service Started", Toast.LENGTH_LONG).show();
        dbHelper = new DbHelper(this);
        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText, String sender) {
                Toast.makeText(AlarmService.this, "Message: "+messageText, Toast.LENGTH_LONG).show();
                if (SmsUtils.getSingleOccuranceFromString(messageText) != null || SmsUtils.getMultipleOccurrenceFromString(messageText) != null){
                    SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss", Locale.getDefault());
                    String currentDateandTime = sdf.format(new Date());
                    if(SmsUtils.getSingleOccuranceFromString(messageText) != null){
                        boolean result = dbHelper.insertMessageData(SmsUtils.SMS_TYPE_ALERT, SmsUtils.getSingleOccuranceFromString(messageText).getLocation(),SmsUtils.getSingleOccuranceFromString(messageText).getAlarmId(),SmsUtils.getSingleOccuranceFromString(messageText).getTimestamp(),currentDateandTime);
                        PreferenceUtils.showToast(AlarmService.this, String.valueOf(result));
                    }
                    if(SmsUtils.getMultipleOccurrenceFromString(messageText) != null){
                        dbHelper.insertMessageData(SmsUtils.SMS_TYPE_ALERT, SmsUtils.getMultipleOccurrenceFromString(messageText).getLocationId(),SmsUtils.getMultipleOccurrenceFromString(messageText).getAlarmId(),SmsUtils.getMultipleOccurrenceFromString(messageText).getFirstGeneratedTimestamp(),currentDateandTime);
                    }

                    // Set the sender
                    PreferenceUtils.setCurrentPhoneNumber(AlarmService.this, sender);

                    PreferenceUtils.setCurrentSmsString(AlarmService.this, messageText);
                    Intent intent1 = new Intent(AlarmService.this, AlarmActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    PreferenceUtils.setAcknowledgedStatus(AlarmService.this, false);
                    startActivity(intent1);
                }
//                if (messageText.toLowerCase().contains("alert")){
//                    // TODO: start alarm activity
//                    Intent intent1 = new Intent(AlarmService.this, AlarmActivity.class);
//                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent1);
//                }
            }
        });
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(AlarmService.this, "Service Destroyed", Toast.LENGTH_SHORT).show();
    }
}
