package com.example.valery.javaforeveryone_begginer.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.valery.javaforeveryone_begginer.R;
import com.example.valery.javaforeveryone_begginer.model.User;
import com.example.valery.javaforeveryone_begginer.viewmodel.UserViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    SharedPreferences prefs;
    @ViewById(resName = "nav_view")
    NavigationView navigationView;
    @ViewById(resName = "home_layout")
    DrawerLayout drawerLayout;
    @ViewById(resName = "nav_head_username")
    TextView navHeadUsername;
    View navHeaderView;
    ActionBarDrawerToggle toggler;

    UserViewModel mUserViewModel;
    User mUser;

    List<User> allUsersTEST;
    @ViewById
    TextView textViewTEST;
    String text = "";

    String username,password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void logout(){
        SharedPreferences.Editor ed = prefs.edit();
        ed.clear().apply();
        Intent intent = new Intent(this, MainActivity_.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_logout:
                logout();
        }
        return false;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggler.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @AfterViews
    void setUp(){
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        username = prefs.getString("username", "");
        password = prefs.getString("password", "");

        allUsersTEST = new ArrayList<>();

        navHeaderView = navigationView.getHeaderView(0);
        toggler = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_str,R.string.close_str);
        drawerLayout.addDrawerListener(toggler);
        toggler.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("JFE");
        }
        navigationView.setNavigationItemSelectedListener(this);

        navHeadUsername = navHeaderView.findViewById(R.id.nav_head_username);

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        getUser();

        navHeadUsername.setText(username);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Background
    public void getUser(){
        mUser = mUserViewModel.getmUser(username, password);
        mUserViewModel.getmAllUsers().observe(HomeActivity.this, users -> {
            for (User user : users){
                Log.e("USERS" , user.toString() + "");
            }
        });
    }
}
