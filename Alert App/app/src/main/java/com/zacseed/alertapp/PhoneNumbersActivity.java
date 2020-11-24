package com.zacseed.alertapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zacseed.alertapp.utils.PreferenceUtils;

public class PhoneNumbersActivity extends AppCompatActivity {
    private TextView number1;
    private TextView number2;
    private TextView number3;
    private TextView number4;
    private TextView number5;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button buttonHome;

    private final String NUMBER = "NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_numbers);
        getSupportActionBar().hide();

        number1 = findViewById(R.id.textViewNumber1);
        number2 = findViewById(R.id.textViewNumber2);
        number3 = findViewById(R.id.textViewNumber3);
        number4 = findViewById(R.id.textViewNumber4);
        number5 = findViewById(R.id.textViewNumber5);

        button1 = findViewById(R.id.buttonNumber1);
        button2 = findViewById(R.id.buttonNumber2);
        button3 = findViewById(R.id.buttonNumber3);
        button4 = findViewById(R.id.buttonNumber4);
        button5 = findViewById(R.id.buttonNumber5);
        buttonHome = findViewById(R.id.buttonHome);

        number1.setText(PreferenceUtils.getSmsSendNumber(PhoneNumbersActivity.this));
        number2.setText(PreferenceUtils.getSmsSendNumber2(PhoneNumbersActivity.this));
        number3.setText(PreferenceUtils.getSmsSendNumber3(PhoneNumbersActivity.this));
        number4.setText(PreferenceUtils.getSmsSendNumber4(PhoneNumbersActivity.this));
        number5.setText(PreferenceUtils.getSmsSendNumber5(PhoneNumbersActivity.this));

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PhoneNumbersActivity.this, SecurityActivity.class));
                finish();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneNumbersActivity.this, SmsSendNumberActivity.class);
                intent.putExtra(NUMBER, 1);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneNumbersActivity.this, SmsSendNumberActivity.class);
                intent.putExtra(NUMBER, 2);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneNumbersActivity.this, SmsSendNumberActivity.class);
                intent.putExtra(NUMBER, 3);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneNumbersActivity.this, SmsSendNumberActivity.class);
                intent.putExtra(NUMBER, 4);
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneNumbersActivity.this, SmsSendNumberActivity.class);
                intent.putExtra(NUMBER, 5);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        number1.setText(PreferenceUtils.getSmsSendNumber(PhoneNumbersActivity.this));
        number2.setText(PreferenceUtils.getSmsSendNumber2(PhoneNumbersActivity.this));
        number3.setText(PreferenceUtils.getSmsSendNumber3(PhoneNumbersActivity.this));
        number4.setText(PreferenceUtils.getSmsSendNumber4(PhoneNumbersActivity.this));
        number5.setText(PreferenceUtils.getSmsSendNumber5(PhoneNumbersActivity.this));
    }
}
