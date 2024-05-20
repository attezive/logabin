package com.example.logabin.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.logabin.db.dao.ElementDao;
import com.example.logabin.db.model.ElementModel;

@Database(entities = {ElementModel.class}, version = 2)
public abstract class LocalDatabase extends RoomDatabase {
    public abstract ElementDao elementDao();
}
