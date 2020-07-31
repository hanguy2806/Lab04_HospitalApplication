package com.example.lab04_hospitalapplication.Models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "test_table",foreignKeys = {
        @ForeignKey(
                entity = Nurse.class,
                parentColumns = "nurseId",
                childColumns = "nurseId"
        ),
        @ForeignKey(
                entity = Patient.class,
                parentColumns = "patientId",
                childColumns = "patientId"
        )
})
public class Test {
    @PrimaryKey(autoGenerate = true)
    private int testId;
    private int patientId;
    private int nurseId;
    private int BPL;
    private int BPH;
    private int temperatre;

    public int getPatientId() {
        return patientId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public Test(int BPL, int BPH, int temperatre) {
        this.BPL = BPL;
        this.BPH = BPH;
        this.temperatre = temperatre;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public int getBPL() {
        return BPL;
    }

    public int getBPH() {
        return BPH;
    }

    public int getTemperatre() {
        return temperatre;
    }
}
