package com.example.valery.javaforeveryone_begginer.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.valery.javaforeveryone_begginer.model.User;
import com.example.valery.javaforeveryone_begginer.model.UserDao;

/**
 * Created by Valery on 3/6/2018.
 */
@Database(entities = User.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context ctx) {
        if (instance == null){
            instance = Room.databaseBuilder(ctx.getApplicationContext(), AppDatabase.class, "users_db")
                    .build();
        }
        return instance;
    }

    public abstract UserDao userModelDAO();
    //public abstract StageDao stageDao(); ADD IT IN THE ANNOTATIONS!!!
}
