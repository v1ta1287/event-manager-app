package com.example.eventmanagerapp.controller.handler;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;

import java.util.StringTokenizer;

public class SMSReceiver extends BroadcastReceiver {
    public static final String SMS_FILTER = "SMS_FILTER";

    public static final String SMS_KEY= "SMS_KEY";

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

        for (SmsMessage currentMessage : messages) {
            String message = currentMessage.getDisplayMessageBody();
            Intent msgIntent = new Intent();
            msgIntent.setAction(SMS_FILTER);
            msgIntent.putExtra(SMS_KEY, message);
            context.sendBroadcast(msgIntent);
        }
    }
}