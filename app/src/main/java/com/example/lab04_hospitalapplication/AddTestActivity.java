package com.example.lab04_hospitalapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab04_hospitalapplication.Models.Test;
import com.example.lab04_hospitalapplication.ViewModel.TestViewModel;

public class AddTestActivity extends AppCompatActivity {
    public static final String EXTRA_TEST_ID = "com.example.lab04_hospitalapplication.TEST_ID";
    public static final String EXTRA_BPL = "com.example.lab04_hospitalapplication.TEST_BPL";
    public static final String EXTRA_BPH = "com.example.lab04_hospitalapplication.TEST_BPH";
    public static final String EXTRA_TEMPERATURE = "com.example.lab04_hospitalapplication.TEST_TEMPERATURE";
    public static final String EXTRA_PATIENT_ID = "com.example.lab04_hospitalapplication.PATIENT_ID";
    public static final String EXTRA_NURSE_ID = "com.example.lab04_hospitalapplication.NURSE_ID";

    private EditText editTextBPL;
    private EditText editTextBPH;
    private EditText editTextTemperature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);

        init();
        //for view or update Test
        Intent intent=getIntent();
        if(intent.hasExtra(EXTRA_TEST_ID)){
            editTextTemperature.setText(intent.getStringExtra(EXTRA_TEMPERATURE));
            editTextBPL.setText(intent.getStringExtra(EXTRA_BPL));
            editTextBPH.setText(intent.getStringExtra(EXTRA_BPH));
        }

        Button saveBtn = findViewById(R.id.button_save_test);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveClick();
            }
        });
    }

    private void init() {
        editTextBPH = findViewById(R.id.edit_text_test_BPH);
        editTextBPL = findViewById(R.id.edit_text_test_BHL);
        editTextTemperature = findViewById(R.id.edit_text_test_temperature);
    }

    private void saveClick() {
        String bph = editTextBPH.getText().toString();
        String bpl = editTextBPL.getText().toString();
        String temperature = editTextTemperature.getText().toString();

        Intent data = new Intent();
        data.putExtra(EXTRA_BPH, bph);
        data.putExtra(EXTRA_BPL, bpl);
        data.putExtra(EXTRA_TEMPERATURE, temperature);
        data.putExtra(EXTRA_PATIENT_ID,getIntent().getStringExtra(EXTRA_PATIENT_ID));
        data.putExtra(EXTRA_NURSE_ID, getIntent().getStringExtra(EXTRA_NURSE_ID));
        //check test id is created or not
        int id=getIntent().getIntExtra(EXTRA_TEST_ID,-1);
        if(id != -1){
            data.putExtra(EXTRA_TEST_ID,id);
        }
        //if not,then move on
        setResult(RESULT_OK, data);
        finish();
    }
}