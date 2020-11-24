package com.zacseed.alertapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PreferenceUtils {
    public static final String KEY_PREF = "KEY_PREF";
    public static final String KEY_PIN_SAVED = "KEY_PIN_SAVED";
    public static final String KEY_SMS_SEND_NUMBER = "KEY_SMS_SEND_NUMBER";
    public static final String KEY_SMS_SEND_NUMBER_2 = "KEY_SMS_SEND_NUMBER_2";
    public static final String KEY_SMS_SEND_NUMBER_3 = "KEY_SMS_SEND_NUMBER_3";
    public static final String KEY_SMS_SEND_NUMBER_4 = "KEY_SMS_SEND_NUMBER_4";
    public static final String KEY_SMS_SEND_NUMBER_5 = "KEY_SMS_SEND_NUMBER_5";
    public static final String KEY_CURRENT_SMS_STRING = "KEY_CURRENT_SMS_STRING";
    public static final String KEY_ACKNOWLEDGE_STATUS = "KEY_ACKNOWLEDGE_STATUS";
    public static final String KEY_CURRENT_PHONE_NUMBER = "KEY_CURRENT_PHONE_NUMBER";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
    }

    public static void setPin(Context context, int pin){
        SharedPreferences sharedPref = getSharedPreferences(context);

        sharedPref.edit()
                .putInt(KEY_PIN_SAVED, pin)
                .apply();
    }

    public static int getPin(Context context) {
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getInt(KEY_PIN_SAVED, 0);
    }

    public static void setSmsSendNumber(Context context, String number){
        SharedPreferences sharedPref = getSharedPreferences(context);

        sharedPref.edit()
                .putString(KEY_SMS_SEND_NUMBER, number)
                .apply();
    }

    public static String getSmsSendNumber(Context context) {
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(KEY_SMS_SEND_NUMBER, "");
    }

    public static void setSmsSendNumber2(Context context, String number){
        SharedPreferences sharedPref = getSharedPreferences(context);

        sharedPref.edit()
                .putString(KEY_SMS_SEND_NUMBER_2, number)
                .apply();
    }

    public static String getSmsSendNumber2(Context context) {
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(KEY_SMS_SEND_NUMBER_2, "");
    }

    public static void setSmsSendNumber3(Context context, String number){
        SharedPreferences sharedPref = getSharedPreferences(context);

        sharedPref.edit()
                .putString(KEY_SMS_SEND_NUMBER_3, number)
                .apply();
    }

    public static String getSmsSendNumber3(Context context) {
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(KEY_SMS_SEND_NUMBER_3, "");
    }

    public static void setSmsSendNumber4(Context context, String number){
        SharedPreferences sharedPref = getSharedPreferences(context);

        sharedPref.edit()
                .putString(KEY_SMS_SEND_NUMBER_4, number)
                .apply();
    }

    public static String getSmsSendNumber4(Context context) {
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(KEY_SMS_SEND_NUMBER_4, "");
    }

    public static void setSmsSendNumber5(Context context, String number){
        SharedPreferences sharedPref = getSharedPreferences(context);

        sharedPref.edit()
                .putString(KEY_SMS_SEND_NUMBER_4, number)
                .apply();
    }

    public static String getSmsSendNumber5(Context context) {
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(KEY_SMS_SEND_NUMBER_5, "");
    }

    public static void setCurrentSmsString(Context context, String message){
        SharedPreferences sharedPref = getSharedPreferences(context);

        sharedPref.edit()
                .putString(KEY_CURRENT_SMS_STRING, message)
                .apply();
    }

    public static String getCurrentSmsString(Context context) {
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(KEY_CURRENT_SMS_STRING, "0");
    }

    public static void setAcknowledgedStatus(Context context, boolean status){
        SharedPreferences sharedPref = getSharedPreferences(context);

        sharedPref.edit()
                .putBoolean(KEY_ACKNOWLEDGE_STATUS, status)
                .apply();
    }

    public static boolean getAcknowledgedStatus(Context context) {
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getBoolean(KEY_ACKNOWLEDGE_STATUS, true);
    }

    public static void setCurrentPhoneNumber(Context context, String number){
        SharedPreferences sharedPref = getSharedPreferences(context);

        sharedPref.edit()
                .putString(KEY_CURRENT_PHONE_NUMBER, number)
                .apply();
    }

    public static String getCurrentPhoneNumber(Context context) {
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(KEY_CURRENT_PHONE_NUMBER, getSmsSendNumber(context));
    }

    public static void showToast(Context context, String message){
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
