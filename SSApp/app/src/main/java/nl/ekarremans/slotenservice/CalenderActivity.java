package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Set a View
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);


        //        Set Buttons
        final Button archive = findViewById(R.id.openArchive);
        final Button appointment = findViewById(R.id.newAppointment);

//        Set Onclick Listeners
        archive.setOnClickListener(this::openArchive);
        appointment.setOnClickListener(this::openAppointment);


    }


    //    Change to  new View
    private void openAppointment(View view) {
        Intent intent = new Intent(this, AddAppointmentActivity.class);
        startActivity(intent);

    }

    private void openArchive(View view) {
        Intent intent = new Intent(this, ArchiveActivity.class);
        startActivity(intent);
    }

}