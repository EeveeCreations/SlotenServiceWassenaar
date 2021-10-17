package nl.ekarremans.slotenservice;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import nl.ekarremans.slotenservice.models.Appointment;

class FirebaseConnection {
    FirebaseDatabase database = FirebaseDatabase.getInstance();




    //    Write to Appointment database
    public boolean writeAppointentToDB(Appointment appointment) {
        DatabaseReference reference = database.getReference("Appointment");
        reference.setValue("Appointment");

        return true;
    }


    //   Get archiveAppointments
    public boolean getArchiveFromDB(Appointment appointment) {
        DatabaseReference reference = database.getReference("Archive");
        reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String value = dataSnapshot.getValue(String.class);
                    Log.d(TAG, "Value is: " + value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
//                    Log.w(TAG, String.valueOf(R.string.failed_connection_title));
                }
            });


        return true;
    }

    //    Get Day Appointments
    public boolean getDailyAppointmentsFromDB(Appointment appointment) {
        DatabaseReference reference = database.getReference("Appointment");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Log.w(TAG, String.valueOf(R.string.failed_connection_title));
            }
        });
        return true;
    }

}
