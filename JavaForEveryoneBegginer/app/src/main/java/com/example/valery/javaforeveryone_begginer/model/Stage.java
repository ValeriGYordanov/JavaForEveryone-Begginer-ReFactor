package com.example.valery.javaforeveryone_begginer.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Valery on 3/12/2018.
 */
@Entity
public class Stage {
    @PrimaryKey(autoGenerate = true)
    public int stageID;
    private String title;
    private String text;

    public Stage(String title, String text) {
        this.title = title;
        this.text = text;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
        return this.title.equals(obj.toString());
    }

    @Override
    public String toString() {
        return this.title;
    }
}
