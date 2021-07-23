package com.betterschool.co;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.betterschool.co.homePage.adapter.CustomAdapterHomePage;
import com.betterschool.co.homePage.model.modelHomePage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyOptions;
    List<modelHomePage> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        fakeDataHomePage();
    }

    private void findViews() {
        recyOptions = findViewById(R.id.recyOptions);
    }

    private void fakeDataHomePage() {
        list.add(new modelHomePage(R.drawable.ic_student,R.drawable.card_one,"دانش آموزان","در این قسمت میتوانید دانش آموزان را مدیریت کنید","student"));
        list.add(new modelHomePage(R.drawable.ic_teacher,R.drawable.card_two,"معلم ها","در این قسمت میتوانید معلم ها را مدیریت کنید","teacher"));
        list.add(new modelHomePage(R.drawable.ic_resource_class,R.drawable.card_three,"کلاس ها","در این قسمت میتوانید کلاس ها را مدیریت کنید","classContainer"));
        list.add(new modelHomePage(R.drawable.ic_semester,R.drawable.card_four,"ثبت نیم سال","در این قسمت میتوانید نیم سال ها را مدیریت کنید","semester"));
        list.add(new modelHomePage(R.drawable.ic_news,R.drawable.card_one,"ثبت اخبار","در این قسمت میتوانید اخبار را مدیریت کنید","news"));
        /*list.add(new modelHomePage(R.drawable.card_one,"دانش آموزان","در این قسمت میتوانید دانش آموزان را مدیریت کنید","student"));
        list.add(new modelHomePage(R.drawable.card_one,"دانش آموزان","در این قسمت میتوانید دانش آموزان را مدیریت کنید","student"));*/
        CustomAdapterHomePage adapterHomePage = new CustomAdapterHomePage(list,MainActivity.this);
        recyOptions.setAdapter(adapterHomePage);

    }
}