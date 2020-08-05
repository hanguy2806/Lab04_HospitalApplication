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
    public static final String EXTRA_ID = "com.example.lab04_hospitalapplication.ID";
    public static final String EXTRA_FNAME = "com.example.lab04_hospitalapplication.FIRST_NAME";
    public static final String EXTRA_LNAME = "com.example.lab04_hospitalapplication.LAST_NAME";
    public static final String EXTRA_DEPARTMENT = "com.example.lab04_hospitalapplication.DEPARTMENT";
    public static final String EXTRA_ROOM = "com.example.lab04_hospitalapplication.ROOM";

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextDepartment;
    private EditText editTextRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        init();

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Patient Information");
            editTextFirstName.setText(intent.getStringExtra(EXTRA_FNAME));
            editTextLastName.setText(intent.getStringExtra(EXTRA_LNAME));
            editTextDepartment.setText(intent.getStringExtra(EXTRA_DEPARTMENT));
            editTextRoom.setText(intent.getIntExtra(EXTRA_ROOM, 1));
        } else {
            setTitle("Add Patient Information");
        }
        //back button
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
        editTextDepartment = findViewById(R.id.edit_text_patient_department);
        editTextRoom = findViewById(R.id.edit_text_patient_room);
    }

    public void saveClick(View v) {
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String department = editTextDepartment.getText().toString();
        int room = Integer.valueOf(editTextRoom.getText().toString());
        if (firstName.trim().isEmpty() || lastName.trim().isEmpty() || department.trim().isEmpty() || editTextRoom.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please insert all information!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_FNAME, firstName);
        data.putExtra(EXTRA_LNAME, lastName);
        data.putExtra(EXTRA_DEPARTMENT, department);
        data.putExtra(EXTRA_ROOM, room);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }
}