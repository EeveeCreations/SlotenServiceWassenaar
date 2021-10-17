package nl.ekarremans.slotenservice;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import nl.ekarremans.slotenservice.models.Appointment;

class FirebaseConnection {
    static FirebaseConnection firebaseConnection;
    static int AppointmentId;

    FirebaseDatabase database = FirebaseDatabase.getInstance();


    public static FirebaseConnection getInstance() {
        if (firebaseConnection == null) {
            firebaseConnection = new FirebaseConnection();
        }
        return firebaseConnection;
    }


    //    Write to Appointment database
    public boolean writeAppointentToDB(HashMap<String, Appointment> appointment, int nr) {
        DatabaseReference reference = database.getReference("sloten_service");
        reference.child(String.valueOf(nr)).setValue(appointment.get(String.valueOf(nr)));

        return true;
    }


    public DatabaseReference getDBrefrence(String ref) {
        return database.getReference("sloten_service/" + ref);
    }

    //   Get archiveAppointments
    public ArrayList<Appointment> getArchiveFromDB() {
        CalenderActivity calenderActivity = CalenderActivity.getInstance();
        ArrayList<Appointment> appointments = new ArrayList<>();
        DatabaseReference reference = database.getReference("sloten_service/appointment");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot DSS) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot dataSnapshot : DSS.getChildren()) {
                    Appointment appointment = dataSnapshot.getValue(Appointment.class);
                    appointments.add(appointment);
                }
                calenderActivity.startRecyleView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, String.valueOf(R.string.failed_connection_title));
            }
        });
        return appointments;
    }

    //    Get Day Appointments
    public String[] getDailyAppointmentsFromDB(String today) {
//        Make sure its only for today
        final String[] appoint = new String[1];
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("sloten_service/appoinment")
//                .orderByChild("appointment/date")
//                .equalTo(today)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        appoint[0] = value;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w(TAG, String.valueOf(R.string.failed_connection_title));

                    }
                });

        return appoint;
    }

}
