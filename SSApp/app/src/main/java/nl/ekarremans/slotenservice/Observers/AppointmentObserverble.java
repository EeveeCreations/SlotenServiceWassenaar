package nl.ekarremans.slotenservice.Observers;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public interface AppointmentObserverble {
    void registerObserver(AppointmentObserver observer);

    void unregisterObserver(AppointmentObserver observer);

    void Update() throws FileNotFoundException, URISyntaxException;

}
