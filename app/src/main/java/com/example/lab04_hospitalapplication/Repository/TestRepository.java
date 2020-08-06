package com.example.lab04_hospitalapplication.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import com.example.lab04_hospitalapplication.Dao.TestDao;
import com.example.lab04_hospitalapplication.Database.AppDatabase;
import com.example.lab04_hospitalapplication.Models.Test;

import java.util.List;

public class TestRepository {
    private  final TestDao testDao;
    private LiveData<List<Test>> testList;

    public TestRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        testDao = db.testDao();
        testList = testDao.getAllTests();
    }

    public LiveData<List<Test>> getAllTests() {
        return testList;
    }

    public void insert(Test test) { new TestRepository.InsertTestAsyncTask(testDao).execute(test);}
    public void update(Test test){ new TestRepository.UpdateTestAsyncTask(testDao).execute(test);}

    private class InsertTestAsyncTask  extends AsyncTask<Test,Void,Void> {
        private TestDao testDao;
        public InsertTestAsyncTask(TestDao testDao) {
            this.testDao=testDao;
        }
        @Override
        protected Void doInBackground(Test... tests) {
            testDao.insert(tests[0]);
            return null;
        }
    }

    private class UpdateTestAsyncTask  extends AsyncTask<Test,Void,Void> {
        private TestDao testDao;
        public UpdateTestAsyncTask(TestDao testDao) {
            this.testDao=testDao;
        }
        @Override
        protected Void doInBackground(Test... tests) {
            testDao.update(tests[0]);
            return null;
        }
    }
}
