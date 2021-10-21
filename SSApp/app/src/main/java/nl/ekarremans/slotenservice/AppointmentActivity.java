package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import nl.ekarremans.slotenservice.models.Appointment;

public class AppointmentActivity extends AppCompatActivity {
    Appointment currentAppointment = new Appointment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        currentAppointment = getIntent().getParcelableExtra("AppID");
        setButtons();
        setAppointmentSpecifics();
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
        final Button rButton = findViewById(R.id.returnMainButton);
        final Button paidButton = findViewById(R.id.paid_service);
        final Button completeButton = findViewById(R.id.complete_service);

        rButton.setOnClickListener(this::returnToMainMenu);
        paidButton.setOnClickListener(this::setAppointmentOnPaid);
        completeButton.setOnClickListener(this::setAppointmentOnCompleted);

    }

    private void setAppointmentOnCompleted(View view) {
        currentAppointment.setIsCompleted(!currentAppointment.getIsCompleted());
    }

    private void setAppointmentOnPaid(View view) {
        currentAppointment.setIsPaid(!currentAppointment.getIsPaid());

    }

    private void returnToMainMenu(View view) {
        Intent intent = new Intent(AppointmentActivity.this, CalenderActivity.class);
        startActivity(intent);
    }
}