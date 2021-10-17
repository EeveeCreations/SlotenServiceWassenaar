package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import nl.ekarremans.slotenservice.models.Appointment;

public class AppointmentItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_item);

        final View appointment = findViewById(R.id.calender_appointment_item);
        appointment.setOnClickListener(this::OpenAppointment);
    }

    private void OpenAppointment(View view) {
        Intent intent = new Intent(AppointmentItem.this, Appointment.class);
//        Todo: Set and give appointment ID
        intent.putExtra("AppID","AppointmentID");
    }
}