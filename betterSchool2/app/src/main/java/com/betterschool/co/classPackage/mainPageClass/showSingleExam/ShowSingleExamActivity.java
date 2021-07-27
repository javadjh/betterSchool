package com.betterschool.co.classPackage.mainPageClass.showSingleExam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classPackage.exam.model.exams;
import com.betterschool.co.classPackage.mainPageClass.model.mainClassStudentsRoot;
import com.betterschool.co.classPackage.mainPageClass.showSingleExam.adapter.CustomAdapterSingleExam;
import com.betterschool.co.classPackage.mainPageClass.showSingleExam.adapter.payloadStudentExam;
import com.betterschool.co.classPackage.mainPageClass.showSingleExam.model.updateExamModel;
import com.betterschool.co.students.model.students;
import com.betterschool.co.utilityClass.payload;
import com.betterschool.co.utilityClass.questionDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowSingleExamActivity extends AppCompatActivity {
    RecyclerView recyStudents;
    List<students> list = new ArrayList<>();
    TextView setScoreBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_exam);
        findViews();
        if(getIntent().getBooleanExtra("hasUpdated",true)){
            getExamSingle();
        }else {
            getStudents();
            setScoreAction();
        }
    }

    private void getExamSingle() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<exams> call = apiInterface.getSingleExam(getIntent().getStringExtra("idExam"));
        call.enqueue(new Callback<exams>() {
            @Override
            public void onResponse(Call<exams> call, Response<exams> response) {
                if(response.code()==200){
                    recyStudents.setAdapter(new CustomAdapterSingleExam(response.body().getStudents(), ShowSingleExamActivity.this, students -> {

                    },true));
                }
            }

            @Override
            public void onFailure(Call<exams> call, Throwable t) {

            }
        });
    }

    private void setScoreAction() {
        setScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionDialog.show(ShowSingleExamActivity.this, "آیا از ثبت نمره ها اطمینان دارید؟", new payload() {
                    @Override
                    public void payload(Boolean isChange) {
                        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                        Call<Boolean> call = apiInterface.updateStudentsScoreExam(new updateExamModel(getIntent().getStringExtra("idExam"),list));
                        call.enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                if (response.code()==200){
                                    Toast.makeText(ShowSingleExamActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {

                            }
                        });
                    }
                });
            }
        });
    }

    private void findViews() {
        setScoreBtn = findViewById(R.id.setScoreBtn);
        recyStudents = findViewById(R.id.recyStudents);
    }

    private void getStudents() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<mainClassStudentsRoot> call = apiInterface.getMainClassPageStudents(getIntent().getStringExtra("classContainerId"));
        call.enqueue(new Callback<mainClassStudentsRoot>() {
            @Override
            public void onResponse(Call<mainClassStudentsRoot> call, Response<mainClassStudentsRoot> response) {
                if(response.code()==200){
                    list = response.body().getStudents();
                    recyStudents.setAdapter(new CustomAdapterSingleExam(response.body().getStudents(), ShowSingleExamActivity.this, new payloadStudentExam() {
                        @Override
                        public void onChange(students students) {
                            for (int i = 0 ; i<list.size() ; i++){
                                if(list.get(i).get_id()==students.get_id()){
                                    list.get(i).setScore(students.getScore());
                                }
                            }

                        }
                    },false));
                }
            }

            @Override
            public void onFailure(Call<mainClassStudentsRoot> call, Throwable t) {

            }
        });
    }
}