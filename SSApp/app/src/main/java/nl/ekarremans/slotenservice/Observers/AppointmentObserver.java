package nl.ekarremans.slotenservice.Observers;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import nl.ekarremans.slotenservice.models.Appointment;

public interface AppointmentObserver {
    void update(Appointment appointment) throws FileNotFoundException, URISyntaxException;

}
