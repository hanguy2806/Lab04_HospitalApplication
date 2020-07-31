package com.example.lab04_hospitalapplication.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lab04_hospitalapplication.Models.Nurse;
import com.example.lab04_hospitalapplication.Models.Patient;

import java.util.List;
@Dao
public interface PatientDao {
    @Insert
    void insert(Patient patient);
    @Query("SELECT * FROM  patient_table")
    LiveData<List<Patient>> getAllPatients();
}
