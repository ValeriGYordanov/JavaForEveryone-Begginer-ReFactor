package com.example.valery.javaforeveryone_begginer.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.SystemClock;

import com.example.valery.javaforeveryone_begginer.db.AppDatabase;
import com.example.valery.javaforeveryone_begginer.model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


/**
 * Created by Valery on 3/6/2018.
 */

public class UserViewModel extends AndroidViewModel{

    private final LiveData<List<User>> mAllUsers;
    private User user;
    private AppDatabase appDatabase;


    public UserViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());
        mAllUsers = appDatabase.userModelDAO().getAllUsers();
    }

    public User getmUser(){
        return this.user;
    }

    public Observable<User> getUser (String username, String password){
        return Observable.create(emitter -> {
            user = appDatabase.userModelDAO().getUserByUsername(username);

            SystemClock.sleep(2000);
            if (user == null){
                user = new User(username, password,(byte) 0);
                addUser(user);
            }else if (!user.getPassword().equals(password)){
                user = new User("", "");
                emitter.onNext(user);
                emitter.onComplete();
            }
            emitter.onNext(user);
            emitter.onComplete();
        });
    }

    private void addUser (User user){
        appDatabase.userModelDAO().addUser(user);
    }

//    public void deleteAllTable () {
//        appDatabase.userModelDAO().deleteWholeDB();
//    }


}
