package com.example.lab04_hospitalapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lab04_hospitalapplication.Models.Nurse;
import com.example.lab04_hospitalapplication.Repository.NurseRepository;

import java.util.List;

public class NurseViewModel extends AndroidViewModel {
    private NurseRepository repository;
    private LiveData<List<Nurse>> allNurses;

    public NurseViewModel( Application application){
        super(application);
        repository = new NurseRepository(application);
        allNurses = repository.getAllNurses();
    }
    public void insert(Nurse nurse){
        repository.insert(nurse);
    }
    public LiveData<List<Nurse>> getAllNurses(){
        return allNurses;
    }
}
