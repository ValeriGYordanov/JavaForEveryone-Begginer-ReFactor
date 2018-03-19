package com.example.valery.javaforeveryone_begginer.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valery.javaforeveryone_begginer.R;
import com.example.valery.javaforeveryone_begginer.model.User;
import com.example.valery.javaforeveryone_begginer.viewmodel.UserViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    SharedPreferences prefs;
    @ViewById(resName = "nav_view")
    NavigationView navigationView;
    @ViewById(resName = "home_layout")
    DrawerLayout drawerLayout;
    @ViewById(resName = "btn_intro")
    ImageButton btn_intro;
    @ViewById(resName = "btn_loops")
    ImageButton btn_loops;
    @ViewById(resName = "btn_arrays")
    ImageButton btn_arrays;
    @ViewById(resName = "btn_strings")
    ImageButton btn_strings;
    @ViewById(resName = "btn_algorithms")
    ImageButton btn_algorithms;
    @ViewById(resName = "btn_methods")
    ImageButton btn_methods;

    ImageView profilePic;
    TextView navHeadUsername;

    View navHeaderView;
    ActionBarDrawerToggle toggler;

    UserViewModel mUserViewModel;
    User mUser;
    String username,password;

    Fragment fragment;
    FragmentManager frgManager;

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
                break;
            case R.id.nav_profile:
                goToProfile();
                closeDrawerAndFragment();
                break;
            case R.id.nav_home:
                closeDrawerAndFragment();
                break;
        }
        return false;
    }

    private void closeDrawerAndFragment(){
        drawerLayout.closeDrawers();
        if (frgManager != null) {
            int count = frgManager.getBackStackEntryCount();
            if (count > 0) {
                frgManager.popBackStack();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggler.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @AfterViews
    void setUp(){
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        username = prefs.getString("username", "");
        password = prefs.getString("password", "");

        navHeaderView = navigationView.getHeaderView(0);
        toggler = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_str,R.string.close_str);
        drawerLayout.addDrawerListener(toggler);
        toggler.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        navigationView.setNavigationItemSelectedListener(this);

        navHeadUsername = navHeaderView.findViewById(R.id.nav_head_username);
        profilePic = navHeaderView.findViewById(R.id.nav_head_img);

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        frgManager = getSupportFragmentManager();

        btn_algorithms.setOnClickListener(this);
        btn_arrays.setOnClickListener(this);
        btn_intro.setOnClickListener(this);
        btn_loops.setOnClickListener(this);
        btn_strings.setOnClickListener(this);
        btn_methods.setOnClickListener(this);

        getUser();

    }

    @Background
    public void getUser(){
        mUserViewModel.getUser(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(user -> {
                    mUser = user;
                    navHeadUsername.setText(mUser.getUsername());
                    if (mUser.getImageLoc() != null){
                        profilePic.setImageDrawable(mUserViewModel.setImage(mUser.getImageLoc(), this));

                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_intro:
                goToStage("intro");
                break;
            case R.id.btn_algorithms:
                goToStage("algorithms");
                break;
            case R.id.btn_arrays:
                goToStage("arrays");
                break;
            case R.id.btn_loops:
                goToStage("loops");
                break;
            case R.id.btn_strings:
                goToStage("strings");
                break;
            case R.id.btn_methods:
                goToStage("methods");
                break;
        }

    }

    private void goToStage(String stage){
        Bundle fragBndl = new Bundle();
        fragBndl.putString("title", stage);
        fragment = new StageFragment_();
        fragment.setArguments(fragBndl);
        frgManager.beginTransaction()
                .replace(R.id.layout_fragment_place, fragment)
                .addToBackStack(stage)
                .commit();
    }
    private void goToProfile(){
        fragment = new ProfileFragment_();
        frgManager.beginTransaction()
                .replace(R.id.layout_fragment_place, fragment)
                .addToBackStack("profile")
                .commit();
    }

}
