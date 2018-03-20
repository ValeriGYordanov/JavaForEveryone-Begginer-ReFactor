package com.example.valery.javaforeveryone_begginer.ui;


import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valery.javaforeveryone_begginer.R;
import com.example.valery.javaforeveryone_begginer.model.User;
import com.example.valery.javaforeveryone_begginer.viewmodel.UserViewModel;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

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

    private Uri resultUri;
    User user;
    UserViewModel mUserViewModel;

    boolean isImageUpload = false;

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

//                resultUri = result.getUri();
//                updateUserImage(resultUri.toString());
//                InputStream inputStream;
//
//                try {
//                    inputStream = getActivity().getContentResolver().openInputStream(resultUri);
//
//                    Bitmap image = BitmapFactory.decodeStream(inputStream);
//
//                    RoundedBitmapDrawable round = RoundedBitmapDrawableFactory.create(getResources(), image);
//                    round.setCircular(true);
//
//                    this.image.setImageDrawable(round);
//
//                    isImageUpload = true;
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                    isImageUpload = false;
//                    Toast.makeText(getActivity(), "Снимката НЕ е заредена...", Toast.LENGTH_LONG).show();
//                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(getActivity(), "Нещо се обърка с изрязването...", Toast.LENGTH_SHORT).show();

            }
        }
    }

}
