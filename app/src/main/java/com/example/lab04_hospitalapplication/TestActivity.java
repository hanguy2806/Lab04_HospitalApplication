package com.example.lab04_hospitalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class TestActivity extends AppCompatActivity {
    public static final int ADD_TEST_REQUEST = 1;
    public static final int EDIT_TEST_REQUEST = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
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