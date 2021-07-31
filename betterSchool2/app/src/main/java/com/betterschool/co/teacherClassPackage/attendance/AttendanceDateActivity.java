package com.betterschool.co.teacherClassPackage.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.teacherClassPackage.attendance.adapter.CustomAdapterAttendanceDate;
import com.betterschool.co.teacherClassPackage.attendance.model.attendanceRoot;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceDateActivity extends AppCompatActivity {
    RecyclerView recyAttendanceDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendace_date);
        findViews();
        getAttendance();
    }

    private void findViews() {
        recyAttendanceDate = findViewById(R.id.recyAttendanceDate);
    }

    private void getAttendance() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<attendanceRoot>> call = apiInterface.getAttendance(getIntent().getStringExtra("id"));
        call.enqueue(new Callback<List<attendanceRoot>>() {
            @Override
            public void onResponse(Call<List<attendanceRoot>> call, Response<List<attendanceRoot>> response) {
                if(response.code()==200){
                    recyAttendanceDate.setAdapter(new CustomAdapterAttendanceDate(AttendanceDateActivity.this,response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<attendanceRoot>> call, Throwable t) {

            }
        });
    }
}