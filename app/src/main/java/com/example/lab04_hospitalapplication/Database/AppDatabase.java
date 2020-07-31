package com.example.lab04_hospitalapplication.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.lab04_hospitalapplication.Dao.NurseDao;
import com.example.lab04_hospitalapplication.Dao.PatientDao;
import com.example.lab04_hospitalapplication.Dao.TestDao;
import com.example.lab04_hospitalapplication.Models.Nurse;
import com.example.lab04_hospitalapplication.Models.Patient;
import com.example.lab04_hospitalapplication.Models.Test;

//what is version ??
@Database(entities = {Nurse.class, Patient.class, Test.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "HOSPITAL DATABASE";

    public abstract NurseDao nurseDao();

    public abstract PatientDao patientDao();

    public abstract TestDao testDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }
}
