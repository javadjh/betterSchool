package com.betterschool.co.teacherClassPackage.mainPageClass;

import androidx.appcompat.app.AppCompatActivity;
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
import com.betterschool.co.classContainer.insertClassContainer.adapter.payloadStudent;
import com.betterschool.co.teacherClassPackage.attendance.AttendanceDateActivity;
import com.betterschool.co.teacherClassPackage.exam.ExamActivity;
import com.betterschool.co.teacherClassPackage.exam.adapter.CustomAdapterExam;
import com.betterschool.co.teacherClassPackage.exam.model.exams;
import com.betterschool.co.teacherClassPackage.mainPageClass.adapter.CustomAdapterMainPageClassStudents;
import com.betterschool.co.teacherClassPackage.mainPageClass.model.mainClassStudentsRoot;
import com.betterschool.co.teacherClassPackage.note.NoteActivity;
import com.betterschool.co.teacherClassPackage.startClass.StartClassActivity;
import com.betterschool.co.teacherClassPackage.studentDetail.StudentDetailActivity;
import com.betterschool.co.students.model.students;
import com.betterschool.co.teacherClassPackage.teachersFile.TeachersFileActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPageClassActivity extends AppCompatActivity {
    RecyclerView recyStudents;
    ImageView allClassExams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_class);
        findViews();
        getStudents();
        getClassExam();
    }

    private void getClassExam() {
        allClassExams.setVisibility(View.VISIBLE);
        allClassExams.setOnClickListener(v -> {
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
        });
    }

    public void btnAction(View view) {
        Intent intent = null;
        if(view.getId()==R.id.exam)
            intent = new Intent(MainPageClassActivity.this, ExamActivity.class);
        else if(view.getId()==R.id.startNewClass)
            intent = new Intent(MainPageClassActivity.this, StartClassActivity.class);
        else if(view.getId()==R.id.attendance)
            intent = new Intent(MainPageClassActivity.this, AttendanceDateActivity.class);
        else if(view.getId()==R.id.note)
            intent = new Intent(MainPageClassActivity.this, NoteActivity.class);
        else if(view.getId()==R.id.teachersFile)
            intent = new Intent(MainPageClassActivity.this, TeachersFileActivity.class);
        assert intent != null;
        intent.putExtra("id",getIntent().getStringExtra("id"));
        intent.putExtra("classContainerId",getIntent().getStringExtra("classContainerId"));
        startActivity(intent);
    }

    private void getStudents() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<mainClassStudentsRoot> call = apiInterface.getMainClassPageStudents(getIntent().getStringExtra("classContainerId"));
        call.enqueue(new Callback<mainClassStudentsRoot>() {
            @Override
            public void onResponse(Call<mainClassStudentsRoot> call, Response<mainClassStudentsRoot> response) {
                if(response.code()==200){
                    recyStudents.setAdapter(new CustomAdapterMainPageClassStudents(MainPageClassActivity.this, response.body().getStudents(), new payloadStudent() {
                        @Override
                        public void students(students st) {
                            Intent intent = new Intent(MainPageClassActivity.this, StudentDetailActivity.class);
                            intent.putExtra("id",getIntent().getStringExtra("id"));
                            intent.putExtra("studentId",st.get_id());
                            intent.putExtra("classContainerId",getIntent().getStringExtra("classContainerId"));
                            startActivity(intent);
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<mainClassStudentsRoot> call, Throwable t) {

            }
        });
    }

    private void findViews() {
        recyStudents = findViewById(R.id.recyStudents);
        allClassExams = findViewById(R.id.allClassExams);
    }
}