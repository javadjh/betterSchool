package com.betterschool.co.teacherClassPackage.studentDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.teacherClassPackage.studentDetail.adapter.CustomAdapterStudentDetailAttendance;
import com.betterschool.co.teacherClassPackage.studentDetail.adapter.CustomAdapterStudentDetailExam;
import com.betterschool.co.teacherClassPackage.studentDetail.model.studentDetailRoot;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentDetailActivity extends AppCompatActivity {
    RecyclerView recyStudentExam,recyAttendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        findViews();
        getData();
    }

    private void findViews() {
        recyStudentExam = findViewById(R.id.recyStudentExam);
        recyAttendance = findViewById(R.id.recyAttendance);
    }

    private void getData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<studentDetailRoot> call = apiInterface.getStudentDetail(getIntent().getStringExtra("id"),
                getIntent().getStringExtra("studentId"));
        call.enqueue(new Callback<studentDetailRoot>() {
            @Override
            public void onResponse(Call<studentDetailRoot> call, Response<studentDetailRoot> response) {
                if(response.code()==200){
                    recyStudentExam.setAdapter(new CustomAdapterStudentDetailExam(response.body().getScores()));
                    recyAttendance.setAdapter(new CustomAdapterStudentDetailAttendance(response.body().getAttendance()));
                }
            }

            @Override
            public void onFailure(Call<studentDetailRoot> call, Throwable t) {

            }
        });
    }
}