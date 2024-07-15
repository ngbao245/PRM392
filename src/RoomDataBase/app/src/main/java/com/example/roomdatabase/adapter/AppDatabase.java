package com.example.roomdatabase.adapter;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.roomdatabase.dao.PersonDao;
import com.example.roomdatabase.model.Person;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}
