package com.example.valery.javaforeveryone_begginer.ui;


import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valery.javaforeveryone_begginer.R;
import com.example.valery.javaforeveryone_begginer.model.User;
import com.example.valery.javaforeveryone_begginer.viewmodel.UserViewModel;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class ProfileFragment extends Fragment implements View.OnClickListener {

    private View view;

    @ViewById(resName = "menu")
    FloatingActionMenu floatingActionMenu;
    @ViewById(resName = "menu_item_avatar")
    FloatingActionButton changeAvatarBtn;
    @ViewById(resName = "menu_item_password")
    FloatingActionButton changePasswordBtn;
    @ViewById(resName = "username_txt")
    TextView usernameTxt;
    @ViewById(resName = "profile_lay_parent")
    ConstraintLayout parentLayout;


    User user;
    UserViewModel mUserViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);


        return view;
    }


    @AfterViews
    void setUp(){
        changeAvatarBtn.setOnClickListener(this);
        changePasswordBtn.setOnClickListener(this);
        mUserViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        user = mUserViewModel.getmUser();
        usernameTxt.setText(user.getUsername());
        parentLayout.setOnTouchListener((view, motionEvent) -> {
            floatingActionMenu.close(true);
            return false;
        });
    }

    @Override
    public void onClick(View view) {
        if (view == changeAvatarBtn){
            Toast.makeText(getContext(), "Avatar clicked", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Password Clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
