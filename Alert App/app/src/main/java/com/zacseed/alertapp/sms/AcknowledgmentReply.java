package com.zacseed.alertapp.sms;

public class AcknowledgmentReply {
    private String location_id,alarm_index,alarm_ref, phoneNumber;

    public AcknowledgmentReply(String location_id, String alarm_index, String alarm_ref) {
        this.location_id = location_id;
        this.alarm_index = alarm_index;
        this.alarm_ref = alarm_ref;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getAlarm_index() {
        return alarm_index;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAlarm_index(String alarm_index) {
        this.alarm_index = alarm_index;
    }

    public String getAlarm_ref() {
        return alarm_ref;
    }

    public void setAlarm_ref(String alarm_ref) {
        this.alarm_ref = alarm_ref;
    }
}
