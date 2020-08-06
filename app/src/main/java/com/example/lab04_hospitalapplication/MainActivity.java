package com.example.lab04_hospitalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start=findViewById(R.id.btnGetStarted);
        start.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        startClick();
        }
        });
        }
private void startClick(){
        Intent intent=new Intent(this,LogInActivity.class);
        startActivity(intent);
        }

        }