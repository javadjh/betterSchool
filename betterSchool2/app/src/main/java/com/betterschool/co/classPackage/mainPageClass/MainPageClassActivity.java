package com.betterschool.co.classPackage.mainPageClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classPackage.attendance.AttendanceDateActivity;
import com.betterschool.co.classPackage.exam.ExamActivity;
import com.betterschool.co.classPackage.exam.adapter.CustomAdapterExam;
import com.betterschool.co.classPackage.exam.model.exams;
import com.betterschool.co.classPackage.mainPageClass.adapter.CustomAdapterMainPageClassStudents;
import com.betterschool.co.classPackage.mainPageClass.model.mainClassStudentsRoot;
import com.betterschool.co.classPackage.startClass.StartClassActivity;
import com.betterschool.co.students.model.students;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPageClassActivity extends AppCompatActivity {
    RecyclerView recyStudents;
    CardView startNewClass,attendance,exam;
    ImageView allClassExams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_class);
        findViews();
        getStudents();
        btnAction();
        getClassExam();
    }

    private void getClassExam() {
        allClassExams.setVisibility(View.VISIBLE);
        allClassExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainPageClassActivity.this);
                dialog.setContentView(R.layout.dialog_simple_recycler_view);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                RecyclerView recyExams = dialog.findViewById(R.id.recyExams);
                TextView closeDialog =dialog.findViewById(R.id.closeDialog);
                closeDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<List<exams>> call = apiInterface.getClassExams(getIntent().getStringExtra("classContainerId"));
                call.enqueue(new Callback<List<exams>>() {
                    @Override
                    public void onResponse(Call<List<exams>> call, Response<List<exams>> response) {
                        recyExams.setAdapter(new CustomAdapterExam(response.body(),MainPageClassActivity.this,null,null));
                    }

                    @Override
                    public void onFailure(Call<List<exams>> call, Throwable t) {

                    }
                });
                dialog.show();
                dialog.getWindow().setAttributes(lp);
            }
        });
    }

    private void btnAction() {
        startNewClass.setOnClickListener(v -> {
            Intent intent = new Intent(MainPageClassActivity.this, StartClassActivity.class);
            intent.putExtra("id",getIntent().getStringExtra("id"));
            intent.putExtra("classContainerId",getIntent().getStringExtra("classContainerId"));
            startActivity(intent);
        });
        attendance.setOnClickListener(v -> {
            Intent intent = new Intent(MainPageClassActivity.this, AttendanceDateActivity.class);
            intent.putExtra("id",getIntent().getStringExtra("id"));
            intent.putExtra("classContainerId",getIntent().getStringExtra("classContainerId"));
            startActivity(intent);
        });
        exam.setOnClickListener(v -> {
            Intent intent = new Intent(MainPageClassActivity.this, ExamActivity.class);
            intent.putExtra("id",getIntent().getStringExtra("id"));
            intent.putExtra("classContainerId",getIntent().getStringExtra("classContainerId"));
            startActivity(intent);
        });
    }

    private void getStudents() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<mainClassStudentsRoot> call = apiInterface.getMainClassPageStudents(getIntent().getStringExtra("classContainerId"));
        call.enqueue(new Callback<mainClassStudentsRoot>() {
            @Override
            public void onResponse(Call<mainClassStudentsRoot> call, Response<mainClassStudentsRoot> response) {
                if(response.code()==200){
                    recyStudents.setAdapter(new CustomAdapterMainPageClassStudents(MainPageClassActivity.this,response.body().getStudents()));
                }
            }

            @Override
            public void onFailure(Call<mainClassStudentsRoot> call, Throwable t) {

            }
        });
    }

    private void findViews() {
        recyStudents = findViewById(R.id.recyStudents);
        startNewClass = findViewById(R.id.startNewClass);
        attendance = findViewById(R.id.attendance);
        exam = findViewById(R.id.exam);
        allClassExams = findViewById(R.id.allClassExams);
    }
}