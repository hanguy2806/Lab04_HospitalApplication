package com.example.lab04_hospitalapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab04_hospitalapplication.Models.Nurse;
import com.example.lab04_hospitalapplication.ViewModel.NurseViewModel;

import java.util.ArrayList;

public class LogInActivity extends AppCompatActivity {
private NurseViewModel nurseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loadData();

        Button loginBtn=findViewById(R.id.button_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNurse();
                login();
            }
        });
    }
    private void login(){

    }
    private void loadData(){
    nurseViewModel= ViewModelProviders.of(this).get(NurseViewModel.class);
  //  nurseViewModel.getAllNurses();
    }
    private void addNurse(){
        EditText nurse_name=findViewById(R.id.text_nurse_name);
        EditText password=findViewById(R.id.text_password);
        Nurse nurse=new Nurse(nurse_name.getText().toString(),password.getText().toString());
        nurseViewModel.insert(nurse);
    }
}