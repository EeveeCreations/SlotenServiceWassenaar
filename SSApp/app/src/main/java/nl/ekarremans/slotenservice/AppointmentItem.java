package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import nl.ekarremans.slotenservice.models.Appointment;

public class AppointmentItem extends AppCompatActivity {
    FirebaseConnection firebaseConnection = FirebaseConnection.getInstance();
    static AppointmentItem appointmentItem;

    public static AppointmentItem getInstance(){
        if (appointmentItem == null){
            appointmentItem = new AppointmentItem();
        }
        return appointmentItem;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_item);

    }
}