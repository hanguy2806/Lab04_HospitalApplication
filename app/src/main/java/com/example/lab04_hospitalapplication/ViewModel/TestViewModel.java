package com.example.lab04_hospitalapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.core.widget.ListViewAutoScrollHelper;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lab04_hospitalapplication.Models.Test;
import com.example.lab04_hospitalapplication.Repository.TestRepository;

import java.util.List;

public class TestViewModel extends AndroidViewModel {
    private TestRepository testRepository;
    private LiveData<List<Test>> allTests;

    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository = new TestRepository(application);
        allTests = testRepository.getAllTests();
    }

    public void insert(Test test) {
        testRepository.insert(test);
    }

    public void update(Test test) {
        testRepository.update(test);
    }

    public LiveData<List<Test>> getAllTests() {
        return allTests;
    }

}
