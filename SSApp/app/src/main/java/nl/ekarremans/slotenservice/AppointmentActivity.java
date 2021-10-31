package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nl.ekarremans.slotenservice.models.Appointment;

public class AppointmentActivity extends AppCompatActivity {
    Appointment currentAppointment = new Appointment();
    FirebaseConnection firebaseConnection = FirebaseConnection.getInstance();
    //    Buttons
    private Button paidButton;
    private Button completeButton;
    private Button deleteButton;
    private TextView phoneIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
//        Get Appointment
        currentAppointment = (Appointment) getIntent().getExtras().getSerializable("AppID");
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
        ((TextView) findViewById(R.id.reg_app_paid)).setText(R.string.appointment_is_not_paid);
        ((TextView) findViewById(R.id.reg_app_completed)).setText(R.string.appointment_is_not_completed);

        changeToActualText();
    }

    private void changeToActualText() {
        if(currentAppointment.getIsPaid()) {
            ((TextView) findViewById(R.id.reg_app_paid)).setText(R.string.appointment_is_paid);
        }
        if(currentAppointment.getIsCompleted()) {
            ((TextView) findViewById(R.id.reg_app_completed)).setText(R.string.appointment_is_completed);
        }
    }

    private void setButtons() {
        //    Buttons
        paidButton = findViewById(R.id.paid_service);
        completeButton = findViewById(R.id.complete_service);
        deleteButton = findViewById(R.id.delete_button);
        phoneIcon = findViewById(R.id.reg_app_phone);

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

        deleteButton.setOnClickListener(this::deleteAppointment);
        phoneIcon.setOnClickListener(this::callPhoneClient);
    }

    private void setAppointmentOnCompleted(View view) {
        completeButton.setEnabled(false);
        currentAppointment.setIsCompleted(!currentAppointment.getIsCompleted());
        firebaseConnection.updateAppointmentToArchiveDB(currentAppointment);
        returnToMain();
    }

    private void setAppointmentOnPaid(View view) {
        paidButton.setEnabled(false);
        currentAppointment.setIsPaid(!currentAppointment.getIsPaid());
        firebaseConnection.updateAppointmentToDB(currentAppointment);
        returnToMain();
    }
//    Watchout
    private void deleteAppointment(View view) {
       firebaseConnection.deleteAppointmentFromDB(currentAppointment);
        returnToMain();
    }

    private void callPhoneClient(View view) {
        String number = currentAppointment.getPhone().replace("", "");
        Intent callIntent = new Intent(Intent.ACTION_CALL); // Could have been call but is here so a miss click can be overcome
        callIntent.setData(Uri.parse("tel:"+ number));
        startActivity(callIntent);
    }


    //        return To Calender View
    private void returnToMain(){
        Intent intent = new Intent(this, CalenderActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}