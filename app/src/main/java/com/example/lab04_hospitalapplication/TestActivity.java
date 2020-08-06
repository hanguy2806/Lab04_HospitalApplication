package com.example.lab04_hospitalapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lab04_hospitalapplication.Models.Test;
import com.example.lab04_hospitalapplication.ViewModel.TestViewModel;

import java.util.List;

public class TestActivity extends AppCompatActivity {
    public static final int ADD_TEST_REQUEST = 4;
    public static final int EDIT_TEST_REQUEST = 5;

    public TestViewModel testViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        RecyclerView testRV = findViewById(R.id.test_recycler_view);
        testRV.setLayoutManager(new LinearLayoutManager(this));
        testRV.setHasFixedSize(true);

        final TestAdapter adapter = new TestAdapter();
        testRV.setAdapter(adapter);

      testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        testViewModel.getAllTests().observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(List<Test> tests) {
                adapter.setTests(tests);
            }
        });

        testViewModel.getAllTests().observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(List<Test> tests) {
                adapter.setTests(tests);
            }
        });

        adapter.setOnItemClickListener(new TestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Test test) {
                Intent data=new Intent(TestActivity.this, AddTestActivity.class);
                data.putExtra(AddTestActivity.EXTRA_BPH,String.valueOf(test.getBPH()));
                data.putExtra(AddTestActivity.EXTRA_BPL,String.valueOf(test.getBPL()));
                data.putExtra(AddTestActivity.EXTRA_TEMPERATURE,String.valueOf(test.getTemperatre()));
                data.putExtra(AddTestActivity.EXTRA_TEST_ID,test.getTestId());
                data.putExtra(AddTestActivity.EXTRA_NURSE_ID,test.getNurseId());
                data.putExtra(AddTestActivity.EXTRA_PATIENT_ID,String.valueOf(test.getPatientId()));
                startActivityForResult(data,EDIT_TEST_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //get nurse_id and patient_id from previous screens
        SharedPreferences sh=getSharedPreferences("shared preferences", MODE_PRIVATE);
        String nurse_id=sh.getString("nurse_id","no one");
        int patient_id=Integer.valueOf(sh.getString("patient_id","-1"));
        //end of getting nurse and patient id

        if (requestCode == ADD_TEST_REQUEST && resultCode == RESULT_OK) {
            String bph = data.getStringExtra(AddTestActivity.EXTRA_BPH);
            String bpl = data.getStringExtra(AddTestActivity.EXTRA_BPL);
            String temperature = data.getStringExtra(AddTestActivity.EXTRA_TEMPERATURE);
//            String nurseId=data.getStringExtra(AddTestActivity.EXTRA_NURSE_ID);
//            String patientId=data.getStringExtra(AddTestActivity.EXTRA_PATIENT_ID);

            Test test=new Test(Integer.valueOf(bpl),Integer.valueOf(bph),Integer.valueOf(temperature));

            testViewModel.insert(test);
            test.setNurseId(nurse_id);
            test.setPatientId(patient_id);
            Toast.makeText(this, "Test info saved", Toast.LENGTH_SHORT).show();
        }
        //edit handler
        else if(requestCode == EDIT_TEST_REQUEST && resultCode == RESULT_OK){
            int id=data.getIntExtra(AddTestActivity.EXTRA_TEST_ID,-1);
            if(id==-1){
                Toast.makeText(this, "Test info cant be updated", Toast.LENGTH_SHORT).show();
                return ;
            }
            String bph = data.getStringExtra(AddTestActivity.EXTRA_BPH);
            String bpl = data.getStringExtra(AddTestActivity.EXTRA_BPL);
            String temperature = data.getStringExtra(AddTestActivity.EXTRA_TEMPERATURE);
            String nurseId=data.getStringExtra(AddTestActivity.EXTRA_NURSE_ID);
            String patientId=data.getStringExtra(AddTestActivity.EXTRA_PATIENT_ID);

            Test test=new Test(Integer.valueOf(bpl),Integer.valueOf(bph),Integer.valueOf(temperature));
            test.setPatientId(Integer.valueOf(patientId));
            test.setNurseId(nurseId);
            test.setTestId(id);
            testViewModel.update(test);

        }//end else if
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
                Intent intent = new Intent(TestActivity.this, AddTestActivity.class);
                startActivityForResult(intent, ADD_TEST_REQUEST);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}