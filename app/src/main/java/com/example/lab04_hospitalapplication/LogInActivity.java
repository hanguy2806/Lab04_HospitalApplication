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
    ArrayList<Nurse> nurseArrayList = new ArrayList<>();
    NurseViewModel nurseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        nurseViewModel = ViewModelProviders.of(this).get(NurseViewModel.class);

        loadData();

        Button loginBtn = findViewById(R.id.button_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNurse();
                //           login();
                //          startActivity(new Intent(LogInActivity.this, SelectionActivity.class));
            }
        });
    }

    private void login() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(nurseArrayList);
        editor.putString("nurse list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("nurse list", null);
        Type type = new TypeToken<ArrayList<Nurse>>() {
        }.getType();
        nurseArrayList = gson.fromJson(json, type);
        if (nurseArrayList == null) {
            nurseArrayList = new ArrayList<>();
        }
        Toast.makeText(this, "Load data test", Toast.LENGTH_SHORT).show();
    }

    private void addNurse() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        EditText nurse_name = findViewById(R.id.text_nurse_name);
        EditText password = findViewById(R.id.text_password);

        editor.putString("nurse_id", nurse_name.getText().toString());
        editor.commit();
        if (nurse_name.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter id and password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.getText().toString().length() < 8) {
            Toast.makeText(this, "Password must be more than 8 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        Nurse nurse = new Nurse(nurse_name.getText().toString(), password.getText().toString());
        nurseArrayList.add(nurse);
        nurseViewModel.insert(nurse);
//        String output="";
//        for(Nurse n:nurseArrayList){
//            output+="\n"+n.getNurseId();
//        }
//        Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LogInActivity.this, SelectionActivity.class));
    }
}