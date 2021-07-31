package com.betterschool.co.letters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.betterschool.co.MainActivity;
import com.betterschool.co.R;
import com.betterschool.co.letters.adapter.CustomAdapterLetterViewPager;
import com.betterschool.co.news.NewsActivity;
import com.betterschool.co.utilityClass.IntentBetweenActivity;
import com.google.android.material.tabs.TabLayout;

public class ShowLettersActivity extends AppCompatActivity {
    TabLayout tabLayoutLetter;
    ViewPager viewPagerLetter;
    private int[] tabIcons = {
            R.drawable.ic_baseline_access_time_24,
            R.drawable.ic_baseline_access_time_24,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_letters);
        setTabLayoutData();
        setMenu();
    }
    private void setTabLayoutData() {
        tabLayoutLetter = findViewById(R.id.tabLayoutLetter);
        viewPagerLetter = findViewById(R.id.viewPagerLetter);
        viewPagerLetter.setAdapter(new CustomAdapterLetterViewPager(getSupportFragmentManager()));
        tabLayoutLetter.setupWithViewPager(viewPagerLetter);
        tabLayoutLetter.setSelectedTabIndicatorColor(Color.rgb(250,250,250));
        for (int i = 0; i < tabLayoutLetter.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.textviewtablayout,null);
            Typeface typeface = Typeface.createFromAsset(getAssets(),"vazir.ttf");
            tv.setTypeface(typeface);
            tv.setCompoundDrawablesWithIntrinsicBounds(0,tabIcons[i],0,0);
            tabLayoutLetter.getTabAt(i).setCustomView(tv);
        }
        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayoutLetter.getTabAt(0).setIcon(tabIcons[0]);
        tabLayoutLetter.getTabAt(1).setIcon(tabIcons[1]);
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

        homeTitle.setTextColor(getResources().getColor(R.color.disable));
        messageTitle.setTextColor(getResources().getColor(R.color.primary));
        newsTitle.setTextColor(getResources().getColor(R.color.disable));
        classesTitle.setTextColor(getResources().getColor(R.color.disable));

        homeIcon.setColorFilter(ContextCompat.getColor(ShowLettersActivity.this, R.color.disable), android.graphics.PorterDuff.Mode.MULTIPLY);
        messageIcon.setColorFilter(ContextCompat.getColor(ShowLettersActivity.this, R.color.primary), android.graphics.PorterDuff.Mode.MULTIPLY);
        newsIcon.setColorFilter(ContextCompat.getColor(ShowLettersActivity.this, R.color.disable), android.graphics.PorterDuff.Mode.MULTIPLY);
        classesIcon.setColorFilter(ContextCompat.getColor(ShowLettersActivity.this, R.color.disable), android.graphics.PorterDuff.Mode.MULTIPLY);

        home = findViewById(R.id.home);
        message = findViewById(R.id.message);
        news = findViewById(R.id.news);
        classes = findViewById(R.id.classes);

        intentBetweenActivity.startIntent(false,home,message,news,classes,ShowLettersActivity.this, MainActivity.class, NewsActivity.class,
                NewsActivity.class, NewsActivity.class);
    }
}