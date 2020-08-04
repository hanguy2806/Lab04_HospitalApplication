package com.example.lab04_hospitalapplication.Repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lab04_hospitalapplication.Dao.PatientDao;
import com.example.lab04_hospitalapplication.Database.AppDatabase;
import com.example.lab04_hospitalapplication.Models.Patient;

import java.util.List;

public class PatientRepository {
    private final PatientDao patientDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Patient>> patientList;

    public PatientRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        patientDao = db.patientDao();
        patientList = patientDao.getAllPatients();
    }

    public LiveData<List<Patient>> getAllPatients() {
        return patientList;
    }

    public void insert(Patient patient) {
        insertAsync(patient);
    }

    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private void insertAsync(final Patient person) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    patientDao.insert(person);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
}
