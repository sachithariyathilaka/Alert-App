package com.zacseed.alertapp.sms;

public class SingleOccurrence {
    private String location, alarmIndex, alarmRef, alarmId, alarmType, alarmLevel, timestamp, phoneNumber;

    public SingleOccurrence() {
    }

    public SingleOccurrence(String location, String alarmIndex, String alarmRef, String alarmId, String alarmType, String alarmLevel,String timestamp) {
        this.location = location;
        this.alarmIndex = alarmIndex;
        this.alarmRef = alarmRef;
        this.alarmId = alarmId;
        this.alarmType = alarmType;
        this.alarmLevel = alarmLevel;
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAlarmIndex() {
        return alarmIndex;
    }

    public void setAlarmIndex(String alarmIndex) {
        this.alarmIndex = alarmIndex;
    }

    public String getAlarmRef() {
        return alarmRef;
    }

    public void setAlarmRef(String alarmRef) {
        this.alarmRef = alarmRef;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
