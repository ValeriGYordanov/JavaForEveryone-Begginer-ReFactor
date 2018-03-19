package com.example.valery.javaforeveryone_begginer.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.Toast;

import com.example.valery.javaforeveryone_begginer.db.AppDatabase;
import com.example.valery.javaforeveryone_begginer.model.User;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EReceiver;
import org.reactivestreams.Subscription;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


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

    public void updateUser(User currentUser){
        appDatabase.userModelDAO().updateUser(user);
    }

//    public void deleteAllTable () {
//        appDatabase.userModelDAO().deleteWholeDB();
//    }

    public RoundedBitmapDrawable setImage(String imagePath, Activity activity){

        if (!imagePath.equals(user.getImageLoc())){
            user.setImageLoc(imagePath);

            Observable.just(user)
                    .subscribeOn(Schedulers.io())
                    .subscribe(user1 -> updateUser(user1));
        }

        InputStream inputStream;
        Uri imageUri = Uri.parse(imagePath);

        try {
            inputStream = activity.getContentResolver().openInputStream(imageUri);

            Bitmap image = BitmapFactory.decodeStream(inputStream);

            RoundedBitmapDrawable round = RoundedBitmapDrawableFactory.create(activity.getResources(), image);
            round.setCircular(true);

            return round;
            //this.image.setImageDrawable(round);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(activity, "Снимката НЕ е заредена...", Toast.LENGTH_LONG).show();
            return null;
        }

    }
}
