package nl.ekarremans.slotenservice;

import android.graphics.drawable.Drawable;
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
    private OnNoteListener aOnNoteListener;
    private View appointmentItemView;



    public static AppointmentAdapter getInstance() {
        if(appointmentAdapter == null){
            appointmentAdapter = new AppointmentAdapter();
        }
        return appointmentAdapter;
    }

    public AppointmentAdapter(ArrayList<Appointment> appointments, OnNoteListener onNoteListener) {
        this.appointments = appointments;
        this.aOnNoteListener = onNoteListener;
    }

    public AppointmentAdapter() {
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private  TextView clientName;
        private  TextView timeAppointment;
        private  TextView idAppointment;

        //        Attach listener
        private  OnNoteListener onNoteListenerItem;

        public AppointmentViewHolder(@NonNull final View v, OnNoteListener onNoteListenerItem) {
            super(v);
            idAppointment = v.findViewById(R.id.appointment_id);
            clientName = v.findViewById(R.id.client_cname);
            timeAppointment = v.findViewById(R.id.appointment_time);
            this.onNoteListenerItem = onNoteListenerItem;

            v.setOnClickListener(this);

            }


        @Override
        public void onClick(View view) {
            onNoteListenerItem.onNoteClick(getAdapterPosition());

        }
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        appointmentItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_appointment_item, parent, false);
        return new AppointmentViewHolder(appointmentItemView, aOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.AppointmentViewHolder holder, int position) {
        String name = appointments.get(position).getCustomerName();
        holder.clientName.setText(name);

        String time = appointments.get(position).getTime();
        holder.timeAppointment.setText(time);

        String id = appointments.get(position).getId();
        holder.idAppointment.setText(id);

        if(appointments.get(position).getIsPaid()){
            appointmentItemView.setBackground(appointmentItemView.getResources().getDrawable(R.drawable.border));

        }

    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    //Make Click listener to go to specific appointment
    public interface OnNoteListener {
        void onNoteClick(int position);
    }

}


