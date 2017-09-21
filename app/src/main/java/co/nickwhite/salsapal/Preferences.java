package co.nickwhite.salsapal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Calendar;

public class Preferences extends AppCompatActivity {

    private PendingIntent pendingIntent;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        sharedPreferences = getApplicationContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);

        final RadioButton yesButton = (RadioButton)findViewById(R.id.yesButton);
        final RadioButton noButton = (RadioButton)findViewById(R.id.noButton);

        boolean like_salsa = sharedPreferences.getBoolean("like_salsa", true);

        yesButton.setChecked(like_salsa);
        noButton.setChecked(!like_salsa);

        yesButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPreferences.edit().putBoolean("like_salsa", isChecked).apply();
            }
        });

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.AM_PM, Calendar.AM);

        Intent intent = new Intent(Preferences.this, Receiver.class);
        pendingIntent = PendingIntent.getBroadcast(Preferences.this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
    }
}
