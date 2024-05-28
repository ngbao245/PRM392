package com.example.lab4_2;

import android.os.Parcelable;

import java.io.Serializable;

public class Student implements Serializable {
    private String Name;
    private int Age;
    private String Major;
    private boolean isGraduate;

    public Student()  {
    }

    public Student(String name, int age, String major, boolean isGraduate) {
        Name = name;
        Age = age;
        Major = major;
        this.isGraduate = isGraduate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public boolean isGraduate() {
        return isGraduate;
    }

    public void setGraduate(boolean graduate) {
        isGraduate = graduate;
    }
}
