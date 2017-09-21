package co.nickwhite.salsapal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by nick4 on 20/09/2017.
 */

public class Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            SharedPreferences preferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
            boolean like_salsa = preferences.getBoolean("like_salsa", true);
            String message;
            if (like_salsa) {
                message = "Hey! Just letting you know that you love scooping up and eating the chunky spicy broth called \"salsa.\"";
            } else {
                message = "Hi there. Just so you know, you're not a fan of the soup with hunks they call \"salsa.\"";
            }
            Utils.generateNotification(context, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
