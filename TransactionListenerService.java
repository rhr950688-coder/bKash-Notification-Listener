package com.itsaky.androidide.services;

import android.app.Notification;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class TransactionListenerService extends NotificationListenerService {

    private static final String TAG = "TransactionListener";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String packageName = sbn.getPackageName();
        
        if ("com.bKash.customerapp".equals(packageName) || "com.konasl.nagad".equals(packageName)) {
            Bundle extras = sbn.getNotification().extras;
            String title = extras.getString(Notification.EXTRA_TITLE);
            String text = extras.getString(Notification.EXTRA_TEXT);

            Log.d(TAG, "Notification received from: " + packageName);
            Log.d(TAG, "Title: " + title);
            Log.d(TAG, "Text: " + text);

            if (text != null) {
                parseTransaction(packageName, text);
            }
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
    }

    private void parseTransaction(String packageName, String message) {
        if (packageName.contains("bKash")) {
            Log.d(TAG, "Parsing bKash transaction...");
        } else if (packageName.contains("nagad")) {
            Log.d(TAG, "Parsing Nagad transaction...");
        }
    }
}
