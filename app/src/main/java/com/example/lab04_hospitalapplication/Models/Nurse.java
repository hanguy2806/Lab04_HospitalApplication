package com.example.lab04_hospitalapplication.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="nurse_table")
public class Nurse {
    @PrimaryKey@NonNull
    private String nurseId;
    private String firstName;
    private String lastName;
    private String department;
    private  String password;

    public Nurse(String nurseId, String password){
        this.nurseId=nurseId;
        this.password=password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNurseId() {
        return nurseId;
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

    public String getPassword() {
        return password;
    }
}
