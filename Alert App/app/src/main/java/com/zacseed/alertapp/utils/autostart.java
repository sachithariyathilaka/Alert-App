package com.zacseed.alertapp.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.zacseed.alertapp.AlarmService;

public class autostart extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent arg1) {
        Intent intent = new Intent(context, AlarmService.class);
        Toast.makeText(context, "Message: ", Toast.LENGTH_LONG).show();
        context.startService(intent);
    }
}