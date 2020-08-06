package com.example.lab04_hospitalapplication.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.lab04_hospitalapplication.Dao.PatientDao;
import com.example.lab04_hospitalapplication.Database.AppDatabase;
import com.example.lab04_hospitalapplication.Models.Patient;

import java.util.List;

public class PatientRepository {
    private  final PatientDao patientDao;
    private LiveData<List<Patient>> patientList;

    public PatientRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        patientDao = db.patientDao();
        patientList = patientDao.getAllPatients();
    }

    public LiveData<List<Patient>> getAllPatients() {
        return patientList;
    }

    public void insert(Patient patient) { new InsertPatientAsyncTask(patientDao).execute(patient);}
    public void update(Patient patient){ new UpdatePatientAsyncTask(patientDao).execute(patient);}

    private class InsertPatientAsyncTask  extends AsyncTask<Patient,Void,Void> {
        private PatientDao patientDao;
        public InsertPatientAsyncTask(PatientDao patientDao) {
           this.patientDao=patientDao;
        }
        @Override
        protected Void doInBackground(Patient... patients) {
            patientDao.insert(patients[0]);
            return null;
        }
    }

    private class UpdatePatientAsyncTask  extends AsyncTask<Patient,Void,Void> {
        private PatientDao patientDao;
        public UpdatePatientAsyncTask(PatientDao patientDao) {
            this.patientDao=patientDao;
        }
        @Override
        protected Void doInBackground(Patient... patients) {
            patientDao.update(patients[0]);
            return null;
        }
    }
}

