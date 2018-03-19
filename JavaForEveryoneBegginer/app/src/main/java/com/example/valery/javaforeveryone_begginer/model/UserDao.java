package com.example.valery.javaforeveryone_begginer.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.ABORT;

/**
 * Created by Valery on 3/6/2018.
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM User WHERE username = :username")
    User getUserByUsername(String username);

    @Insert(onConflict = ABORT)
    void addUser(User user);

    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);

//    @Query("DELETE FROM User")
//    void deleteWholeDB();

}
