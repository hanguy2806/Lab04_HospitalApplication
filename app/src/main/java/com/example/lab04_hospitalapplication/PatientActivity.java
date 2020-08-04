package com.example.lab04_hospitalapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lab04_hospitalapplication.Models.Patient;
import com.example.lab04_hospitalapplication.ViewModel.PatientViewModel;

import java.util.List;

public class PatientActivity extends AppCompatActivity {
    public static final int ADD_PATIENT_REQUEST=1;
    private PatientViewModel patientViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        //init view model
        patientViewModel= ViewModelProviders.of(this).get(PatientViewModel.class);

        RecyclerView patientRV=findViewById(R.id.patient_recycler_view);
        patientRV.setLayoutManager(new LinearLayoutManager(this));
        patientRV.setHasFixedSize(true);

        final PatientAdapter adapter=new PatientAdapter();
        patientRV.setAdapter(adapter);
        //init view model
        patientViewModel= ViewModelProviders.of(this).get(PatientViewModel.class);
        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(@NonNull List<Patient> patients) {
                Toast.makeText(PatientActivity.this, "PATIENT VIEW MODEL CHANGED", Toast.LENGTH_SHORT).show();
                adapter.setPatients(patients);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_patient_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_patient:
                startActivity(new Intent(this, AddPatientActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("CHECK PATIENT ACT", "HERE WE GO");
        if (requestCode == ADD_PATIENT_REQUEST && resultCode == RESULT_OK) {
            String firstName = data.getStringExtra(AddPatientActivity.EXTRA_FIRST_NAME);
            String lastName = data.getStringExtra(AddPatientActivity.EXTRA_LAST_NAME);
            String department = data.getStringExtra(AddPatientActivity.EXTRA_DEPARTMENT);
            int room = data.getIntExtra(AddPatientActivity.EXTRA_ROOM, 1);
            Patient p = new Patient(firstName, lastName, department, room);
            patientViewModel.insert(p);
            Toast.makeText(this, "Patient's info saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Patient's info not saved", Toast.LENGTH_SHORT).show();
        }
    }
}