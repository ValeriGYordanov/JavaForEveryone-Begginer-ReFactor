//package com.example.valery.javaforeveryone_begginer.model;
//
//import android.arch.lifecycle.LiveData;
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Delete;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.Query;
//
//import java.util.List;
//
//import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
//
///**
// * Created by Valery on 3/12/2018.
// */
//@Dao
//public interface StageDAO {
//
//    @Query("SELECT * FROM Stage")
//    LiveData<List<Stage>> getAllStages();
//
//    @Query("SELECT * FROM User WHERE title = :title")
//    User getStageByTitle(String title);
//
//    @Insert(onConflict = REPLACE)
//    void addStage(Stage stage);
//
//    @Delete
//    void deleteStage(Stage stage);
//}
