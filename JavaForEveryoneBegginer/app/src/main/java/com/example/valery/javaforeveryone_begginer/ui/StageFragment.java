package com.example.valery.javaforeveryone_begginer.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.valery.javaforeveryone_begginer.R;
import com.example.valery.javaforeveryone_begginer.model.User;
import com.example.valery.javaforeveryone_begginer.viewmodel.UserViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * Created by Valery on 3/12/2018.
 */
@EFragment
public class StageFragment extends Fragment {

    private View view;
    private String title, text;

    @ViewById(resName = "txt_stages")
    HtmlTextView stageTxt;
    @ViewById(resName = "txt_title")
    TextView titleTxt;

    User user;
    UserViewModel mUserViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.stage_fragment, container, false);

        mUserViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        user = mUserViewModel.getmUser();
        title = getArguments().getString("title");
        Log.e("TAG", mUserViewModel + " : in Fragment");
        Log.e("TAG", user + "");

        return view;

    }

    @AfterViews
    public void setUp(){
        titleTxt.setText(title);
        stageTxt.setHtml("<p style=text-align: center;><strong>Принципи на ООП</strong></p><p style=text-align: center; align=center>Обектно Ориентираното Програмиране се състои от<br /> 4 принципа, които са както следва:<br /> - Капсулация<br /> - Наследяване<br /> - Абстракция<br /> - Полиморфизъм</p><p style=\text-align: center; align=center>Като ще започнем разглеждането от - Капсулация</p><p style=\text-align: center; align=center><strong>Капсулация</strong></p><p style=text-align: center; align=center>Капсулацията можем да я разгледаме като<br /> ограничаване на достъпа до характеристиките и<br /> поведението на нашия обект, така че във външния<br /> свят да се вижда или оперира само с тези атрибути<br /> които не биха повлияли правилното поведение<br /> на нашия обект.</p><p style=text-align: center; align=center><strong>Реализация</strong></p><p style=text-align: center; align=center>Капсулацията се реализира благодарение<br /> на модификатори за достъп:</p><p style=text-align: center; align=center><span style=color: #ff0000;>private</span><br /> -Прави полето/метода видим само за класа.</p><p style=text-align: center; align=center><span style=color: #ff0000;>default</span><br /> -видими за вскички класове в пакета.</p><p style=text-align: center; align=center><span style=color: #ff0000;>protected</span><br /> -видим за всички класове в пакета,<br /> също и наследниците на класа в<br /> проекта.</p><p style=text-align: center; align=center><span style=color: #ff0000;>public</span><br /> -видим за всички в проекта.</p><p style=text-align: center; align=center>Пример:</p><p style=text-align: center;><span style=color: #ff0000;>public class</span> Dog{</p><p style=text-align: center;>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=color: #ff0000;>private</span> <span style=color: #0000ff;>String</span> name;<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=color: #ff0000;>private</span> int age;</p><p style=text-align: center;>}</p><p style=text-align: center; align=center>Това прави променливите &ndash; name и age, видими<br /> само за класа Dog.</p><p style=text-align: center; align=center><strong>Хммм, Ами сега?!<br /> </strong><strong>Setter/Getter</strong></p><p style=text-align: center; align=center>След като сме ограничили достъпа до името и годините<br /> на нашето куче това създава известен проблем, а именно,<br /> как бихме научили на колко години е кучето, ако работим<br /> с него от друг клас, след като полето е заключено..?<br /> На помощ ни идват така наречените Getter-и, <br /> това представляват методи които ни връщат стойността<br /> на дадено поле като в самия метод може да<br /> имплементираме логика за запазване на данните.</p><p style=text-align: center;><span style=color: #ff0000;>protected</span> <span style=color: #0000ff;>String</span> getName(){</p><p style=text-align: center;>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=color: #ff0000;>return</span> name;</p><p style=text-align: center;>}</p><p style=text-align: center; align=center>След като си решихме проблема с виждането<br /> на променливите идва следващия проблем в който<br /> нашето животно не би могло да порасне, тъй като<br /> ние можем да видим годините му но не и да ги променим<br /> С помощта на Setter-и ние бихме могли да променим стойноста<br /> на годините и да вкараме логика за да не се променят<br /> годините със неадекватна стойност която би счупила<br /> нашето приложение. Представете си ако полето е<br /> byte а някой зададе стойност 300 за години на кучето.</p><p style=text-align: center;><span style=color: #ff0000;>protected</span> <span style=color: #000000;>int </span>setAge(int newAge){</p><p style=text-align: center;>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=color: #ff0000;>if</span>(newAge &lt; age || newAge &nbsp;&gt; 1){</p><p style=text-align: center;><span style=color: #ff0000;>return</span>;</p><p style=text-align: center;>}<span style=color: #ff0000;>else</span>{</p><p style=text-align: center;>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; age = newAge;</p><p style=text-align: center;>}</p><p style=text-align: center;>}</p><p style=text-align: center; align=center>Така вече ограничаваме въвеждането на нови<br /> години за кучето, като то може да приема стойност<br /> само с 1 повече от сегашната стойност.</p><p style=text-align: center;>&nbsp;</p><p style=text-align: center; align=center><strong>Ключова дума &ndash; </strong><strong><span style=color: #ff0000;>Static</span><br /> </strong>Ключовата дума static използвана с променлива<br /> би направила въпросната &ndash; обща за всички обекти<br /> създадени от този клас.<br /> Тоест, ако променливата ни age по горе е статична<br /> то всички кучета ще са на една възраст ВИНАГИ.<br /> Същото важи за статичните методи.<br /> Още: Статичните атрибути, могат да се достъпват<br /> дори и да не сме създали обект от даден клас.<br /> Защото те са променливи на класа а не на конкретен обект.</p><p style=text-align: center; align=center><strong>Ключова дума &ndash; </strong><strong><span style=color: #ff0000;>Final</span><br /> </strong>С нея указваме, че дадено поле няма да бъде променяно<br /> до края на нашата програма.<br /> Тоест, ако нашата променлива name е final, и <br /> зададем име &ndash; Пухчо, кучето ни ще остане с<br /> име Пухчо до края на програмата, и не бихме<br /> Могли да го променим по никакъв начин.<br /> <br /> Ако една променлива е едновременно<br /> <span style=color: #ff0000;>static final</span> &ndash; това я прави константа!</p><p style=text-align: center; align=center><strong>Какво е Конструктор*?<br /> </strong>Той се използва при създаване на обект, като<br /> можем да кажем, че той приема някакви входни данни<br /> или да оставим default конструктор който не приема<br /> данни.<br /> Пример:</p><p style=text-align: center;><span style=color: #ff0000;>public &nbsp;class</span> Dog{</p><p style=text-align: center;>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; //Променливите по горе<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Dog(<span style=color: #0000ff;>String</span> dogName, int dogAge){</p><p style=text-align: center;>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; name = dogName;</p><p style=text-align: center;>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; age = dogAge;</p><p style=text-align: center;>}</p><p style=text-align: center;>}</p><p style=text-align: center;>Dog fluffy = <span style=color: #ff0000;>new</span> Dog(&bdquo;Балкан&rdquo;, 5);</p><p style=text-align: center; align=center>Така създаваме куче с името &ndash; &bdquo;Балкан&rdquo; което е<br /> на 5 години. Така още при създаването на обект<br /> можем да изнесем логика и контрол относно данните<br /> с които нашият обект бива създаден.</p><p style=text-align: center; align=center>&nbsp;</p>'");
    }

    @Click(R.id.btn_next_frag)
    public void loadStages(){

    }


}
