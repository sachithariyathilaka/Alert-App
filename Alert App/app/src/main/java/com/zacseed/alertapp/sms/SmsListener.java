package com.zacseed.alertapp.sms;

public interface SmsListener {
    public void messageReceived(String messageText, String sender);
}