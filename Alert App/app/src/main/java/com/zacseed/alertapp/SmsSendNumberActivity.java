package com.zacseed.alertapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zacseed.alertapp.utils.PreferenceUtils;

import java.security.Security;

public class SmsSendNumberActivity extends AppCompatActivity {
    private EditText editTextNumber;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_send_number);
        getSupportActionBar().hide();

        editTextNumber = findViewById(R.id.editTextNumber);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(editTextNumber.getText()).length() > 3){
                    Bundle extras = getIntent().getExtras();
                    if (!extras.isEmpty() || extras.containsKey("NUMBER")){
                        int number = extras.getInt("NUMBER");
                        if (number == 1){
                            PreferenceUtils.setSmsSendNumber(SmsSendNumberActivity.this, String.valueOf(editTextNumber.getText()));
                        }else if (number == 2){
                            PreferenceUtils.setSmsSendNumber2(SmsSendNumberActivity.this, String.valueOf(editTextNumber.getText()));
                        }else if (number == 3){
                            PreferenceUtils.setSmsSendNumber3(SmsSendNumberActivity.this, String.valueOf(editTextNumber.getText()));
                        }else if (number == 4){
                            PreferenceUtils.setSmsSendNumber4(SmsSendNumberActivity.this, String.valueOf(editTextNumber.getText()));
                        }else if (number == 5){
                            PreferenceUtils.setSmsSendNumber5(SmsSendNumberActivity.this, String.valueOf(editTextNumber.getText()));
                        }
                        startService(new Intent(SmsSendNumberActivity.this, AlarmService.class));
                        startActivity(new Intent(SmsSendNumberActivity.this, PhoneNumbersActivity.class));
                        finish();
                    }else {
                        PreferenceUtils.setSmsSendNumber(SmsSendNumberActivity.this, String.valueOf(editTextNumber.getText()));
                        startService(new Intent(SmsSendNumberActivity.this, AlarmService.class));
                        startActivity(new Intent(SmsSendNumberActivity.this, SecurityActivity.class));
                        finish();
                    }
                }else {
                    editTextNumber.setError(getString(R.string.number_invalid));
                }
            }
        });
    }
}
