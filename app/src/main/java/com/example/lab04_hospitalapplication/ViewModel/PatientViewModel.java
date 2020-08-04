package com.example.lab04_hospitalapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lab04_hospitalapplication.Models.Patient;
import com.example.lab04_hospitalapplication.Repository.PatientRepository;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {
    private PatientRepository patientRepository;
   // private LiveData<Integer> insertResult;
    private LiveData<List<Patient>> allPatients;

    public PatientViewModel(@NonNull Application application){
        super(application);
        patientRepository=new PatientRepository(application);
      //  insertResult=patientRepository.getInsertResult();
        allPatients=patientRepository.getAllPatients();
    }
    public void insert(Patient patient){ patientRepository.insert(patient);}

 //   public LiveData<Integer> getInsertResult(){ return insertResult;}

  //  public void update(Patient patient){ patientRepository.update(patient)};
    public LiveData<List<Patient>> getAllPatients(){ return allPatients;}

}
