package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;

import nl.ekarremans.slotenservice.Observers.AppointmentObserver;
import nl.ekarremans.slotenservice.Observers.AppointmentObserverble;
import nl.ekarremans.slotenservice.models.Appointment;

public class AppointmentActivity extends AppCompatActivity implements AppointmentObserver {
    Appointment currentAppointment = new Appointment();
    FirebaseConnection firebaseConnection = FirebaseConnection.getInstance();
    ArrayList<AppointmentObserverble> observerbles = new ArrayList<>();
    //    Buttons
    private Button paidButton;
    private Button completeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
//        Get Appointment
        currentAppointment = (Appointment) getIntent().getExtras().getSerializable("AppID");
        registerAppointmentObserver(currentAppointment);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setButtons();
        setAppointmentSpecifics();
    }


    private void setAppointmentSpecifics() {
        ((TextView) findViewById(R.id.reg_app_cname)).setText(currentAppointment.getCustomerName());
        ((TextView) findViewById(R.id.reg_app_phone)).setText(currentAppointment.getPhone());
        ((TextView) findViewById(R.id.reg_app_date)).setText(currentAppointment.getDate());
        ((TextView) findViewById(R.id.reg_app_time)).setText(currentAppointment.getTime());
        ((TextView) findViewById(R.id.reg_app_price)).setText("€" + String.valueOf(currentAppointment.getPrice()));
        ((TextView) findViewById(R.id.reg_app_service)).setText(currentAppointment.getService());
    }

    private void setButtons() {
         paidButton = findViewById(R.id.paid_service);
        completeButton = findViewById(R.id.complete_service);

        if (currentAppointment.getIsPaid()) {
            paidButton.setEnabled(false);
        } else {
            paidButton.setOnClickListener(this::setAppointmentOnPaid);
        }
        if (currentAppointment.getIsCompleted()) {
            completeButton.setEnabled(false);
        } else {
            completeButton.setOnClickListener(this::setAppointmentOnCompleted);
        }
    }

    private void setAppointmentOnCompleted(View view) {
        completeButton.setEnabled(false);
        currentAppointment.setIsCompleted(!currentAppointment.getIsCompleted());
        firebaseConnection.updateAppointmentToArchiveDB(currentAppointment);
    }

    private void setAppointmentOnPaid(View view) {
        completeButton.setEnabled(false);
        currentAppointment.setIsPaid(!currentAppointment.getIsPaid());
        firebaseConnection.updateAppointmentToDB(currentAppointment);
    }

    //        return To Calender View
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void registerAppointmentObserver(Appointment appointment) {
        appointment.registerObserver(this);
    }

    @Override
    public void update(Appointment appointment) throws FileNotFoundException, URISyntaxException {
        setAppointmentSpecifics();
    }
}