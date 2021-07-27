package com.betterschool.co.classContainer.classes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classContainer.classes.adapter.CustomAdapterContainersClass;
import com.betterschool.co.classContainer.classes.adapter.CustomAdapterTeachers;
import com.betterschool.co.classContainer.classes.adapter.payloadTeacher;
import com.betterschool.co.classContainer.classes.model.ClassRoot;
import com.betterschool.co.teachers.model.teachers;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassesActivity extends AppCompatActivity {
    RecyclerView recyClasses;
    FloatingActionButton insertClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        findViews();
        getClasses();
        insertAction();

    }

    private void insertAction() {
        insertClass.setOnClickListener(v -> {
            Intent intent = new Intent(ClassesActivity.this,InsertClassActivity.class);
            intent.putExtra("id",getIntent().getStringExtra("id"));
            startActivity(intent);
        });
    }

    private void getClasses() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<ClassRoot>> call = apiInterface.getContainersClass(getIntent().getStringExtra("id"));
        call.enqueue(new Callback<List<ClassRoot>>() {
            @Override
            public void onResponse(Call<List<ClassRoot>> call, Response<List<ClassRoot>> response) {
                recyClasses.setAdapter(new CustomAdapterContainersClass(response.body(),ClassesActivity.this));
            }

            @Override
            public void onFailure(Call<List<ClassRoot>> call, Throwable t) {

            }
        });

    }

    private void findViews() {
        insertClass = findViewById(R.id.insertClass);
        recyClasses = findViewById(R.id.recyClasses);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getClasses();
    }
}