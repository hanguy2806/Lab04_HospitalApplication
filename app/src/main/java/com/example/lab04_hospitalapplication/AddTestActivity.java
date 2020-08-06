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

    private EditText editTextBPL;
    private EditText editTextBPH;
    private EditText editTextTemperature;

    private TestViewModel testViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);

        testViewModel= ViewModelProviders.of(this).get(TestViewModel.class);

        init();

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

//        SharedPreferences sh=getSharedPreferences("shared preferences", MODE_PRIVATE);
//        String nurse_id=sh.getString("nurse_id","no one");
//        int patient_id=Integer.valueOf(sh.getString("patient_id","-1"));
//
//        Test test=new Test(Integer.valueOf(bpl),Integer.valueOf(bph),Integer.valueOf(temperature));
//       test.setPatientId(patient_id);
//        test.setNurseId(nurse_id);
//        testViewModel.insert(test);

        Intent data = new Intent();
        data.putExtra(EXTRA_BPH, bph);
        data.putExtra(EXTRA_BPL, bpl);
        data.putExtra(EXTRA_TEMPERATURE, temperature);
        setResult(RESULT_OK, data);
        finish();
    }
}