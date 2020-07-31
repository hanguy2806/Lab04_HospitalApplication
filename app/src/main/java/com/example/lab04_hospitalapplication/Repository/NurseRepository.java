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

    private LiveData<List<Nurse>> nurseList;

    public NurseRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        nurseDao = db.nurseDao();
        nurseList = nurseDao.getAllNurses();
    }

    public LiveData<List<Nurse>> getAllNurses() {
        return nurseList;
    }
    public void insert(Nurse nurse){
        new InsertNurseAsyncTask(nurseDao).execute(nurse);
    }

    private static class InsertNurseAsyncTask extends AsyncTask<Nurse,Void,Void>{
        private NurseDao nurseDao;
        private InsertNurseAsyncTask(NurseDao nurseDao){
            this.nurseDao=nurseDao;
        }
        @Override
        protected Void doInBackground(Nurse... nurses) {
            nurseDao.insert(nurses[0]);
            return null;
        }
    }

}
