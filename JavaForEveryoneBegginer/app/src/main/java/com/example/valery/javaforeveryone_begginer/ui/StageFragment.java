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
    public void setUp() {
        titleTxt.setText(title);
        String intro = "<p style=\"text-align: center;>\"Java за всеки\" е предназначена за хора, <br />които имат голямо желание и мотивация <br />да навлязат в дебрите на програмирането с езика Java.</p>\n" +
                "<p style=\"text-align: center;\">За да работите с това приложение и да усвоите материала в него, <br />не е нужно да имате предварителни познания по програмиране <br />или висше образование в сферата на математиката <br />или информационните технологии.</p>\n" +
                "<p style=\"text-align: center;\">За Приложението и ITTalents</p>\n" +
                "<p style=\"text-align: center;\">Приложението е базирано на книгата - \"Java за всеки\", <br />която е написана от лекторите в ITTalents - Красимир Стоев и Николай Томитов. <br />Приложението се ръководи на базата на учебната програма, с която работят от началото на 2013г. <br />Обучението в ITTalents e интензивно и от най-високо ниво. <br />Статистиката показва, че абсолютно всички завършили курса, започват работа като програмисти! <br />Въведение Преди да започнем, е необходимо да инсталираме софтуер, <br />който ще ни е нужен, за да пишем и изпълним нашите програми. <br />Ще трябва да инсталираме JDK - Java Development Kit. <br />Можем лесно да го намерим в сайта на oracle.com. <br />Ще ни бъде нужна и среда за разработка, <br />за целите предлагаме да изтеглите Eclipse, <br />който е наличен в сайта - eclipse.org. <br />След инсталацията вече сме готови да преминем към реалната ОСНОВА.</p>\n" +
                "<p style=\"text-align: center;\">Примитивни типове Данни</p>\n" +
                "<p style=\"text-align: center;\">Какво е променлива <br />Можем да я наречем кутия в която можем да сложим нещо. <br />Кутиите имат размер и вид като ние можем да слагаме различни <br />по големина неща в нашите кутии в зависимост от размера и вида! <br />За какво използваме променливите? <br />Обикновенно за да запазим данни прочетени от потребителя<br /> и да изпълняваме пресмятания с тях <br />те се съхраняват в оперативната памет на компютъра - (РАМ)</p>\n" +
                "<p style=\"text-align: center;\">Стойности на променливите <br />Ако създадем променлива без да и зададем конкретна стойност<br /> виртуалната машина на Java - JVM <br />ще зададе стойност по подразбиране на тази променлива.</p>\n" +
                "<p style=\"text-align: center;\">Още за променливите <br />Литерали в Java <br />Литералите представляват стойностите които ние задаваме на нашите променливи!</p>\n" +
                "<p style=\"text-align: center;\">Целочислени Литерали <br />Ако числата които задаваме на променлива завършват с \"L\" <br />то типът на променливата е long, <br />а по подразбиране целочислените литерали са от тип Int.<br />Това е важно когато създаваме променлива която ще съдържа голямо число.</p>\n" +
                "<p style=\"text-align: center;\">Литерали с плаваща запетая <br />По подразбиране числата с плаваща запетая в Java са double,<br />но ако искаме да използваме float то тогава в края на числото трябва да добавим \"F\".<br />Ако не го направим ще получим грешка,<br />защото float не може да побере големината на double.</p>\n" +
                "<p style=\"text-align: center;\">Знакови Литерали<br />Задаваме стойност на знаков литерал като зададем символ<br /> и го запишем в единични кавички.</p>\n" +
                "<p style=\"text-align: center;\">Пример : char x = 'x';</p>\n" +
                "<p style=\"text-align: center;\">Escape Sequence</p>\n" +
                "<p style=\"text-align: center;\">Както би станало ако зададен като стойност знака за край на реда?<br />За тази цел в езика са измислени литерали за специални знаци наречени Escape Sequences,<br />които се задават започвайки с наклонена черта - \"\\\".</p>\n" +
                "<p style=\"text-align: center;\">С командата System.out.print() можем да изведем нашите променливи в конзолата.<br />Като пример System.out.print(5) ще изведе числото 5 в конзолата.</p>\n" +
                "<p style=\"text-align: center;\">Взимане на стойност от Конзолата <br />Много често се налага началната стойност на някоя променлива<br /> да е взета от потребителя, бази данни или друг източник,<br /> за това на среща имаме конструкцията - Scanner. </p>\n" +
                "<p style=\"text-align: center;\">Ето пример как работи:</p>\n" +
                "<p style=\"text-align: center;\">Scanner scan = new Scanner(System.in);</p>\n" +
                "<p style=\"text-align: center;\">scan.nextInt();</p>\n" +
                "<p style=\"text-align: center;\">Збележете че в горният код четем int стойност от конзолата, как обаче да прочетем double или byte? <br />Просто заменяме nextInt() с nextDouble(), nextByte() и т.н.</p>\n" +
                "<p style=\"text-align: center;\">Операции</p>\n" +
                "<p style=\"text-align: center;\">Върху променливите от целичислен тип можем да извършваме<br /> следните аритметични операции -[+,-,/,*,%]. <br />Като '%' връща остатъка от деление на една променлива с друга.</p>\n" +
                "<p style=\"text-align: center;\">Сравнение</p>\n" +
                "<p style=\"text-align: center;\">В Java можем да ползваме следните оператори за сравнение <br />[&gt;,&lt;,==,!=,&lt;=,&gt;=]<br />Които са както следва <br />по - голямо, по -малко, равно, различни, по - малко или равно и по голямо или равно.</p>\n" +
                "<p style=\"text-align: center;\">Логически оператори</p>\n" +
                "<p style=\"text-align: center;\">Върху променливите от булев тип(boolean) можем да ползваме следните логически оператори</p>\n" +
                "<p style=\"text-align: center;\">[&amp;&amp; - и, || - или, ^ - изключващо или, ! - отрицание].</p>\n" +
                "<p style=\"text-align: center;\">Операции</p>\n" +
                "<p style=\"text-align: center;\">Оператори за присвояване Освен оператор \"=\" в Java има и други оператори за присвояване,<br />които всъщност за комбинация от \"=\" и аритметичен израз - Пример: +=. <br />Това ни улеснява като сакратява записването на част от операциите <br />Оператори за увеличаване и намаляне с единица <br />Или така наречените Постинкремент/Преинкремент <br />и Постдекремент/Предекремент.</p>";

    }
//    @Click(R.id.btn_next_frag)
//    public void loadStages(){
//
//    }


}
