package com.betterschool.co;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.betterschool.co.homePage.adapter.CustomAdapterHomePage;
import com.betterschool.co.homePage.model.modelHomePage;
import com.betterschool.co.letters.ShowLettersActivity;
import com.betterschool.co.login.LoginActivity;
import com.betterschool.co.news.NewsActivity;
import com.betterschool.co.students.StudentsActivity;
import com.betterschool.co.utilityClass.IntentBetweenActivity;
import com.betterschool.co.weeklySchedule.WeeklyScheduleActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyOptions;
    List<modelHomePage> list = new ArrayList<>();
    CardView exitApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        fakeDataHomePage();
        setMenu();
        exitAction();
    }

    private void exitAction() {
        exitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });
    }

    private void setMenu() {
        IntentBetweenActivity intentBetweenActivity = new IntentBetweenActivity();
        LinearLayout home,message,news,classes;
        ImageView homeIcon,messageIcon,newsIcon,classesIcon;
        TextView homeTitle,messageTitle,newsTitle,classesTitle;

        homeIcon = findViewById(R.id.homeIcon);
        newsIcon = findViewById(R.id.newsIcon);
        classesIcon = findViewById(R.id.classesIcon);
        messageIcon = findViewById(R.id.messageIcon);
        homeTitle = findViewById(R.id.homeTitle);
        messageTitle = findViewById(R.id.messageTitle);
        newsTitle = findViewById(R.id.newsTitle);
        classesTitle = findViewById(R.id.classesTitle);

        homeTitle.setTextColor(getResources().getColor(R.color.primary));
        messageTitle.setTextColor(getResources().getColor(R.color.disable));
        newsTitle.setTextColor(getResources().getColor(R.color.disable));
        classesTitle.setTextColor(getResources().getColor(R.color.disable));

        homeIcon.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.primary), android.graphics.PorterDuff.Mode.MULTIPLY);
        messageIcon.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.disable), android.graphics.PorterDuff.Mode.MULTIPLY);
        newsIcon.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.disable), android.graphics.PorterDuff.Mode.MULTIPLY);
        classesIcon.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.disable), android.graphics.PorterDuff.Mode.MULTIPLY);

        home = findViewById(R.id.home);
        message = findViewById(R.id.message);
        news = findViewById(R.id.news);
        classes = findViewById(R.id.classes);

        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        if(sharedPreferences.getString("department","").equals("student")){
            classes.setVisibility(View.VISIBLE);
        }

        intentBetweenActivity.startIntent(false,home,message,news,classes,MainActivity.this, NewsActivity.class, ShowLettersActivity.class,
                NewsActivity.class, WeeklyScheduleActivity.class);
    }

    private void findViews() {
        recyOptions = findViewById(R.id.recyOptions);
        exitApp = findViewById(R.id.exitApp);
    }

    private void fakeDataHomePage() {
        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        String getDepartment = sharedPreferences.getString("department","student");
        if(getDepartment.equals("headmaster")) {
            list.add(new modelHomePage(R.drawable.ic_student, R.drawable.card_one, "دانش آموزان", "در این قسمت میتوانید دانش آموزان را مدیریت کنید", "student"));
            list.add(new modelHomePage(R.drawable.ic_teacher, R.drawable.card_two, "معلم ها", "در این قسمت میتوانید معلم ها را مدیریت کنید", "teacher"));
            list.add(new modelHomePage(R.drawable.ic_resource_class, R.drawable.card_three, "کلاس ها", "در این قسمت میتوانید کلاس ها را مدیریت کنید", "classContainer"));
            list.add(new modelHomePage(R.drawable.ic_semester, R.drawable.card_four, "ثبت نیم سال", "در این قسمت میتوانید نیم سال ها را مدیریت کنید", "semester"));
        }else if(getDepartment.equals("teacher")){
            list.add(new modelHomePage(R.drawable.ic_resource_class, R.drawable.card_one, "مدیریت کلاس ها", "در این قسمت میتوانید کلاس هایتان را مدیریت کنید", "classManager"));
//            list.add(new modelHomePage(R.drawable.ic_todo, R.drawable.card_two, "یادداشت ها", "در این قسمت میتوانید یادداشت هایتان را مدیریت کنید", "todoList"));
            list.add(new modelHomePage(R.drawable.ic_exam, R.drawable.card_three, "ارسال سوالات", "در این قسمت میتوانید سوالات امتحانی را برای معاون ارسال کنید", "sendExam"));
        }else if(getDepartment.equals("student")){
            list.add(new modelHomePage(R.drawable.ic_resource_class, R.drawable.card_one, "کلاس ها", "در این قسمت میتوانید کلاس هایتان را مدیریت کنید", "studentsClass"));
            list.add(new modelHomePage(R.drawable.ic_todo, R.drawable.card_two, "امتحان ها", "در این قسمت میتوانید امتحان های خود را مشاهده کنید", "studentExam"));
//            list.add(new modelHomePage(R.drawable.ic_exam, R.drawable.card_three, "ارسال سوالات", "در این قسمت میتوانید سوالات امتحانی را برای معاون ارسال کنید", "sendExam"));
        }else if(getDepartment.equals("deputy")){
            list.add(new modelHomePage(R.drawable.ic_resource_class, R.drawable.card_one, "همه یادداشت ها", "در این قسمت میتوانید یادداشت های انضباطی ا مشاهده کنید", "allDeputies"));
            list.add(new modelHomePage(R.drawable.ic_todo, R.drawable.card_two, "ثبت یادداشت", "در این قسمت میتوانید امتحان های خود را مشاهده کنید", "insertDeputy"));
            list.add(new modelHomePage(R.drawable.ic_exam, R.drawable.card_three, "ثبت نمره انضباط", "در این قسمت میتوانید نمره انضباط ثبت کنید", "setDiscipline"));
        }
        CustomAdapterHomePage adapterHomePage = new CustomAdapterHomePage(list,MainActivity.this);
        recyOptions.setAdapter(adapterHomePage);

    }
}