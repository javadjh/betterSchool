package com.betterschool.co.semesters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.semesters.adapter.CustomAdapterSemesters;
import com.betterschool.co.semesters.model.semesters;
import com.betterschool.co.semesters.model.semestersRoot;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SemestersActivity extends AppCompatActivity {
    TextView currentSemester;
    RecyclerView recySemesters;
    FloatingActionButton insertSemester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semesters);
        findViews();
        getSemesters();
        insertAction();
    }

    private void insertAction() {
        insertSemester.setOnClickListener(v -> {
            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
            Call<Boolean> call = apiInterface.insertSemester();
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if(response.code()==200){
                        Toast.makeText(SemestersActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                        getSemesters();
                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {

                }
            });
        });
    }

    private void findViews() {
        currentSemester = findViewById(R.id.currentSemester);
        recySemesters = findViewById(R.id.recySemesters);
        insertSemester = findViewById(R.id.insertSemester);
    }

    private void getSemesters() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<semestersRoot> call = apiInterface.getSemesters();
        call.enqueue(new Callback<semestersRoot>() {
            @Override
            public void onResponse(Call<semestersRoot> call, Response<semestersRoot> response) {
                if(response.code()==200){
                    currentSemester.setText("سال تحصیلی فعلی : " + response.body().getCurrentSemester());
                    recySemesters.setAdapter(new CustomAdapterSemesters(response.body().getSemesters()));
                }
            }

            @Override
            public void onFailure(Call<semestersRoot> call, Throwable t) {

            }
        });
    }
}