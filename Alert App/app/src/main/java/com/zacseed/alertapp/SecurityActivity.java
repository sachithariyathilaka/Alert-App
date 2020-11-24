package com.zacseed.alertapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.zacseed.alertapp.utils.PreferenceUtils;

import java.security.Security;

public class SecurityActivity extends AppCompatActivity {
    private ImageView disarm, stay, away;
    private TextView disarmed;
    private ImageView image, menuBtn;
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        getSupportActionBar().hide();
        disarm = findViewById(R.id.disarm_image);
        disarmed = findViewById(R.id.disarmed);
        stay = findViewById(R.id.stay_image);
        away = findViewById(R.id.arm_image);
        image = findViewById(R.id.image);
        menuBtn = findViewById(R.id.menuBtn);

        if ("".equals(PreferenceUtils.getSmsSendNumber(SecurityActivity.this))){
            PreferenceUtils.showToast(SecurityActivity.this, getString(R.string.sms_send_number_not_defined));
            Intent intent = new Intent(SecurityActivity.this, SmsSendNumberActivity.class);
            intent.putExtra("NUMBER",1);
            startActivity(intent);
            finish();
        }
        disarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disarmed.setText("Disarmed");
                disarmed.setTextColor(Color.GREEN);
                image.setImageResource(R.drawable.ic_lock_open_black_24dp);
//                if(checkPermission(Manifest.permission.SEND_SMS)){
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(PreferenceUtils.getSmsSendNumber(SecurityActivity.this),null,"alert",null,null);
//                    Toast.makeText(v.getContext(),"Message Sent",Toast.LENGTH_LONG).show();
//                }
            }
        });

        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disarmed.setText("Armed(Stay)");
                disarmed.setTextColor(Color.HSVToColor(new float[]{24,100,83}));
                image.setImageResource(R.drawable.ic_lock_outline_black_24dp);
            }
        });

        away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disarmed.setText("Armed(Away)");
                disarmed.setTextColor(Color.HSVToColor(new float[]{360,87,84}));
                image.setImageResource(R.drawable.ic_lock);
            }
        });

        if (checkPermission(Manifest.permission.SEND_SMS)) {


        } else{
            ActivityCompat.requestPermissions(SecurityActivity.this, new String[] { Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu(menuBtn);
            }
        });

        if (!PreferenceUtils.getAcknowledgedStatus(SecurityActivity.this)){
            startActivity(new Intent(SecurityActivity.this, AlarmActivity.class));
        }
    }

    private void showPopUpMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater()
                .inflate(R.menu.pop_up_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.top_nav_home:
                        break;
                    case R.id.top_nav_change_pin:
                        startActivity(new Intent(SecurityActivity.this, RegisterPinActivity.class));
                        break;
                    case R.id.top_nav_pain_change_sms_number:
                        startActivity(new Intent(SecurityActivity.this, PhoneNumbersActivity.class));
                        break;
                    case R.id.top_nav_message_log:
                        startActivity(new Intent(SecurityActivity.this,MessageLogActivity.class));
                        break;
                    case R.id.top_nav_about:
                        startActivity(new Intent(SecurityActivity.this, AboutActivity.class));
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(SecurityActivity.this,permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
}
