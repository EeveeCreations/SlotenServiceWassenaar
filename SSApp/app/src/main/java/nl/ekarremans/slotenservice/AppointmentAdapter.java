package nl.ekarremans.slotenservice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nl.ekarremans.slotenservice.models.Appointment;

class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {
    private ArrayList<Appointment> appointments;
    private static AppointmentAdapter appointmentAdapter;

    public static AppointmentAdapter getInstance() {
        if(appointmentAdapter == null){
            appointmentAdapter = new AppointmentAdapter();
        }
        return appointmentAdapter;
    }

    public AppointmentAdapter(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public AppointmentAdapter() {
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {
        private TextView clientName;
        private TextView timeAppointment;

        public AppointmentViewHolder(final View v) {
            super(v);
            clientName = v.findViewById(R.id.reg_app_cName);
            timeAppointment = v.findViewById(R.id.appointment_time);

        }
    }

    @NonNull
    @Override
    public AppointmentAdapter.AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View appointmentItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_appointment_item, parent, false);
        return new AppointmentViewHolder(appointmentItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.AppointmentViewHolder holder, int position) {
        String name = appointments.get(position).getCustomerName();
        holder.clientName.setText(name);

        String time = appointments.get(position).getTime().toString();
        holder.timeAppointment.setText(time);
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }
}
