package com.betterschool.co.teacherClassPackage.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.teacherClassPackage.attendance.adapter.CustomAdapterShowAttendance;
import com.betterschool.co.teacherClassPackage.attendance.model.attendanceRoot;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceActivity extends AppCompatActivity {
    RecyclerView recyAttendace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        findViews();
        getAttendance();
    }

    private void findViews() {
        recyAttendace = findViewById(R.id.recyAttendace);
    }

    private void getAttendance() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<attendanceRoot> call = apiInterface.getSingleAttendance(getIntent().getStringExtra("id"));
        call.enqueue(new Callback<attendanceRoot>() {
            @Override
            public void onResponse(Call<attendanceRoot> call, Response<attendanceRoot> response) {
                if(response.code()==200){
                    recyAttendace.setAdapter(new CustomAdapterShowAttendance(response.body().getStudents(),AttendanceActivity.this));
                }
            }

            @Override
            public void onFailure(Call<attendanceRoot> call, Throwable t) {

            }
        });
    }
}