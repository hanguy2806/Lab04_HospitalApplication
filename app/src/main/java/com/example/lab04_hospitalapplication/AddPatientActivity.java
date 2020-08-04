package com.example.lab04_hospitalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.lab04_hospitalapplication.Models.Patient;
import com.example.lab04_hospitalapplication.ViewModel.PatientViewModel;

import java.util.List;

public class AddPatientActivity extends AppCompatActivity {
    public static final String EXTRA_FIRST_NAME = "com.example.lab04.EXTRA_FIRST_NAME";
    public static final String EXTRA_LAST_NAME = "com.example.lab04.EXTRA_LAST_NAME";
    public static final String EXTRA_DEPARTMENT = "com.example.lab04.EXTRA_DEPARTMENT";
    public static final String EXTRA_ROOM = "com.example.lab04.EXTRA_ROOM";

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextDepartment;
    private EditText editTextRoom;
    private PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        init();

        Button saveBtn = findViewById(R.id.button_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveBtnClicked();
            }
        });
        Button backBtn = findViewById(R.id.button_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddPatientActivity.this, PatientActivity.class));
            }
        });
    }

    private void init() {
        editTextFirstName = findViewById(R.id.edit_text_patient_fname);
        editTextLastName = findViewById(R.id.edit_text_patient_lname);
        editTextDepartment = findViewById(R.id.edit_text__patient_department);
        editTextRoom = findViewById(R.id.edit_text_patient_room);
    }

    private void SaveBtnClicked() {
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String department = editTextDepartment.getText().toString();
        int room = Integer.valueOf(editTextRoom.getText().toString());
        if (firstName.trim().isEmpty() || lastName.trim().isEmpty() || department.trim().isEmpty()) {
            Toast.makeText(this, "Please insert all information!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_FIRST_NAME, firstName);
        data.putExtra(EXTRA_LAST_NAME, lastName);
        data.putExtra(EXTRA_DEPARTMENT, department);
        data.putExtra(EXTRA_ROOM, room);
        setResult(RESULT_OK, data);
        finish();
    }
}