package com.zacseed.alertapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zacseed.alertapp.SecurityActivity;
import com.zacseed.alertapp.utils.PreferenceUtils;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "User_Log";
    private static final String Alart_message = "Message_Data";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ Alart_message + "(Id INTEGER PRIMARY KEY AUTOINCREMENT, Type,Location_id TEXT,Alarm_id TEXT, Time_stamp TEXT, Received_time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Alart_message);
        onCreate(db);

    }

    public boolean insertMessageData(String type, String Location_id, String Alarm_id,String Time_stamp,String Received_time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Type", type);
        contentValues.put("Location_id",Location_id);
        contentValues.put("Alarm_id",Alarm_id);
        contentValues.put("Time_stamp",Time_stamp);
        contentValues.put("Received_time",Received_time);
        long result = db.insert(Alart_message,null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllMessageLog(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM Message_Data ORDER BY Id DESC",null);
        return res;
    }

}