package com.zacseed.alertapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zacseed.alertapp.utils.PreferenceUtils;

public class RegisterPinActivity extends AppCompatActivity {
    private EditText editTextPin1;
    private EditText editTextPin2;
    private Button buttonRegPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pin);
        getSupportActionBar().hide();

        editTextPin1 = findViewById(R.id.editTextPin1);
        editTextPin2 = findViewById(R.id.editTextPin2);
        buttonRegPin = findViewById(R.id.btnRegisterPin);

        buttonRegPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pin1 = 0;
                int pin2 = 0;
                if (!editTextPin1.getText().toString().equals("") && !editTextPin2.getText().toString().equals("")){
                    pin1 = Integer.parseInt(String.valueOf(editTextPin1.getText()));
                    pin2 = Integer.parseInt(String.valueOf(editTextPin2.getText()));
                }
                if (String.valueOf(editTextPin1.getText()).length() < 4){
                    editTextPin1.setError(getString(R.string.pin_length_less_than_6));
                }else if (String.valueOf(editTextPin2.getText()).length() < 4){
                    editTextPin2.setError(getString(R.string.pin_length_less_than_6));
                }else if (pin1 != pin2){
                    editTextPin2.setError(getString(R.string.pins_not_matching));
                }else {
                    PreferenceUtils.setPin(RegisterPinActivity.this, pin1);
                    startActivity(new Intent(RegisterPinActivity.this, SecurityPinActivity.class));
                    finish();
                }
            }
        });
    }
}
