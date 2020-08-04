package com.example.lab04_hospitalapplication.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.core.widget.ListViewAutoScrollHelper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lab04_hospitalapplication.Dao.NurseDao;
import com.example.lab04_hospitalapplication.Database.AppDatabase;
import com.example.lab04_hospitalapplication.Models.Nurse;

import java.util.List;

public class NurseRepository {
    private final NurseDao nurseDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Nurse>> nurseList;

    public NurseRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        nurseDao = db.nurseDao();
        nurseList = nurseDao.getAllNurses();
    }

    public LiveData<List<Nurse>> getAllNurses() {
        return nurseList;
    }

    public void insert(Nurse nurse) {
        insertAsync(nurse);
    }

    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private void insertAsync(final Nurse person) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    nurseDao.insert(person);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
}
