package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import nl.ekarremans.slotenservice.models.Appointment;
import nl.ekarremans.slotenservice.models.kService;

public class AddAppointmentActivity extends AppCompatActivity {
    FirebaseConnection firebaseConnection = FirebaseConnection.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setButtons();
        addServiceSpinner();
    }

    private void setButtons() {
        final Button addB = findViewById(R.id.add_appointment);
        addB.setOnClickListener(this::addAppointment);
    }

    private void addServiceSpinner() {
        ArrayList<String> serviceNames = new ArrayList<>();
        serviceNames.add("Advise");
        serviceNames.add("Sloten inzetten");
        serviceNames.add("Deuren openen");
        serviceNames.add("Deur inzetten");
//
//        ArrayList<kService> services = firebaseConnection.getServiceFromDB();
//        ArrayList<String> serviceNames = services.stream().map(kService::getName)
//                .collect(Collectors.toCollection(ArrayList::new));
//       // Set a small Adapter for the spinner
        Spinner serviceSpinner = findViewById(R.id.appointment_service_Spinner);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, serviceNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        serviceSpinner.setAdapter(adapter);
    }


    //      Add an Appointment
    private void addAppointment(View view) {
        firebaseConnection.writeAppointmentToDB(getAllInformation());
        showPopUp();
    }

    private void showPopUp() {
            Intent intent = new Intent(this, CalenderActivity.class);
            startActivity(intent);
    }

    private Appointment getAllInformation() {
        String cName = ((EditText) findViewById(R.id.client_edit_name)).getText().toString();
        String cPhone = ((EditText) findViewById(R.id.client_edit_phone)).getText().toString();
        String aDate = ((EditText) findViewById(R.id.appointment_edit_date)).getText().toString();
        String aTime = ((EditText) findViewById(R.id.appointment_edit_time)).getText().toString();
        float aPrice = Float.parseFloat(((EditText) findViewById(R.id.appointment_edit_price)).getText().toString());
        String aService = ((Spinner) findViewById(R.id.appointment_service_Spinner)).getSelectedItem().toString();

//      set correct Setup for information
        aDate = aDate.replace("/", "-");
        aDate = aDate.replace(" ", "");
        aDate+= "-" + LocalDate.now().getYear();

        aTime = aTime.replace("-", ":");
        aTime = aTime.replace(" ", "");

        cPhone = cPhone.replace("-", "");
        cPhone = cPhone.replace(" ", "");


        Appointment appointment = new Appointment(aService, cName, cPhone, aTime, aDate, aPrice, false, false);

        return appointment;
    }


    //    Return to CalenderScreen
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}