package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import nl.ekarremans.slotenservice.models.Appointment;

public class AddAppointmentActivity extends AppCompatActivity {
    FirebaseConnection firebaseConnection = FirebaseConnection.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
        setButtons();
        addServiceSpinner();
    }

    private void setButtons() {
        final Button returnB = findViewById(R.id.returnMainButton);
        returnB.setOnClickListener(this::returnToMain);

        final Button addB = findViewById(R.id.add_appointment);
        addB.setOnClickListener(this::addAppointment);
    }

    private void addServiceSpinner() {
    //      todo:  Get all Services from DB
        ArrayList<String> services = new ArrayList<>();
        services.add("Advise");
        services.add("Sloten inzetten");
        services.add("Deuren openen");
        services.add("Deur inzetten");

       // Set a small Adapter for the spinner
         Spinner serviceSpinner = findViewById(R.id.appointment_service_Spinner);
            ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, services);
            adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

            serviceSpinner.setAdapter(adapter);
    }


    //      Add an Appointment
    private void addAppointment(View view) {
        boolean isFinnished = firebaseConnection.writeAppointmentToDB(getAllInformation());
        showPopUp(isFinnished);
    }

    private void showPopUp(boolean b) {
        if(b){
            Intent intent = new Intent(this, CalenderActivity.class);
            startActivity(intent);
        }
    }
    private Appointment getAllInformation() {
        String cName = ((EditText) findViewById(R.id.client_edit_name)).getText().toString();
        String cPhone = ((EditText) findViewById(R.id.client_edit_phone)).getText().toString();
        String aDate = ((EditText) findViewById(R.id.appointment_edit_date)).getText().toString();
        String aTime =((EditText) findViewById(R.id.appointment_edit_time)).getText().toString();
        float aPrice = Float.parseFloat(((EditText) findViewById(R.id.appointment_edit_price)).getText().toString());
        String aService = ((Spinner) findViewById(R.id.appointment_service_Spinner)).getSelectedItem().toString();

        Appointment appointment = new Appointment(aService,cName,cPhone, aTime,aDate,aPrice,false ,false);

        return appointment;
    }

    //    Return to CalenderScreen
    private void returnToMain(View view) {
    Intent intent = new Intent(this, CalenderActivity.class);
    startActivity(intent);
    }
}