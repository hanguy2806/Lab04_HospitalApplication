package com.example.lab04_hospitalapplication.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lab04_hospitalapplication.Dao.TestDao;
import com.example.lab04_hospitalapplication.Database.AppDatabase;
import com.example.lab04_hospitalapplication.Models.Test;

import java.util.List;

public class TestRepository {
    private final TestDao testDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Test>> testList;

    public TestRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        testDao = db.testDao();
        testList = testDao.getAllTests();
    }

    public LiveData<List<Test>> getAllTests() {
        return testList;
    }

    public void insert(Test test) {
        insertAsync(test);
    }

    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private void insertAsync(final Test test) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testDao.insert(test);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
}
