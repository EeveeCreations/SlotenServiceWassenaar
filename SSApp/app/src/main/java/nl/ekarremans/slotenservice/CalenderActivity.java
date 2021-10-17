package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.Timestamp;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import nl.ekarremans.slotenservice.models.Appointment;

public class CalenderActivity extends AppCompatActivity {
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private RecyclerView appointmentRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Set a View
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);


        //        Set Elements
        final Button archive = findViewById(R.id.openArchive);
        final Button appointment = findViewById(R.id.newAppointment);
        appointmentRecycler = findViewById(R.id.appointment_recycler);


//        Set Onclick Listeners
        archive.setOnClickListener(this::openArchive);
        appointment.setOnClickListener(this::openAppointment);

        //    Recycler View
        setAdapter();
        setPlaceHolderInfo();


    }

    private void setAdapter() {
        AppointmentAdapter adapter = new AppointmentAdapter(appointments);
        RecyclerView appointmentRecycler = findViewById(R.id.appointment_recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        appointmentRecycler.setLayoutManager(layoutManager);
        appointmentRecycler.setItemAnimator(new DefaultItemAnimator());
        appointmentRecycler.setAdapter(adapter);
    }

    private void setPlaceHolderInfo() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        int x = 0;
        while(x < 4){
            Appointment appointment = new Appointment("Deur instaleren", "Jan Klaasen", dtf.format(LocalDateTime.now()).toString(), 122.4f, false, false);
            Appointment appointment2 = new Appointment("Vervangen Slot", "Ykia  Roost", "16:10", 42.4f, false, true);
            Appointment appointment3 = new Appointment("Sluetelen", "Julia Smith", dtf.format(LocalDateTime.now()).toString(), 82.4f, true, false);

            appointments.add(appointment);
            appointments.add(appointment2);
            appointments.add(appointment3);
            x++;
        }


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