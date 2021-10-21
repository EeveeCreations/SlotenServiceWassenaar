package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import nl.ekarremans.slotenservice.models.Appointment;

public class WelcomeActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_MS = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //        prepare Recycle
        getAppointmentsOfTheDay();

//      Show logo of SSVW  voor doorgaan naar Calender
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent calender = new Intent(WelcomeActivity.this, CalenderActivity.class);
                startActivity(calender);
                WelcomeActivity.this.finish();
            }
        }, SPLASH_DISPLAY_MS);

    }
    private String getAppointmentsOfTheDay() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd - MM");
        String today = dtf.format(LocalDateTime.now());
        FirebaseConnection.getInstance().getDailyAppointmentsFromDB(today);
        return today;
    }
}