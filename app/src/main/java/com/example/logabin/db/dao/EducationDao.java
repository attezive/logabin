package com.example.logabin.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.logabin.db.model.EducationModel;

import java.util.List;

@Dao
public interface EducationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EducationModel educationModel);

    @Query("SELECT * FROM education_models")
    List<EducationModel> getAllEducationModels();
}
