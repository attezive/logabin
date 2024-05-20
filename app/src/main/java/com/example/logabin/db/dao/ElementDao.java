package com.example.logabin.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.logabin.db.model.ElementModel;

import java.util.List;

@Dao
public interface ElementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ElementModel elementModel);

    @Query("SELECT * FROM element_models")
    List<ElementModel> getAllElementModels();
}
