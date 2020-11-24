package com.zacseed.alertapp;

public class LogModel {
    private String type, locationId,alarmId,timestamp,Received_time, phoneNumber;

    public LogModel(String type, String locationId, String alarmId, String timestamp,String Received_time) {
        this.type = type;
        this.locationId = locationId;
        this.alarmId = alarmId;
        this.timestamp = timestamp;
        this.Received_time=Received_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public String getReceived_time() {
        return Received_time;
    }

    public void setReceived_time(String received_time) {
        Received_time = received_time;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
