package com.zacseed.alertapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.zacseed.alertapp.database.DbHelper;
import com.zacseed.alertapp.database.RecycleAdapter;
import com.zacseed.alertapp.sms.SingleOccurrence;
import com.zacseed.alertapp.utils.PreferenceUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class MessageLogActivity extends AppCompatActivity {
    DbHelper dbHelper;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<LogModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_log);
        getSupportActionBar().setTitle("Message Log");
        dbHelper = new DbHelper(this);
        recyclerView = findViewById(R.id.recycleview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        Cursor cursor =dbHelper.getAllMessageLog();

            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                do {
                    LogModel logModel = new LogModel(cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5));
                    logModel.setPhoneNumber(PreferenceUtils.getCurrentPhoneNumber(MessageLogActivity.this));
                    arrayList.add(logModel);
                } while (cursor.moveToNext());
                adapter = new RecycleAdapter(arrayList);
                recyclerView.setAdapter(adapter);
            } else{
            Toast.makeText(this,"No data to preview!!",Toast.LENGTH_SHORT).show();
        }


    }
}
