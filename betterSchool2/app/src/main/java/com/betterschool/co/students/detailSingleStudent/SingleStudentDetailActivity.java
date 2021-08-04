package com.betterschool.co.students.detailSingleStudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.students.detailSingleStudent.adapter.CustomAdapterClassStudentDetailSingle;
import com.betterschool.co.students.detailSingleStudent.model.singleStudentDetailModel;
import com.betterschool.co.teacherClassPackage.exam.adapter.CustomAdapterExam;
import com.betterschool.co.teacherClassPackage.mainPageClass.showSingleExam.adapter.CustomAdapterSingleExam;
import com.betterschool.co.teacherClassPackage.studentDetail.adapter.CustomAdapterStudentDetailExam;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleStudentDetailActivity extends AppCompatActivity {
    singleStudentDetailModel singleStudentDetailModel;
    TextView name,lastName,melliCode,fathersName,grade,createDate,firstScore,secondScore,classContainerName;
    RecyclerView recyClass,recyExams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_student_detail);
        findViews();
        getData();
    }

    private void findViews() {
        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);
        melliCode = findViewById(R.id.melliCode);
        fathersName = findViewById(R.id.fathersName);
        grade = findViewById(R.id.grade);
        createDate = findViewById(R.id.createDate);
        firstScore = findViewById(R.id.firstScore);
        secondScore = findViewById(R.id.secondScore);
        classContainerName = findViewById(R.id.classContainerName);
        recyClass = findViewById(R.id.recyClass);
        recyExams = findViewById(R.id.recyExams);
    }

    private void getData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<singleStudentDetailModel> call = apiInterface.getSingleUserDetail(getIntent().getStringExtra("id"));
        call.enqueue(new Callback<singleStudentDetailModel>() {
            @Override
            public void onResponse(Call<singleStudentDetailModel> call, Response<singleStudentDetailModel> response) {
                if(response.code()==200){
                    singleStudentDetailModel = response.body();
                    setStudentInformation();
                    setClassContainer();
                    setExams();
                }
            }

            @Override
            public void onFailure(Call<singleStudentDetailModel> call, Throwable t) {

            }
        });
    }

    private void setExams() {
        recyExams.setAdapter(new CustomAdapterStudentDetailExam(singleStudentDetailModel.getExams()));
    }

    private void setClassContainer() {
        classContainerName.setText(singleStudentDetailModel.getClassContainers().getName());
        recyClass.setAdapter(new CustomAdapterClassStudentDetailSingle(singleStudentDetailModel.getClassContainers().getClasses()));
    }

    @SuppressLint("SetTextI18n")
    private void setStudentInformation() {
        name.setText("نام : " + singleStudentDetailModel.getStudent().getName());
        lastName.setText("فامیلی : " + singleStudentDetailModel.getStudent().getLastName());
        melliCode.setText("ملی : " + singleStudentDetailModel.getStudent().getMelliCode());
        fathersName.setText("پدر : " + singleStudentDetailModel.getStudent().getFathersName());
        switch (singleStudentDetailModel.getStudent().getGrade()){
            case 1:
                grade.setText("مقطع : اول");
                break;
            case 2:
                grade.setText("مقطع : دوم");
                break;
            case 3:
                grade.setText("مقطع : سوم");
                break;
        }
        createDate.setText("ساخت : " + singleStudentDetailModel.getStudent().getCreateDate());
        firstScore.setText("انضباط اول : " + singleStudentDetailModel.getDisciplines().getFirstScore());
        secondScore.setText("انضباط دوم : " + singleStudentDetailModel.getDisciplines().getSecondScore());

    }
}