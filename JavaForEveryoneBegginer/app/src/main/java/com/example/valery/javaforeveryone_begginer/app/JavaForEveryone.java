package com.example.valery.javaforeveryone_begginer.app;

import android.app.Activity;
import android.app.Application;

/**
 * Created by Valery on 3/8/2018.
 */

public class JavaForEveryone extends Application {

    private static JavaForEveryone singleton;

    public JavaForEveryone getInstance(){
        return singleton;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    public static JavaForEveryone get(Activity activity) {
        return (JavaForEveryone) activity.getApplication();
    }
}
