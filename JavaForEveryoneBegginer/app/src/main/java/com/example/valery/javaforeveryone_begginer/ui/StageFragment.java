package com.example.valery.javaforeveryone_begginer.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.valery.javaforeveryone_begginer.R;
import com.example.valery.javaforeveryone_begginer.model.User;
import com.example.valery.javaforeveryone_begginer.viewmodel.StageViewModel;
import com.example.valery.javaforeveryone_begginer.viewmodel.UserViewModel;
import com.jakewharton.rxbinding2.widget.RxTextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.reactivestreams.Subscription;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Valery on 3/12/2018.
 */
@EFragment
public class StageFragment extends Fragment {
//    private static final String STAGE_INTRO = "intro";
//    private static final String STAGE_ARRAYS = "arrays";
//    private static final String STAGE_STRINGS = "strings";
//    private static final String STAGE_ALGORITHMS = "algorithms";
//    private static final String STAGE_LOOPS = "loops";

    private View view;
    private String title, text;

    @ViewById(resName = "txt_stages")
    HtmlTextView stageTxt;
    @ViewById(resName = "txt_title")
    TextView titleTxt;
    @ViewById(resName = "btn_next")
    Button btnNext;

    User user;
    UserViewModel mUserViewModel;
    StageViewModel mStageViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.stage_fragment, container, false);

        mUserViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        mStageViewModel = ViewModelProviders.of(getActivity()).get(StageViewModel.class);

        user = mUserViewModel.getmUser();
        title = getArguments().getString("title");
        Log.e("TAG", mUserViewModel + " : in Fragment");
        Log.e("TAG", user + "");

        return view;

    }

    @AfterViews
    public void setUp() {
        switch (title){
            case "intro" :
                titleTxt.setText("Въведение");
                loadText("intro");
                break;
            case "algorithms":
                titleTxt.setText("Алгоритми");
                loadText("algorithms");
                break;
            case "arrays":
                titleTxt.setText("Масиви");
                loadText("arrays");
                break;
            case "loops":
                titleTxt.setText("Цикли");
                loadText("loops");

                break;
            case "strings":
                titleTxt.setText("Strings");
                loadText("strings");
                break;
            case "methods":
                titleTxt.setText("Методи");
                loadText("methods");
        }

    }
    @Click(R.id.btn_next)
    public void popUpTest(){
        //TODO:
        // Pop up the stage test dialog
        Toast.makeText(getActivity(), "Къде напред?!", Toast.LENGTH_SHORT).show();
    }

    private void loadText(String stageTitle){

        mStageViewModel.getStage(stageTitle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stage -> {
                    if (stageTxt != null){
                        stageTxt.setHtml(stage.getText());
                        btnNext.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.FadeIn)
                                .duration(700)
                                .playOn(stageTxt);
                    }
                });
    }

}
