package com.example.valery.javaforeveryone_begginer.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.valery.javaforeveryone_begginer.model.Stage;
import com.example.valery.javaforeveryone_begginer.model.StageDao;
import com.example.valery.javaforeveryone_begginer.model.User;
import com.example.valery.javaforeveryone_begginer.model.UserDao;
import com.fstyle.library.helper.AssetSQLiteOpenHelperFactory;

/**
 * Created by Valery on 3/6/2018.
 */
@Database(entities = {User.class, Stage.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context ctx) {
        if (instance == null){
//            instance = Room.databaseBuilder(ctx.getApplicationContext(), AppDatabase.class, "appdb.db")
//                    .build();
            instance = Room.databaseBuilder(ctx.getApplicationContext(), AppDatabase.class, "appdb.db")
                    .openHelperFactory(new AssetSQLiteOpenHelperFactory()).build();

        }
        return instance;
    }

    public abstract UserDao userModelDAO();
    public abstract StageDao stageDao();
}
