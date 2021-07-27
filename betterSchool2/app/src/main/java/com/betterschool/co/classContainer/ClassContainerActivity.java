package com.betterschool.co.classContainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classContainer.adapter.CustomAdapterClassContainer;
import com.betterschool.co.classContainer.insertClassContainer.InsertClassContainerActivity;
import com.betterschool.co.classContainer.model.ClassContainerRoot;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassContainerActivity extends AppCompatActivity {
    RecyclerView recyClassContainer;
    FloatingActionButton insertClassContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_container);
        findViews();
        getData();
        insertClassContainerAction();
    }

    private void insertClassContainerAction() {
        insertClassContainer.setOnClickListener(v -> startActivity(new Intent(ClassContainerActivity.this, InsertClassContainerActivity.class)));
    }

    private void findViews() {
        recyClassContainer = findViewById(R.id.recyClassContainer);
        insertClassContainer = findViewById(R.id.insertClassContainer);
    }

    private void getData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<ClassContainerRoot>> call = apiInterface.getClassContainer();
        call.enqueue(new Callback<List<ClassContainerRoot>>() {
            @Override
            public void onResponse(Call<List<ClassContainerRoot>> call, Response<List<ClassContainerRoot>> response) {
                if(response.code()==200){
                    recyClassContainer.setAdapter(new CustomAdapterClassContainer(ClassContainerActivity.this,response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<ClassContainerRoot>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}