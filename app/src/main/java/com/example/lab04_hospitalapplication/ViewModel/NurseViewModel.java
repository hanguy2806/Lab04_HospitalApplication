package com.example.lab04_hospitalapplication.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lab04_hospitalapplication.Models.Nurse;
import com.example.lab04_hospitalapplication.Repository.NurseRepository;

import java.util.List;

public class NurseViewModel extends AndroidViewModel {
    private NurseRepository nurseRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Nurse>> allNurses;

    public NurseViewModel( Application application){
        super(application);
        nurseRepository = new NurseRepository(application);
        insertResult=nurseRepository.getInsertResult();
        allNurses = nurseRepository.getAllNurses();
    }
    public void insert(Nurse nurse){
        nurseRepository.insert(nurse);
    }
    public LiveData<Integer> getInsertResult(){ return  insertResult;}
    public LiveData<List<Nurse>> getAllNurses(){
        return allNurses;
    }
}
