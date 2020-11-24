package com.zacseed.alertapp.sms;

public class MultipleOccurance {
    private String locationId, alarmIndex, alarmRef, alarmId, count, alarmType, alarmLevel, firstGeneratedTimestamp, lastGeneratedTimestamp, firstClearedTimestamp, lastClearedTimestamp, phoneNumber;

    public MultipleOccurance() {
    }

    public MultipleOccurance(String locationId, String alarmIndex, String alarmRef, String alarmId, String count, String alarmType, String alarmLevel, String firstGeneratedTimestamp, String lastGeneratedTimestamp, String firstClearedTimestamp, String lastClearedTimestamp) {
        this.locationId = locationId;
        this.alarmIndex = alarmIndex;
        this.alarmRef = alarmRef;
        this.alarmId = alarmId;
        this.count = count;
        this.alarmType = alarmType;
        this.alarmLevel = alarmLevel;
        this.firstGeneratedTimestamp = firstGeneratedTimestamp;
        this.lastGeneratedTimestamp = lastGeneratedTimestamp;
        this.firstClearedTimestamp = firstClearedTimestamp;
        this.lastClearedTimestamp = lastClearedTimestamp;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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

    public String getFirstGeneratedTimestamp() {
        return firstGeneratedTimestamp;
    }

    public void setFirstGeneratedTimestamp(String firstGeneratedTimestamp) {
        this.firstGeneratedTimestamp = firstGeneratedTimestamp;
    }

    public String getLastGeneratedTimestamp() {
        return lastGeneratedTimestamp;
    }

    public void setLastGeneratedTimestamp(String lastGeneratedTimestamp) {
        this.lastGeneratedTimestamp = lastGeneratedTimestamp;
    }

    public String getFirstClearedTimestamp() {
        return firstClearedTimestamp;
    }

    public void setFirstClearedTimestamp(String firstClearedTimestamp) {
        this.firstClearedTimestamp = firstClearedTimestamp;
    }

    public String getLastClearedTimestamp() {
        return lastClearedTimestamp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLastClearedTimestamp(String lastClearedTimestamp) {
        this.lastClearedTimestamp = lastClearedTimestamp;
    }
}
