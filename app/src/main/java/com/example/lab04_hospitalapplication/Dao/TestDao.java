package com.example.lab04_hospitalapplication.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lab04_hospitalapplication.Models.Nurse;
import com.example.lab04_hospitalapplication.Models.Test;

import java.util.List;
@Dao
public interface TestDao {
    @Insert
    void insert(Test test);
    @Query("SELECT * FROM test_table")
    LiveData<List<Test>> getAllTests();
}
