package com.example.lab04_hospitalapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab04_hospitalapplication.Models.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {
    private List<Patient> patients = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_item, parent, false);
        return new PatientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
        Patient pa = patients.get(position);
        holder.textViewFirstName.setText(pa.getFirstName());
        holder.textViewLastName.setText(pa.getLastName());
        holder.textViewroom.setText(String.valueOf(pa.getRoom()));
        holder.textViewdepartment.setText(pa.getDepartment());
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
        notifyDataSetChanged();
    }

    class PatientHolder extends RecyclerView.ViewHolder {
        private TextView textViewFirstName;
        private TextView textViewLastName;
        private TextView textViewdepartment;
        private TextView textViewroom;

        public PatientHolder(View itemView) {
            super(itemView);
            textViewFirstName = itemView.findViewById(R.id.text_view_name);
            textViewdepartment = itemView.findViewById(R.id.text_view_department);
            textViewroom = itemView.findViewById(R.id.text_view_room);
            textViewLastName = itemView.findViewById(R.id.text_view_lastname);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(patients.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Patient patient);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
