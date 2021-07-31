package com.betterschool.co.weeklySchedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.betterschool.co.MainActivity;
import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classContainer.model.classes;
import com.betterschool.co.news.NewsActivity;
import com.betterschool.co.utilityClass.IntentBetweenActivity;
import com.betterschool.co.weeklySchedule.adapter.CustomAdapterWeeklySchedule;
import com.betterschool.co.weeklySchedule.model.weeklyScheduleModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeeklyScheduleActivity extends AppCompatActivity {
    RecyclerView recyOne,recyTwo,recyThree,recyFour,recyFive,recySix;
    List<classes> list0 = new ArrayList<>();
    List<classes> list1 = new ArrayList<>();
    List<classes> list2 = new ArrayList<>();
    List<classes> list3 = new ArrayList<>();
    List<classes> list4 = new ArrayList<>();
    List<classes> list5 = new ArrayList<>();
    List<classes> list6 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_schedule);
        findViews();
        setMenu();
        getData();
    }

    private void findViews() {
        recyOne = findViewById(R.id.recyOne);
        recyTwo = findViewById(R.id.recyTwo);
        recyThree = findViewById(R.id.recyThree);
        recyFour = findViewById(R.id.recyFour);
        recyFive = findViewById(R.id.recyFive);
        recySix = findViewById(R.id.recySix);
    }

    private void getData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<weeklyScheduleModel> call = apiInterface.getStudentClassContainer();
        call.enqueue(new Callback<weeklyScheduleModel>() {
            @Override
            public void onResponse(Call<weeklyScheduleModel> call, Response<weeklyScheduleModel> response) {
                if(response.code()==200){
                    for (int i = 0 ; i<response.body().getClasses().size() ; i++){
                        switch (response.body().getClasses().get(i).getDayStart()){
                            case 0:
                                list0.add(response.body().getClasses().get(i));
                                break;
                            case 1:
                                list1.add(response.body().getClasses().get(i));
                                break;
                            case 2:
                                list2.add(response.body().getClasses().get(i));
                                break;
                            case 3:
                                list3.add(response.body().getClasses().get(i));
                                break;
                            case 4:
                                list4.add(response.body().getClasses().get(i));
                                break;
                            case 5:
                                list5.add(response.body().getClasses().get(i));
                                break;
                            case 6:
                                list6.add(response.body().getClasses().get(i));
                                break;
                        }
                    }
                    recyOne.setAdapter(new CustomAdapterWeeklySchedule(list0));
                    recyTwo.setAdapter(new CustomAdapterWeeklySchedule(list1));
                    recyThree.setAdapter(new CustomAdapterWeeklySchedule(list2));
                    recyFour.setAdapter(new CustomAdapterWeeklySchedule(list3));
                    recyFive.setAdapter(new CustomAdapterWeeklySchedule(list4));
                    recySix.setAdapter(new CustomAdapterWeeklySchedule(list5));
                }
            }

            @Override
            public void onFailure(Call<weeklyScheduleModel> call, Throwable t) {

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

        homeTitle.setTextColor(getResources().getColor(R.color.disable));
        messageTitle.setTextColor(getResources().getColor(R.color.disable));
        newsTitle.setTextColor(getResources().getColor(R.color.disable));
        classesTitle.setTextColor(getResources().getColor(R.color.primary));

        homeIcon.setColorFilter(ContextCompat.getColor(WeeklyScheduleActivity.this, R.color.disable), android.graphics.PorterDuff.Mode.MULTIPLY);
        messageIcon.setColorFilter(ContextCompat.getColor(WeeklyScheduleActivity.this, R.color.disable), android.graphics.PorterDuff.Mode.MULTIPLY);
        newsIcon.setColorFilter(ContextCompat.getColor(WeeklyScheduleActivity.this, R.color.disable), android.graphics.PorterDuff.Mode.MULTIPLY);
        classesIcon.setColorFilter(ContextCompat.getColor(WeeklyScheduleActivity.this, R.color.primary), android.graphics.PorterDuff.Mode.MULTIPLY);

        home = findViewById(R.id.home);
        message = findViewById(R.id.message);
        news = findViewById(R.id.news);
        classes = findViewById(R.id.classes);

        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        if(sharedPreferences.getString("department","").equals("student")){
            classes.setVisibility(View.VISIBLE);
        }

        intentBetweenActivity.startIntent(false,home,message,news,classes,WeeklyScheduleActivity.this, MainActivity.class, NewsActivity.class,
                NewsActivity.class, WeeklyScheduleActivity.class);
    }
}