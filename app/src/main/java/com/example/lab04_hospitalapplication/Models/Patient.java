package com.example.lab04_hospitalapplication.Models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.lab04_hospitalapplication.Models.Nurse;

@Entity(tableName = "patient_table",foreignKeys = @ForeignKey(entity = Nurse.class,
        parentColumns = "nurseId",
        childColumns = "nurseId",
        onDelete = ForeignKey.CASCADE))
public class Patient {
    @PrimaryKey(autoGenerate = true)
    private int patientId;
    private String firstName;
    private String lastName;
    private String department;
    private String nurseId;
    private int room;

    public Patient(String firstName, String lastName, String department, int room) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.room = room;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDepartment() {
        return department;
    }

    public int getRoom() {
        return room;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }
}
