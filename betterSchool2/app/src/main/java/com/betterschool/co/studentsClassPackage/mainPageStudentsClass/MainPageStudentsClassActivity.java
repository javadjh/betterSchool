package com.betterschool.co.studentsClassPackage.mainPageStudentsClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.teacherClassPackage.studentDetail.adapter.CustomAdapterStudentDetailAttendance;
import com.betterschool.co.teacherClassPackage.studentDetail.adapter.CustomAdapterStudentDetailExam;
import com.betterschool.co.teacherClassPackage.studentDetail.model.studentDetailRoot;
import com.betterschool.co.teacherClassPackage.teachersFile.adapter.CustomAdapterTeachersFile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPageStudentsClassActivity extends AppCompatActivity {
    RecyclerView recyAttendance,recyExams,teachersFile;
    TextView attendance,exam,files;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_students_class);
        findViews();
        getData();
        expansionLogic();
    }

    private void expansionLogic() {
        attendance.setOnClickListener(v -> {
            recyExams.setVisibility(View.GONE);
            teachersFile.setVisibility(View.GONE);
            recyAttendance.setVisibility(View.VISIBLE);
        });
        exam.setOnClickListener(v -> {
            recyAttendance.setVisibility(View.GONE);
            teachersFile.setVisibility(View.GONE);
            recyExams.setVisibility(View.VISIBLE);
        });
        files.setOnClickListener(v -> {
            recyAttendance.setVisibility(View.GONE);
            recyExams.setVisibility(View.GONE);
            teachersFile.setVisibility(View.VISIBLE);
        });
    }

    private void findViews() {
        recyAttendance = findViewById(R.id.recyAttendance);
        recyExams = findViewById(R.id.recyExams);
        attendance = findViewById(R.id.attendance);
        exam = findViewById(R.id.exam);
        files = findViewById(R.id.files);
        teachersFile = findViewById(R.id.teachersFile);
    }

    private void getData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<studentDetailRoot> call = apiInterface.getStudentsStudentDetail(getIntent().getStringExtra("id"));
        call.enqueue(new Callback<studentDetailRoot>() {
            @Override
            public void onResponse(Call<studentDetailRoot> call, Response<studentDetailRoot> response) {
                if(response.code()==200){
                    recyExams.setAdapter(new CustomAdapterStudentDetailExam(response.body().getScores()));
                    recyAttendance.setAdapter(new CustomAdapterStudentDetailAttendance(response.body().getAttendance()));
                    teachersFile.setAdapter(new CustomAdapterTeachersFile(response.body().getFiles(),MainPageStudentsClassActivity.this));
                }
            }

            @Override
            public void onFailure(Call<studentDetailRoot> call, Throwable t) {

            }
        });
    }
}