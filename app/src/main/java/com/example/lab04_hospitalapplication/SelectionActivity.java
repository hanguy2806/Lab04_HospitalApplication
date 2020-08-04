package com.example.lab04_hospitalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lab04_hospitalapplication.Models.Nurse;
import com.example.lab04_hospitalapplication.Models.Patient;

import java.util.ArrayList;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        ListView listview=(ListView) findViewById(R.id.selection_list_view);
//        ArrayList<String> activities=new ArrayList<>();
//        activities.add("Patient Activity");
//        activities.add("Nurse Activity");
//
//        ArrayAdapter adapter= new ArrayAdapter(this,R.layout.list_view_layout,activities);
//        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getItemAtPosition(position).toString()){
                    case "Patient Activity":
                        startActivity(new Intent(view.getContext(), PatientActivity.class));
                        break;
                    case "Nurse Activity":
                        startActivity(new Intent(view.getContext(), NurseActivity.class));
                        break;
                }
            }
        });
    }
}