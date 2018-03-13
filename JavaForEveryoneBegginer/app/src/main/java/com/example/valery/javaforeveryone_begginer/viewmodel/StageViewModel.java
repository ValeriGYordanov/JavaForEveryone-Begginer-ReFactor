package com.example.valery.javaforeveryone_begginer.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.SystemClock;

import com.example.valery.javaforeveryone_begginer.db.AppDatabase;
import com.example.valery.javaforeveryone_begginer.model.Stage;
import com.example.valery.javaforeveryone_begginer.model.User;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Valery on 3/13/2018.
 */

public class StageViewModel extends AndroidViewModel {

    private final LiveData<List<Stage>> allStages;
    private AppDatabase appDatabase;
    private Stage stage;

    public StageViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());
        allStages = appDatabase.stageDao().getAllStages();
    }

    public Observable<Stage> getStage (String title){
        return Observable.create(emitter -> {
            stage = appDatabase.stageDao().getStageByTitle(title);

            SystemClock.sleep(2000);
            if (stage == null){
                stage = new Stage("", "");
            }

            emitter.onNext(stage);
            emitter.onComplete();
        });
    }
}
