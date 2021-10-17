package nl.ekarremans.slotenservice;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import nl.ekarremans.slotenservice.models.Appointment;

class FirebaseConnection extends FirebaseApp {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    /**
     * Default constructor.
     *
     * @param applicationContext
     * @param name
     * @param options
     * @hide
     */
    protected FirebaseConnection(Context applicationContext, String name, FirebaseOptions options) {
        super(applicationContext, name, options);
    }


    //    Write to database
    public boolean writeAppointentToDB(Appointment appointment) {
        try {
            reference.setValue(appointment);


        } catch (Exception e) {
            return false;

        }
        return true;
    }

}
