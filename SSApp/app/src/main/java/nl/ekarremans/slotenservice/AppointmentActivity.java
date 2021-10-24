package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import nl.ekarremans.slotenservice.models.Appointment;

public class AppointmentActivity extends AppCompatActivity {
    Appointment currentAppointment = new Appointment();
    FirebaseConnection firebaseConnection = FirebaseConnection.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
//        Get Appointment
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                currentAppointment =(Appointment) extras.getSerializable("AppID");
            }
        } else {
            currentAppointment =(Appointment) savedInstanceState.getSerializable("AppID");
        }
//        Bundle extras = getIntent().getExtras();
//        String currentAppointmentId = extras.getParcelable("AppID");
//        setCurrentAppointment(currentAppointmentId);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setButtons();
        setAppointmentSpecifics();
    }


    private void setCurrentAppointment(String currentAppointmentId) {
        currentAppointment = firebaseConnection.getSpecificAppointmentAppointment(currentAppointmentId);
    }

    private void setAppointmentSpecifics() {
        ((TextView) findViewById(R.id.reg_app_cname)).setText(currentAppointment.getCustomerName());
        ((TextView) findViewById(R.id.reg_app_phone)).setText(currentAppointment.getPhone());
        ((TextView) findViewById(R.id.reg_app_date)).setText(currentAppointment.getDate());
        ((TextView) findViewById(R.id.reg_app_time)).setText(currentAppointment.getTime());
        ((TextView) findViewById(R.id.reg_app_price)).setText(String.valueOf(currentAppointment.getPrice()));
        ((TextView) findViewById(R.id.reg_app_service)).setText(currentAppointment.getService());
    }

    private void setButtons() {
        final Button paidButton = findViewById(R.id.paid_service);
        final Button completeButton = findViewById(R.id.complete_service);

        paidButton.setOnClickListener(this::setAppointmentOnPaid);
        completeButton.setOnClickListener(this::setAppointmentOnCompleted);

    }

    private void setAppointmentOnCompleted(View view) {
        currentAppointment.setIsCompleted(!currentAppointment.getIsCompleted());
    }

    private void setAppointmentOnPaid(View view) {
        currentAppointment.setIsPaid(!currentAppointment.getIsPaid());

    }

//        return To Calender View
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}