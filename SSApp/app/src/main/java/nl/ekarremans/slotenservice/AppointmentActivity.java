package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nl.ekarremans.slotenservice.models.Appointment;

public class AppointmentActivity extends AppCompatActivity {

    Appointment currentAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        currentAppointment = new Appointment();
        setButtons();
        setAppointmentSpecifics();
    }

    private void setAppointmentSpecifics() {
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
    }
}