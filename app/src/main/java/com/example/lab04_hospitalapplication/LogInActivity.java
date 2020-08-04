package com.example.lab04_hospitalapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab04_hospitalapplication.Models.Nurse;
import com.example.lab04_hospitalapplication.ViewModel.NurseViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LogInActivity extends AppCompatActivity {
    private NurseViewModel nurseViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        nurseViewModel = ViewModelProviders.of(this).get(NurseViewModel.class);

        nurseViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer result) {
                if (result == 1) {
                    Toast.makeText(LogInActivity.this, "log in success. Save user", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LogInActivity.this, "log in failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        nurseViewModel.getAllNurses().observe(this, new Observer<List<Nurse>>() {
            @Override
            public void onChanged(List<Nurse> nurses) {
                String output = "Welcome ";
                for (Nurse n : nurses) {
                    output += n.getNurseId() + "\n";
                }
                Toast.makeText(LogInActivity.this, output, Toast.LENGTH_SHORT).show();
            }
        });

        // loadData();

        Button loginBtn = findViewById(R.id.button_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNurse();
                startActivity(new Intent(LogInActivity.this, SelectionActivity.class));
                //    login();
            }
        });
    }

    private void login() {
        addNurse();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(nurseViewModel);
        editor.putString("nurse list", json);
        editor.apply();
        Log.v("LOG IN METHOD", "here");
        Toast.makeText(this, "HERE" + nurseViewModel.getAllNurses().toString(), Toast.LENGTH_SHORT).show();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("nurse list", null);
        Type type = new TypeToken<NurseViewModel>() {
        }.getType();
        nurseViewModel = gson.fromJson(json, type);

        Toast.makeText(this, "DATA LOADING...", Toast.LENGTH_SHORT).show();

        //   nurseViewModel.getAllNurses();
    }

    private void addNurse() {
        EditText nurse_name = findViewById(R.id.text_nurse_name);
        EditText password = findViewById(R.id.text_password);
        Nurse nurse = new Nurse(nurse_name.getText().toString(), password.getText().toString());
//add nurse into nurseViewModel
        nurseViewModel.insert(nurse);
        Toast.makeText(this, "HERE" + nurseViewModel.getAllNurses().toString(), Toast.LENGTH_SHORT).show();

    }
}