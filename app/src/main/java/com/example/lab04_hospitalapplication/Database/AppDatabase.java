package com.example.lab04_hospitalapplication.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomcallback)
                    .build();
        }
        return INSTANCE;
    }

    //populate database
    private static RoomDatabase.Callback roomcallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private PatientDao patientDao;
        private PopulateDbAsyncTask(AppDatabase db){
            patientDao=db.patientDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            patientDao.insert(new Patient("Title 1", "Decription 1","LaLaLa1",1));
            patientDao.insert(new Patient("Title 2", "Decription 2","LaLaLa2",2));
            return null;
        }
    }
}
