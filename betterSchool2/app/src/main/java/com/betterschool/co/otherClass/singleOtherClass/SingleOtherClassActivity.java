package com.betterschool.co.otherClass.singleOtherClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classContainer.insertClassContainer.adapter.CustomAdapterAllStudents;
import com.betterschool.co.classContainer.insertClassContainer.adapter.payloadStudent;
import com.betterschool.co.students.adapter.CustomAdapterStudents;
import com.betterschool.co.students.model.students;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleOtherClassActivity extends AppCompatActivity {
    ImageView headerImage;
    TextView title,description,min,max,startDate,endDate,sessionsCount,totalStudent,price,teacherFullName,grade,startTime,join;
    RecyclerView recyStudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_other_class);
        findViews();
        getData();
        insertAction();
    }

    private void insertAction() {
        join.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
             if(sharedPreferences.getString("department","").equals("student")){
                 APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                 Call<Boolean> call = apiInterface.joinOtherClass(getIntent().getStringExtra("id"));
                 call.enqueue(new Callback<Boolean>() {
                     @Override
                     public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                         if(response.code()==200){
                             Toast.makeText(SingleOtherClassActivity.this, "ثبت شدید", Toast.LENGTH_SHORT).show();
                             finish();
                         }
                     }

                     @Override
                     public void onFailure(Call<Boolean> call, Throwable t) {
                         Toast.makeText(SingleOtherClassActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                     }
                 });
             }
        });
    }

    private void getData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<singleOtherClassModel> call = apiInterface.getSingleOtherClass(getIntent().getStringExtra("id"));
        call.enqueue(new Callback<singleOtherClassModel>() {
            @Override
            public void onResponse(Call<singleOtherClassModel> call, Response<singleOtherClassModel> response) {
                if(response.code()==200) {
                    if(!response.body().getCanRegister() || getIntent().getBooleanExtra("joined",false) ||
                        !getIntent().getStringExtra("type").equals("student")){
                        join.setVisibility(View.GONE);
                    }
                    Picasso.get().load("http://192.168.1.33:1000/" + response.body().getImage()).into(headerImage);
                    title.setText("عنوان : " + response.body().getTitle());
                    description.setText(response.body().getDescription());
                    min.setText(response.body().getMinParticipant());
                    max.setText(response.body().getMaxParticipant());
                    startDate.setText(response.body().getStartDate());
                    endDate.setText(response.body().getEndDate());
                    sessionsCount.setText(response.body().getSessionsCount());
                    startTime.setText(response.body().getTimeStart());
                    totalStudent.setText(response.body().getStudentsId().size() + "");
                    price.setText(response.body().getPrice());
                    teacherFullName.setText(response.body().getTeacher().getName() + " " + response.body().getTeacher().getLastName());
                    switch (response.body().getGrade()) {
                        case "1":
                            grade.setText("اول");
                            break;
                        case "2":
                            grade.setText("سوم");
                            break;
                        case "3":
                            grade.setText("چهار");
                            break;
                    }
                    recyStudents.setAdapter(new CustomAdapterAllStudents(SingleOtherClassActivity.this, response.body().getStudentsId(), new payloadStudent() {
                        @Override
                        public void students(students st) {

                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<singleOtherClassModel> call, Throwable t) {

            }
        });
    }

    private void findViews() {
        headerImage = findViewById(R.id.headerImage);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        min = findViewById(R.id.min);
        max = findViewById(R.id.max);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        sessionsCount = findViewById(R.id.sessionsCount);
        totalStudent = findViewById(R.id.totalStudent);
        price = findViewById(R.id.price);
        teacherFullName = findViewById(R.id.teacherFullName);
        grade = findViewById(R.id.grade);
        recyStudents = findViewById(R.id.recyStudents);
        startTime = findViewById(R.id.startTime);
        join = findViewById(R.id.join);
    }
}