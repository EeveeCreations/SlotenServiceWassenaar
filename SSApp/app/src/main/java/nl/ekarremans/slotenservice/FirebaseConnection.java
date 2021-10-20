package nl.ekarremans.slotenservice;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Service;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Provider;
import java.util.ArrayList;
import java.util.HashMap;

import nl.ekarremans.slotenservice.models.Appointment;
import nl.ekarremans.slotenservice.models.kService;

class FirebaseConnection {
    static FirebaseConnection firebaseConnection;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://slotenservicevoorschoten-default-rtdb.europe-west1.firebasedatabase.app");

    public static FirebaseConnection getInstance() {
        if (firebaseConnection == null) {
            firebaseConnection = new FirebaseConnection();
        }
        return firebaseConnection;
    }

//    __________________GET ____________________________________________//

    //   Get archiveAppointments
    public ArrayList<Appointment> getArchiveFromDB() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        DatabaseReference reference = database.getReference("sloten_service/appointment");
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot DSS) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot dataSnapshot : DSS.getChildren()) {
                    Appointment appointment = dataSnapshot.getValue(Appointment.class);
                    appointments.add(appointment);
                }
                AppointmentAdapter.getInstance().notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, String.valueOf(R.string.failed_connection_title));
            }
        });
        return appointments;
    }

    //   Get archiveAppointments
    public ArrayList<kService> getServiceFromDB() {
        ArrayList<kService> services = new ArrayList<>();
        DatabaseReference reference = database.getReference("sloten_service/service");
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot DSS) {
               for (DataSnapshot dataSnapshot : DSS.getChildren()) {
                    kService service = dataSnapshot.getValue(kService.class);
                    services.add(service);
                }
                AppointmentAdapter.getInstance().notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, String.valueOf(R.string.failed_connection_title));
            }
        });
        return services;
    }

    //    Get Day Appointments
    public ArrayList<Appointment> getDailyAppointmentsFromDB(String today) {
//        Make sure its only for today
        final ArrayList<Appointment> appointments = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("sloten_service/appoinment")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot DSS) {
                        /*Specify the date*/
                        for (DataSnapshot dataSnapshot : DSS.getChildren()) {
                            for (DataSnapshot apointShot : dataSnapshot.getChildren()) {
                                if (apointShot.child("date").getValue().equals(today)) {
                                    Appointment appointment = dataSnapshot.getValue(Appointment.class);
                                    appointments.add(appointment);
                                }
                            }
                        }
                        AppointmentAdapter.getInstance().notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w(TAG, String.valueOf(R.string.failed_connection_title));

                    }
                });

        return appointments;
    }


//    _____________Write to _______________________//

    //    Write to Appointment database
    public boolean writeAppointmentToDB(Appointment appointment) {
        DatabaseReference reference = database.getReference("sloten_service/appointment");
        String id = reference.push().getKey();
        appointment.setId(id);

        reference.child(id).setValue(appointment);
        return true;
    }

    //    Write to Appointment database
    public boolean writeServiceToDB(Service service) {
        DatabaseReference reference = database.getReference("sloten_service/service");
        String id = reference.push().getKey();

        reference.child(id).setValue(service);
        return true;
    }


}
