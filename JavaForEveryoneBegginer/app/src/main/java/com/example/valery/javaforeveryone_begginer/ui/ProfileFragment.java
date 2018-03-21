package com.example.valery.javaforeveryone_begginer.ui;


import android.app.Activity;
import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valery.javaforeveryone_begginer.R;
import com.example.valery.javaforeveryone_begginer.model.User;
import com.example.valery.javaforeveryone_begginer.viewmodel.UserViewModel;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.jakewharton.rxbinding2.view.RxView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.concurrent.TimeUnit;

@EFragment
public class ProfileFragment extends Fragment implements View.OnClickListener {

    public static final int GALLERY_REQUEST = 30;
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
    @ViewById(resName = "profile_pic")
    ImageView image;

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
        user = mUserViewModel.getCurrentUser();
        usernameTxt.setText(user.getUsername());
        parentLayout.setOnTouchListener((view, motionEvent) -> {
            floatingActionMenu.close(true);
            return false;
        });

        if (user.getImageLoc() != null){
            image.setImageDrawable(mUserViewModel.getRoundImageFrom(user.getImageLoc(), this.getActivity()));
        }
    }

    @Override
    public void onClick(View view) {
        if (view == changeAvatarBtn){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_REQUEST);
        }else{
            Toast.makeText(getContext(), "Password Clicked", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_password_change, null);
            EditText oldPass,newPass,newPassAgain;
            Button changeBtn;

            oldPass = dialogView.findViewById(R.id.old_pass);
            newPass = dialogView.findViewById(R.id.new_pass);
            newPassAgain = dialogView.findViewById(R.id.new_pass_again);
            changeBtn = dialogView.findViewById(R.id.change_btn);

            mBuilder.setView(dialogView);
            AlertDialog dialog = mBuilder.create();
            dialog.show();

            RxView.clicks(changeBtn)
                    .throttleFirst(2, TimeUnit.SECONDS)
                    .subscribe(o ->
                            {
                                String oldPassStr,newPassStr,newPassAgainStr;
                                oldPassStr = oldPass.getText().toString();
                                newPassStr = newPass.getText().toString();
                                newPassAgainStr = newPassAgain.getText().toString();

                                if (oldPassStr.isEmpty() || newPassStr.isEmpty() || newPassAgainStr.isEmpty()) createToast("Попълнете полетата");
                                else if (oldPassStr.equals(newPassStr)) createToast("Старата и новата парола съвпадат!");
                                else if (!newPassStr.equals(newPassAgainStr)) createToast("Новите пароли не съвпада");
                                else if (!oldPassStr.equals(user.getPassword())) createToast("Вашата текуща парола не съвпада");
                                else if (user.setPassword(newPassStr)){
                                    createToast("Паролата е сменена");
                                    mUserViewModel.updateUser(user);
                                }else  createToast("Паролата трябва да е от 4 до 8 символа \n и поне една цифра");

                            }
                    );

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == Activity.RESULT_OK) {
            Uri imageUri = data.getData();
            Intent intent = CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setCropShape(CropImageView.CropShape.OVAL)
                    .setAspectRatio(1, 1)
                    .setMinCropResultSize(100, 100)
                    .setMaxCropResultSize(2660, 2660)
                    .getIntent(getContext());
            startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == getActivity().RESULT_OK) {
                this.image.setImageDrawable(mUserViewModel.getRoundImageFrom(result.getUri().toString(), getActivity()));
                getActivity().recreate();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(getActivity(), "Нещо се обърка с изрязването...", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void createToast(String text){
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

}
