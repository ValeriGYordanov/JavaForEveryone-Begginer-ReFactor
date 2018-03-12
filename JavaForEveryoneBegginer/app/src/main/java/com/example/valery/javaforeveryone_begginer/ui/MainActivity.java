package com.example.valery.javaforeveryone_begginer.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.valery.javaforeveryone_begginer.R;
import com.example.valery.javaforeveryone_begginer.model.User;
import com.example.valery.javaforeveryone_begginer.viewmodel.UserViewModel;
import com.jakewharton.rxbinding2.widget.RxTextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.R.drawable.presence_busy;
import static android.R.drawable.presence_online;


@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    EditText usernameEditText, passwordEditText;
    @ViewById
    Button btn_signin;
    @ViewById
    ProgressBar progressBar_login;

    Drawable mValidField;
    Drawable mInvalidField;

    String username;
    String password;
    UserViewModel model;
    User user;
    SharedPreferences prefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        username = prefs.getString("username", "");
        password = prefs.getString("password", "");
        model = ViewModelProviders.of(this).get(UserViewModel.class);
        if (!username.isEmpty() || !password.isEmpty()){
            switchAct();
        }

    }

    @AfterViews
    public void manageUserInput(){
        mValidField = getDrawable(presence_online);
        mInvalidField = getDrawable(presence_busy);
        setUpUserInput();
    }

    private boolean isValidUsername(CharSequence username){
        return username.toString().matches("[a-zA-Z]+");
    }
    private boolean isValidPassword(CharSequence password){
        return password.toString().matches("^(?=.*\\d).{4,8}$");
    }

    @Click(R.id.btn_signin)
    public void login(){
        progressBar_login.setVisibility(View.VISIBLE);
        model.getUser(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(user1 -> {
                    user = user1;
                    rememberUserAndSwitchAct();})
                .subscribe();
    }

    private void setUpUserInput(){
        //Observe text changes on username until valid!
        Observable<CharSequence> usernameObservable = RxTextView.textChanges(usernameEditText);
        usernameObservable
                .map(this::isValidUsername)
                .subscribe(isValid -> usernameEditText
                        .setCompoundDrawablesRelativeWithIntrinsicBounds(null,null, (isValid ? mValidField : mInvalidField), null));
        //Observe text changes on password until valid!
        Observable<CharSequence> passwordObservable = RxTextView.textChanges(passwordEditText);
        passwordObservable
                .map(this::isValidPassword)
                .subscribe(isValid -> passwordEditText
                        .setCompoundDrawablesRelativeWithIntrinsicBounds(null,null, (isValid ? mValidField : mInvalidField), null));
        //Combine observables and change button visibility if requirements match
        Observable.combineLatest(usernameObservable, passwordObservable, (o1,o2) -> isValidUsername(o1) && isValidPassword(o2))
                .subscribe(isVisible -> {
                    btn_signin.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
                    username = usernameEditText.getText().toString();
                    password = passwordEditText.getText().toString();
                });
    }

    private void switchAct(){
        Intent intent = new Intent(this, HomeActivity_.class);
        startActivity(intent);
        finish();
    }

    private void rememberUserAndSwitchAct(){
        if (user.getUsername().isEmpty() && user.getPassword().isEmpty()){
            Toast.makeText(this, "Wrong Password Inserted", Toast.LENGTH_SHORT).show();
            progressBar_login.setVisibility(View.GONE);
            return;
        }
        progressBar_login.setVisibility(View.GONE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("username", username);
        ed.putString("password", password);
        ed.apply();
        switchAct();
    }

}
