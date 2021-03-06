package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import nl.ekarremans.slotenservice.models.Appointment;

public class CalenderActivity extends AppCompatActivity implements AppointmentAdapter.OnNoteListener {
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private final FirebaseConnection firebaseConnection = FirebaseConnection.getInstance();
    private static CalenderActivity calenderActivity;

    public static CalenderActivity getInstance() {
        if (calenderActivity == null) {
            calenderActivity = new CalenderActivity();
        }
        return calenderActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Set a View
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        setEllements();

        //    Recycler View
        startRecycleView();
    }

    private void setEllements() {
        final Button archive = findViewById(R.id.openArchive);
        final Button appointment = findViewById(R.id.newAppointment);
        RecyclerView appointmentRecycler = findViewById(R.id.appointment_recycler);
        ActionBar actionBar = getActionBar();

        archive.setOnClickListener(this::openArchive);
        appointment.setOnClickListener(this::openAddAppointment);

        if (actionBar != null) {
            actionBar.setLogo(R.drawable.archive_icon);
            actionBar.setIcon(R.drawable.archive_icon);
        }
    }


    private void getInformationOfDatabase() {
        appointments = getAppointmentsOfTheDay();
        setAdapter();
    }


    //________________________Start RecyclerView ____________________________________________________________//

    private ArrayList<Appointment> getAppointmentsOfTheDay() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String today = dtf.format(LocalDateTime.now());
        return firebaseConnection.getDailyAppointmentsFromDB(today);
    }

    private void setAdapter() {
        AppointmentAdapter adapter = new AppointmentAdapter(appointments, this);
        RecyclerView appointmentRecycler = findViewById(R.id.appointment_recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        appointmentRecycler.setLayoutManager(layoutManager);
        appointmentRecycler.setItemAnimator(new DefaultItemAnimator());
        appointmentRecycler.setAdapter(adapter);
    }

    @Override
    public void onNoteClick(int position) {
        Appointment appointment = appointments.get(position);
        openSeeAppointment(appointment);
    }

    private void openSeeAppointment(Appointment appointment) {
        Intent intent = new Intent(this, AppointmentActivity.class);
        intent.putExtra("AppID", (Serializable) appointment);
        startActivity(intent);
    }
//________________________end RecyclerView ____________________________________________________________//


    //    Change to  new View
    private void openAddAppointment(View view) {
        Intent intent = new Intent(this, AddAppointmentActivity.class);
        startActivity(intent);

    }

    private void openArchive(View view) {
        firebaseConnection.getArchiveFromDB();
        Intent intent = new Intent(this, ArchiveActivity.class);
        startActivity(intent);
    }

    public void startRecycleView() {
        getInformationOfDatabase();
    }
}