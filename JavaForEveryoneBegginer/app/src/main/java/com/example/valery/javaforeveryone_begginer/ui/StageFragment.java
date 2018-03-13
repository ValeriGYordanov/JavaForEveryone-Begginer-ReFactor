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
import android.widget.Toast;

import com.example.valery.javaforeveryone_begginer.R;
import com.example.valery.javaforeveryone_begginer.model.User;
import com.example.valery.javaforeveryone_begginer.viewmodel.StageViewModel;
import com.example.valery.javaforeveryone_begginer.viewmodel.UserViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Valery on 3/12/2018.
 */
@EFragment
public class StageFragment extends Fragment {
    private static final String STAGE_INTRO = "intro";
    private static final String STAGE_ARRAYS = "arrays";
    private static final String STAGE_STRINGS = "strings";
    private static final String STAGE_ALGORITHMS = "algorithms";
    private static final String STAGE_LOOPS = "loops";

    private View view;
    private String title, text;

    @ViewById(resName = "txt_stages")
    HtmlTextView stageTxt;
    @ViewById(resName = "txt_title")
    TextView titleTxt;

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
                mStageViewModel.getStage("intro")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(stage -> stageTxt.setHtml(stage.getText()));
                //stageTxt.setHtml(INTRO_TXT);
                break;
            case "algorithms":
                titleTxt.setText("Алгоритми");
                break;
            case "arrays":
                titleTxt.setText("Масиви");
                break;
            case "loops":
                stageTxt.setHtml(LOOPS_TXT);
                titleTxt.setText("Цикли");
                break;
            case "strings":
                titleTxt.setText("Strings");
                break;
        }

    }
    @Click(R.id.btn_next)
    public void popUpTest(){
        //TODO:
        // Pop up the stage test dialog
        Toast.makeText(getActivity(), "Къде напред?!", Toast.LENGTH_SHORT).show();
    }


    private static final String INTRO_TXT = "<p style=text-align: center;>Java за всеки е предназначена за хора, <br />които имат голямо желание и мотивация <br />да навлязат в дебрите на програмирането с езика Java.</p><p style=text-align: center;>За да работите с това приложение и да усвоите материала в него, <br />не е нужно да имате предварителни познания по програмиране <br />или висше образование в сферата на математиката <br />или информационните технологии.</p><p style=text-align: center;>За Приложението и ITTalents</p><p style=text-align: center;>Приложението е базирано на книгата - Java за всеки, <br />която е написана от лекторите в ITTalents - Красимир Стоев и Николай Томитов. <br />Приложението се ръководи на базата на учебната програма, с която работят от началото на 2013г. <br />Обучението в ITTalents e интензивно и от най-високо ниво. <br />Статистиката показва, че абсолютно всички завършили курса, започват работа като програмисти! <br />Въведение Преди да започнем, е необходимо да инсталираме софтуер, <br />който ще ни е нужен, за да пишем и изпълним нашите програми. <br />Ще трябва да инсталираме JDK - Java Development Kit. <br />Можем лесно да го намерим в сайта на oracle.com. <br />Ще ни бъде нужна и среда за разработка, <br />за целите предлагаме да изтеглите Eclipse, <br />който е наличен в сайта - eclipse.org. <br />След инсталацията вече сме готови да преминем към реалната ОСНОВА.</p><p style=text-align: center;>Примитивни типове Данни</p><p style=text-align: center;>Какво е променлива <br />Можем да я наречем кутия в която можем да сложим нещо. <br />Кутиите имат размер и вид като ние можем да слагаме различни <br />по големина неща в нашите кутии в зависимост от размера и вида! <br />За какво използваме променливите? <br />Обикновенно за да запазим данни прочетени от потребителя<br /> и да изпълняваме пресмятания с тях <br />те се съхраняват в оперативната памет на компютъра - (РАМ)</p><p style=text-align: center;>Стойности на променливите <br />Ако създадем променлива без да и зададем конкретна стойност<br /> виртуалната машина на Java - JVM <br />ще зададе стойност по подразбиране на тази променлива.</p><p style=text-align: center;>Още за променливите <br />Литерали в Java <br />Литералите представляват стойностите които ние задаваме на нашите променливи!</p><p style=text-align: center;>Целочислени Литерали <br />Ако числата които задаваме на променлива завършват с L <br />то типът на променливата е long, <br />а по подразбиране целочислените литерали са от тип Int.<br />Това е важно когато създаваме променлива която ще съдържа голямо число.</p><p style=text-align: center;>Литерали с плаваща запетая <br />По подразбиране числата с плаваща запетая в Java са double,<br />но ако искаме да използваме float то тогава в края на числото трябва да добавим F.<br />Ако не го направим ще получим грешка,<br />защото float не може да побере големината на double.</p><p style=text-align: center;>Знакови Литерали<br />Задаваме стойност на знаков литерал като зададем символ<br /> и го запишем в единични кавички.</p><p style=text-align: center;>Пример : char x = 'x';</p><p style=text-align: center;>Escape Sequence</p><p style=text-align: center;>Както би станало ако зададен като стойност знака за край на реда?<br />За тази цел в езика са измислени литерали за специални знаци наречени Escape Sequences,<br />които се задават започвайки с наклонена черта - \\.</p><p style=text-align: center;>С командата System.out.print() можем да изведем нашите променливи в конзолата.<br />Като пример System.out.print(5) ще изведе числото 5 в конзолата.</p><p style=text-align: center;>Взимане на стойност от Конзолата <br />Много често се налага началната стойност на някоя променлива<br /> да е взета от потребителя, бази данни или друг източник,<br /> за това на среща имаме конструкцията - Scanner. </p><p style=text-align: center;>Ето пример как работи:</p><p style=text-align: center;>Scanner scan = new Scanner(System.in);</p><p style=text-align: center;>scan.nextInt();</p><p style=text-align: center;>Збележете че в горният код четем int стойност от конзолата, как обаче да прочетем double или byte? <br />Просто заменяме nextInt() с nextDouble(), nextByte() и т.н.</p><p style=text-align: center;>Операции</p><p style=text-align: center;>Върху променливите от целичислен тип можем да извършваме<br /> следните аритметични операции -[+,-,/,*,%]. <br />Като '%' връща остатъка от деление на една променлива с друга.</p><p style=text-align: center;>Сравнение</p><p style=text-align: center;>В Java можем да ползваме следните оператори за сравнение <br />[&gt;,&lt;,==,!=,&lt;=,&gt;=]<br />Които са както следва <br />по - голямо, по -малко, равно, различни, по - малко или равно и по голямо или равно.</p><p style=text-align: center;>Логически оператори</p><p style=text-align: center;>Върху променливите от булев тип(boolean) можем да ползваме следните логически оператори</p><p style=text-align: center;>[&amp;&amp; - и, || - или, ^ - изключващо или, ! - отрицание].</p><p style=text-align: center;>Операции</p><p style=text-align: center;>Оператори за присвояване Освен оператор = в Java има и други оператори за присвояване,<br />които всъщност за комбинация от = и аритметичен израз - Пример: +=. <br />Това ни улеснява като сакратява записването на част от операциите <br />Оператори за увеличаване и намаляне с единица <br />Или така наречените Постинкремент/Преинкремент <br />и Постдекремент/Предекремент.</p>";



    private static final String LOOPS_TXT = "<p style=text-align: center;><strong>Условни Оператори</strong></p><p style=text-align: center;>B Java съществуват 3 типа условни оператори <br />като всеки се използва в зависимост от желаната функционалност.<br />Типове - [ if , if/else ], [switch/case] [?: ].<br />Като можем да ги групираме така:</p><p style=text-align: center;>[Условен Оператор]<br />[Оператор за множество условия]<br />[Оператор за сравнение] </p><p style=text-align: left; padding-left: 300px;><em>if(условието е вярно){ </em><br /><em>&nbsp; &nbsp;изпълни този код </em><br /><em>} else { </em><br /><em>&nbsp; &nbsp;изпълни този код</em><br /><em> }</em></p><p style=text-align: left; padding-left: 300px;><br /><em>switch(условие){ </em><br /><em>&nbsp; &nbsp;case резултат: Изпълни това; </em><br /><em>&nbsp; &nbsp;case резултат: Изпълни това; </em><br /><em>}</em></p><p style=text-align: center; padding-left: 30px;>&nbsp;</p><p style=text-align: center;><strong>If/Else Statement</strong></p><p style=text-align: center;>Тъй като if/else е най-използваната конструкция<br />даваме пример за неговото използване.&nbsp;</p><p style=text-align: left; padding-left: 270px;>public class Snippets { <br />&nbsp; &nbsp;рubliс static void main(String[] args) { <br />&nbsp; &nbsp; &nbsp; int x=5; <br />&nbsp; &nbsp; &nbsp; int y=4; <br />&nbsp; &nbsp; &nbsp; if(x==++y){ <br />&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;System.out.println(Те са равни); <br />&nbsp; &nbsp; &nbsp; }else { <br />&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;System.out.println(Не са равни); <br />&nbsp; &nbsp;} <br />}</p><p style=text-align: left; padding-left: 270px;>Следния код би принтирал Те са равни в конзолата.</p><p style=text-align: left; padding-left: 270px;>&nbsp;</p><p style=text-align: center;><strong>Цикли</strong><br />Цикълът е конструкция която ни позволява да изпълним<br />даден блок от код няколко на брой пъти.</p><p style=text-align: center;><br /><strong>Видове Цикли: </strong></p><p style=text-align: center;><strong>1</strong> while(условието е вярно){ изпълнявай кода докато условието е вярно }</p><p style=text-align: center;><strong>2</strong> for(int i= 0; i&lt; 0; i++)<br />Цикълът for можем да си го преведем по следният начин <br />За променлива i която започва от 0;<br /> докато i стане = на 0<br /> извършвай кода <br />и след това увеличи i c 1;</p><p style=text-align: center;><strong>3</strong> do{ Изпълнявай парчето код докато }<br />while(условието е вярно)</p><p style=text-align: center;>&nbsp;</p><p style=text-align: center;><strong>Вложени цикли</strong></p><p style=text-align: center;>Вложените цикли представляват цикъл написан в тялото на друг цикъл.<br />Защо бихме използвали вложени цикли?</p><p style=text-align: center;>Пример: За да преминем през всички данни в двумерен масив<br /> ще са ни нужни 2 цикъла като единият да върти <br />колоните а другият да върти редовете.</p><p style=text-align: left; padding-left: 270px;><em>рubliс class Snippets { </em><br /><em>&nbsp; &nbsp;рubliс static void main(String[] args) { </em><br /><em>&nbsp; &nbsp; &nbsp; int [][] twoDimArr = {{1,2,3},{4,5,6},{7,8,9}}; </em><br /><em>&nbsp; &nbsp; &nbsp; for (int i= 0; i&lt; twoDimArr.length; i++) { </em><br /><em>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;for (int j= 0; j&lt; twoDimArr[i].length; j++) { </em><br /><em>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; System.out.print(twoDimArr[i][j]); </em><br /><em>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;} </em><br /><em>&nbsp; &nbsp; &nbsp; } </em><br /><em>&nbsp; &nbsp; } </em><br /><em>}</em></p><p style=text-align: center;>Със следния код <br />итерираме в двумерен масив.</p><p style=text-align: center;>&nbsp;</p><p style=text-align: center;><strong>Ключови думи</strong></p><p style=text-align: center;><br />Често ни се случва да имаме<br />нужда да спрем цикъл ако<br />настъпи друго условие освен<br />зададеното в условието на<br />цикъла, тогава ние можем да го<br />спрем с ключовата дума - break.<br />В друг случай имаме нужда да<br />пропускаме определени данни,<br />но цикъла ни да продължи или с<br />други думи, ако принтираме<br />двумерен масив с числови<br />стойности и искаме да<br />изпринтираме само делимите на<br />2 числа бихме използвали<br />ключовата дума - continue.</p><p style=text-align: center;>Така цикълът<br />ще се върти<br />докато х стане 5</p><p>&nbsp;</p><p>&nbsp;</p>";

}
