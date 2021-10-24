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

import java.util.ArrayList;

import nl.ekarremans.slotenservice.models.Appointment;

public class ArchiveActivity extends AppCompatActivity implements AppointmentAdapter.OnNoteListener {

    ArrayList<Appointment> archived = new ArrayList<>();
    FirebaseConnection firebaseConnection = FirebaseConnection.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        setButtons();

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        Recycle view
        setAdapter();

    }


    private void setButtons() {
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

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}