package com.zacseed.alertapp.utils;

import com.zacseed.alertapp.sms.MultipleOccurance;
import com.zacseed.alertapp.sms.SingleOccurrence;

public class SmsUtils {
    public static final String ACKNOWLEDGMENT_REPLY_FORMAT = "%s,%s,%s";
    public static final String SMS_TYPE_ACKNOWLEDGEMENT = "Acknowledgement";
    public static final String SMS_TYPE_ALERT = "Alarm Received";

    public static String getAcknowledgmentReplyString(String location, String alarmIndex, String alarmRef){
        return String.format(ACKNOWLEDGMENT_REPLY_FORMAT, location, alarmIndex, alarmRef);
    }

    public static SingleOccurrence getSingleOccuranceFromString(String message){
        String[] csv = message.split(",");
        SingleOccurrence singleOccurrence;
        if (csv.length == 7){
            singleOccurrence = new SingleOccurrence(
                    csv[0],
                    csv[1],
                    csv[2],
                    csv[3],
                    csv[4],
                    csv[5],
                    csv[6]
            );
            return singleOccurrence;
        }else {
            return null;
        }
    }

    public static MultipleOccurance getMultipleOccurrenceFromString(String message){
        MultipleOccurance multipleOccurance;
        String[] csv = message.split(",");
        if (csv.length == 10){
            String count = csv[3].replaceAll("\\D+","");
            multipleOccurance = new MultipleOccurance(
                    csv[0],
                    csv[1],
                    csv[2],
                    csv[3],
                    count,
                    csv[4],
                    csv[5],
                    csv[6],
                    csv[7],
                    csv[8],
                    csv[9]
            );
            return multipleOccurance;
        }else {
            return null;
        }
    }
}
