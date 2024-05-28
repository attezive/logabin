package com.example.logabin.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.logabin.db.dao.EducationDao;
import com.example.logabin.db.model.EducationModel;

@Database(entities = {EducationModel.class}, version = 1)
public abstract class EducationDatabase extends RoomDatabase {
    public abstract EducationDao educationDao();
}
