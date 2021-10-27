package nl.ekarremans.slotenservice;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import nl.ekarremans.slotenservice.models.Appointment;
import nl.ekarremans.slotenservice.models.kService;

class FirebaseConnection {
    static FirebaseConnection firebaseConnection;
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private final AppointmentAdapter adapter = AppointmentAdapter.getInstance();
    private final FirebaseDatabase database = FirebaseDatabase.getInstance("https://slotenservicevoorschoten-default-rtdb.europe-west1.firebasedatabase.app");

    private final DatabaseReference service = database.getReference("sloten_service/service");
    private final DatabaseReference archive = database.getReference("sloten_service/archives");
    private final DatabaseReference appointment = database.getReference("sloten_service/appointment");


    public static FirebaseConnection getInstance() {
        if (firebaseConnection == null) {
            firebaseConnection = new FirebaseConnection();
        }
        return firebaseConnection;
    }


    //    __________________GET ____________________________________________//
    //   Get specific Appointment
    public Appointment getSpecificAppointmentAppointment(String appID) {
        final Appointment[] wantedApp = {new Appointment()};
        appointment.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot DSS) {
                for (DataSnapshot dataSnapshot : DSS.getChildren()) {
                    Appointment appointment = dataSnapshot.getValue(Appointment.class);
                    if (appointment.getId().equals(appID)) {
                        wantedApp[0] = appointment;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, String.valueOf(R.string.failed_connection_title));
            }
        });
        if (wantedApp.length < 1) {
            wantedApp[0] = getSpecificAppointmentArchive(appID);
        }
        return wantedApp[0];
    }

    public Appointment getSpecificAppointmentArchive(String appID) {
        final Appointment[] wantedApp = {new Appointment()};
        archive.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot DSS) {
                for (DataSnapshot dataSnapshot : DSS.getChildren()) {
                    Appointment appointment = dataSnapshot.getValue(Appointment.class);
                    if (appointment.getId().equals(appID)) {
                        wantedApp[0] = appointment;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, String.valueOf(R.string.failed_connection_title));
            }
        });
        return wantedApp[0];
    }

    //   Get archiveAppointments
    public ArrayList<Appointment> getArchiveFromDB() {
        appointments.clear();
        archive.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot DSS) {
                for (DataSnapshot dataSnapshot : DSS.getChildren()) {
                    Appointment appointment = dataSnapshot.getValue(Appointment.class);
                    appointments.add(appointment);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, String.valueOf(R.string.failed_connection_title));
            }
        });
        return appointments;
    }

    //   Get Services
    public ArrayList<kService> getServiceFromDB() {
        ArrayList<kService> services = new ArrayList<>();
        service.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot DSS) {
                for (DataSnapshot dataSnapshot : DSS.getChildren()) {
                    kService service = dataSnapshot.getValue(kService.class);
                    services.add(service);
                }
                adapter.notifyDataSetChanged();

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
        appointments.clear();
//        Make sure its only for today
        appointment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot DSS) {
                /*Specify the date*/
                for (DataSnapshot dataSnapshot : DSS.getChildren()) {
                    Appointment appointment = dataSnapshot.getValue(Appointment.class);
                    if (appointment.getDate().equals(today)) {
                        appointments.add(appointment);
                    }
                }
                adapter.notifyDataSetChanged();
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
    public void writeAppointmentToDB(Appointment appointment) {
        String id = this.appointment.push().getKey();
        appointment.setId(id);

        this.appointment.child(id).setValue(appointment);
    }

    //    Write to Service database
    public void writeServiceToDB(kService kService) {
        String id = service.push().getKey();

        service.child(id).setValue(kService);
    }
//    _____________Write to  UPDATE_______________________//

    public void updateAppointmentToDB(Appointment appointment) {
        String id = appointment.getId();
        this.appointment.child(id).setValue(appointment);
    }

    public void updateServiceToDB(kService kService) {
        String id = kService.getName();
        service.child(id).setValue(kService);
    }

    //    Transfer to database
    public void updateAppointmentToArchiveDB(Appointment appointment) {
        updateAppointmentToDB(appointment);
        String id = appointment.getId();
        archive.child(id).setValue(appointment);
        this.appointment.child(id).removeValue();
    }

    //    _____________Delete from Database_______________________//

    //    Write to Appointment database
    public boolean deleteAppointmentFromDB(Appointment appointment) {
        String id = appointment.getId();
        this.appointment.child(id).removeValue();
        return true;
    }

    //    _____________Appointments to Archive_______________________//


    public void moveYesterdayAppointmentsToArchive(Date todayDTF) {
        getOldAppointments(todayDTF);
    }

    private void getOldAppointments(Date today) {
        SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy");
        appointment.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot DSS) {
                for (DataSnapshot dataSnapshot : DSS.getChildren()) {
                    Appointment appointment = dataSnapshot.getValue(Appointment.class);
                    Date appDateDTF = null;
                    try {
                        appDateDTF = stf.parse(appointment.getDate());
                        if (appDateDTF.before(today)) {
                            updateAppointmentToArchiveDB(appointment);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, String.valueOf(R.string.failed_connection_title));
            }
        });
    }
}
