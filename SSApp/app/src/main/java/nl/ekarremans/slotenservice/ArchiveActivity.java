package nl.ekarremans.slotenservice;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

import nl.ekarremans.slotenservice.models.Appointment;

public class ArchiveActivity extends AppCompatActivity implements AppointmentAdapter.OnNoteListener {

    ArrayList<Appointment> archived = new ArrayList<>();
    FirebaseConnection firebaseConnection = FirebaseConnection.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        Recycle view
        startRecycleView();
    }

    private void startRecycleView() {
        getArchivedAppointments();
        setAdapter();
    }


    private void setAdapter() {
        getArchivedAppointments();
        AppointmentAdapter adapter = new AppointmentAdapter(archived, this);
        RecyclerView appointmentRecycler = findViewById(R.id.archive_recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        appointmentRecycler.setLayoutManager(layoutManager);
        appointmentRecycler.setItemAnimator(new DefaultItemAnimator());
        appointmentRecycler.setAdapter(adapter);
    }

    private void getArchivedAppointments() {
        archived = firebaseConnection.getArchiveFromDB();
    }


    @Override
    public void onNoteClick(int position) {
        Appointment appointment = archived.get(position);
        openSeeAppointment(appointment);
    }

    private void openSeeAppointment(Appointment appointment) {
        Intent intent = new Intent(this, AppointmentActivity.class);
        intent.putExtra("AppID", (Serializable) appointment);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}