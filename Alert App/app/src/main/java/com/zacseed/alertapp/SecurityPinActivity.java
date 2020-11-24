package com.zacseed.alertapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zacseed.alertapp.database.DbHelper;
import com.zacseed.alertapp.utils.PreferenceUtils;

public class SecurityPinActivity extends AppCompatActivity {

    Button btnLogin;
    TextView inputPin;

    private final int SMS_READ_PERMISSION_CODE = 123;
    private final int SMS_RECEIVE_PERMISSION_CODE = 456;
    private final int SMS_SEND_PERMISSION_CODE = 789;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_pin);
        getSupportActionBar().hide();
        inputPin = findViewById(R.id.editTextPin);
        btnLogin = findViewById(R.id.btnLogin);
        if (PreferenceUtils.getPin(SecurityPinActivity.this) == 0){
            PreferenceUtils.showToast(SecurityPinActivity.this, getString(R.string.first_time_pin));
            startActivity(new Intent(SecurityPinActivity.this, RegisterPinActivity.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputPin.getText().toString().equals("")){
                    int pin = Integer.parseInt(inputPin.getText().toString());
                }
                if (String.valueOf(inputPin.getText()).length() < 4){
                    PreferenceUtils.showToast(SecurityPinActivity.this, getString(R.string.pin_length_less_than_6));
                    inputPin.setError(getString(R.string.pin_length_less_than_6));
                }else {
                    if (Integer.parseInt(String.valueOf(inputPin.getText())) == PreferenceUtils.getPin(SecurityPinActivity.this)){
                        startService(new Intent(SecurityPinActivity.this, AlarmService.class));
                        startActivity(new Intent(SecurityPinActivity.this, SecurityActivity.class));
                        finish();
                    }else {
                        PreferenceUtils.showToast(SecurityPinActivity.this, getString(R.string.pin_invalid));
                    }
                }
            }
        });

        if (!isSmsReadPermissionGranted()){
            requestReadAndSendSmsPermission();
        }

        startService(new Intent(SecurityPinActivity.this, AlarmService.class));
    }

    public boolean isSmsReadPermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isSmsSendPermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isSmsReceivePermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Request runtime SMS permission
     */
    private void requestReadAndSendSmsPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
            // You may display a non-blocking explanation here, read more in the documentation:
            // https://developer.android.com/training/permissions/requesting.html
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, SMS_READ_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case SMS_READ_PERMISSION_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
