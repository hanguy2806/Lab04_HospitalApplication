package com.example.lab04_hospitalapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lab04_hospitalapplication.Models.Patient;
import com.example.lab04_hospitalapplication.Models.Test;
import com.example.lab04_hospitalapplication.ViewModel.PatientViewModel;
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
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_TEST_REQUEST && resultCode == RESULT_OK) {
            String bph = data.getStringExtra(AddTestActivity.EXTRA_BPH);
            String bpl = data.getStringExtra(AddTestActivity.EXTRA_BPL);
            String temperature = data.getStringExtra(AddTestActivity.EXTRA_TEMPERATURE);
            Test test=new Test(Integer.valueOf(bpl),Integer.valueOf(bph),Integer.valueOf(temperature));
            testViewModel.insert(test);
            Toast.makeText(this, "Patient info saved", Toast.LENGTH_SHORT).show();
        }
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