package com.zacseed.alertapp.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.zacseed.alertapp.utils.PreferenceUtils;

public class SmsReceiver extends BroadcastReceiver {

    //interface
    private static SmsListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data  = intent.getExtras();

        Object[] pdus = (Object[]) data.get("pdus");

        for(int i=0;i<pdus.length;i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

            String sender = smsMessage.getOriginatingAddress();
            //Check the sender to filter messages which we require to read

            if (sender.equals(PreferenceUtils.getSmsSendNumber(context)) || sender.equals("+94"+PreferenceUtils.getSmsSendNumber(context).substring(1))
                    || sender.equals(PreferenceUtils.getSmsSendNumber2(context)) || sender.equals("+94"+PreferenceUtils.getSmsSendNumber2(context).substring(1))
                    || sender.equals(PreferenceUtils.getSmsSendNumber3(context)) || sender.equals("+94"+PreferenceUtils.getSmsSendNumber3(context).substring(1))
                    || sender.equals(PreferenceUtils.getSmsSendNumber4(context)) || sender.equals("+94"+PreferenceUtils.getSmsSendNumber4(context).substring(1))
                    || sender.equals(PreferenceUtils.getSmsSendNumber5(context)) || sender.equals("+94"+PreferenceUtils.getSmsSendNumber5(context).substring(1))
            )
            {

                String messageBody = smsMessage.getMessageBody();

                //Pass the message text to interface
                mListener.messageReceived(messageBody, sender);

            }

//            String messageBody = smsMessage.getMessageBody();
//
//            //Pass the message text to interface
//            mListener.messageReceived(messageBody);
        }

    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }
}
