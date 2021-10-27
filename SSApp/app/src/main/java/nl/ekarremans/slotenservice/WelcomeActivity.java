package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class WelcomeActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_MS = 2000;
    private final FirebaseConnection firebaseConnection = FirebaseConnection.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        String today =getAppointmentsOfTheDay(); ;
        setAllAppointmentsOfYesterdayToArchive(today);

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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String sToday = dtf.format(LocalDateTime.now());
        firebaseConnection.getDailyAppointmentsFromDB(sToday);
        return sToday;
    }

    //________________________ Set all Appointments of Yesterday to Archive ____________________________________________________________//
    private void setAllAppointmentsOfYesterdayToArchive(String sToday) {
        SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy");
        Date today = null;
        try {
            today = stf.parse(sToday);
            firebaseConnection.moveYesterdayAppointmentsToArchive(today);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}