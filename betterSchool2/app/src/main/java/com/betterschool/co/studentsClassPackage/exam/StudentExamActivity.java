package com.betterschool.co.studentsClassPackage.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.studentsClassPackage.exam.adapter.CustomAdapterImStudentsExam;
import com.betterschool.co.studentsClassPackage.exam.adapter.CustomAllStudentsExam;
import com.betterschool.co.studentsClassPackage.exam.model.studentsExam;
import com.betterschool.co.teacherClassPackage.exam.adapter.CustomAdapterExam;
import com.betterschool.co.teacherClassPackage.studentDetail.adapter.CustomAdapterStudentDetailExam;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentExamActivity extends AppCompatActivity {
    RecyclerView recyFirstExam,recyFirstFinal,recySecondExam,recySecondFinal,otherExam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_exam);
        findViews();
        getData();
    }

    private void findViews() {
        recyFirstExam = findViewById(R.id.recyFirstExam);
        recyFirstFinal = findViewById(R.id.recyFirstFinal);
        recySecondExam = findViewById(R.id.recySecondExam);
        recySecondFinal = findViewById(R.id.recySecondFinal);
        otherExam = findViewById(R.id.otherExam);
    }

    private void getData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<studentsExam> call = apiInterface.getStudentsExam();
        call.enqueue(new Callback<studentsExam>() {
            @Override
            public void onResponse(Call<studentsExam> call, Response<studentsExam> response) {
                recyFirstExam.setAdapter(new CustomAdapterImStudentsExam(response.body().getListFirstExam()));
                recyFirstFinal.setAdapter(new CustomAdapterImStudentsExam(response.body().getListFirstFinalExam()));
                recySecondExam.setAdapter(new CustomAdapterImStudentsExam(response.body().getListSecondExam()));
                recySecondFinal.setAdapter(new CustomAdapterImStudentsExam(response.body().getListSecondFinalExam()));
                otherExam.setAdapter(new CustomAllStudentsExam(response.body().getExams(),StudentExamActivity.this));
            }

            @Override
            public void onFailure(Call<studentsExam> call, Throwable t) {

            }
        });
    }
}