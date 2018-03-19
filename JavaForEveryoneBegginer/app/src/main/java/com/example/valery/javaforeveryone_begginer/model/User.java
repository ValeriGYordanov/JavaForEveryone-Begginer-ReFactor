package com.example.valery.javaforeveryone_begginer.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


/**
 * Created by Valery on 3/6/2018.
 */

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int _userID;
    private String username;
    private String password;

    private String imageLoc;
    private byte stageID;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.stageID = 0;
    }

    @Ignore
    public User(String username, String password, byte stageID) {
        this.username = username;
        this.password = password;
        this.stageID = stageID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public byte getStageID() {
        return stageID;
    }

    public void setStageID(byte stageID) {
        //Check incoming data
        this.stageID = stageID;
    }
    public String getImageLoc() {
        return imageLoc;
    }

    public void setImageLoc(String imageLoc) {
        this.imageLoc = imageLoc;
    }

    @Override
    public String toString() {
        return "Username : " + this.username + " | Password : " + this.password + " | userID : " + this._userID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User){
            return ((User) obj).username.equals(this.username) && ((User) obj).password.equals(this.password);
        }else {
            return false;
        }
    }
}
