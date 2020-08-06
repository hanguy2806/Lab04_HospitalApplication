package com.example.lab04_hospitalapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.lab04_hospitalapplication.Models.Patient;
import com.example.lab04_hospitalapplication.ViewModel.PatientViewModel;

import java.util.List;

public class PatientActivity extends AppCompatActivity {
    public static final int ADD_PATIENT_REQUEST = 1;
    public static final int EDIT_PATIENT_REQUEST = 2;

    private PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        RecyclerView patientRV = findViewById(R.id.patient_recycler_view);
        patientRV.setLayoutManager(new LinearLayoutManager(this));
        patientRV.setHasFixedSize(true);

        final PatientAdapter adapter = new PatientAdapter();
        patientRV.setAdapter(adapter);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                adapter.setPatients(patients);
            }
        });
        adapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Patient patient) {
                Toast.makeText(PatientActivity.this, patient.getFirstName(), Toast.LENGTH_SHORT).show();
                Intent data = new Intent(PatientActivity.this, AddPatientActivity.class);
                data.putExtra(AddPatientActivity.EXTRA_ID, patient.getPatientId());
                data.putExtra(AddPatientActivity.EXTRA_FNAME, patient.getFirstName());
                data.putExtra(AddPatientActivity.EXTRA_LNAME, patient.getLastName());
                data.putExtra(AddPatientActivity.EXTRA_DEPARTMENT, patient.getDepartment());
                data.putExtra(AddPatientActivity.EXTRA_ROOM, patient.getRoom());
                data.putExtra(AddPatientActivity.EXTRA_NURSEID, patient.getNurseId());
                startActivityForResult(data, EDIT_PATIENT_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PATIENT_REQUEST && resultCode == RESULT_OK) {
            String firstName = data.getStringExtra(AddPatientActivity.EXTRA_FNAME);
            String lastName = data.getStringExtra(AddPatientActivity.EXTRA_LNAME);
            String department = data.getStringExtra(AddPatientActivity.EXTRA_DEPARTMENT);
            int room = data.getIntExtra(AddPatientActivity.EXTRA_ROOM, 1);
            String nurseId=data.getStringExtra(AddPatientActivity.EXTRA_NURSEID);

            Patient p = new Patient(firstName, lastName, department, room);
            p.setNurseId(nurseId);
            patientViewModel.insert(p);
            Toast.makeText(this, "Patient info saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_PATIENT_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddPatientActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Patient info cant be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String firstName = data.getStringExtra(AddPatientActivity.EXTRA_FNAME);
            String lastName = data.getStringExtra(AddPatientActivity.EXTRA_LNAME);
            String department = data.getStringExtra(AddPatientActivity.EXTRA_DEPARTMENT);
            int room = data.getIntExtra(AddPatientActivity.EXTRA_ROOM, 1);
            String nurseId=data.getStringExtra(AddPatientActivity.EXTRA_NURSEID);
            Patient p = new Patient(firstName, lastName, department, room);
            // p.setNurseId(nurseId);
            p.setPatientId(id);
            patientViewModel.update(p);
            Toast.makeText(this, "Patient info updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Patient info not saved", Toast.LENGTH_SHORT).show();
        }
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
                Intent intent = new Intent(PatientActivity.this, AddPatientActivity.class);
                startActivityForResult(intent, ADD_PATIENT_REQUEST);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}