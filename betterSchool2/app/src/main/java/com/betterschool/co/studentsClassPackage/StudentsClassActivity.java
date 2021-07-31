package com.betterschool.co.studentsClassPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classContainer.model.classes;
import com.betterschool.co.studentsClassPackage.adapter.CustomAdapterClassesStudent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentsClassActivity extends AppCompatActivity {
    RecyclerView recyClasses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_class);
        findViews();
        getData();
    }

    private void findViews() {
        recyClasses = findViewById(R.id.recyClasses);
    }

    private void getData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<classes>> call = apiInterface.getStudentsClass();
        call.enqueue(new Callback<List<classes>>() {
            @Override
            public void onResponse(Call<List<classes>> call, Response<List<classes>> response) {
                if(response.code()==200){
                    recyClasses.setAdapter(new CustomAdapterClassesStudent(response.body(),StudentsClassActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<classes>> call, Throwable t) {

            }
        });
    }
}