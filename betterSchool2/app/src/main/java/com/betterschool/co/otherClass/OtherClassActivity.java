package com.betterschool.co.otherClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.otherClass.adapter.CustomAdapterOtherClass;
import com.betterschool.co.otherClass.insertOtherClass.InsertOtherClassActivity;
import com.betterschool.co.otherClass.model.otherClasses;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherClassActivity extends AppCompatActivity {
    RecyclerView recyOtherClass;
    MaterialSpinner gradeSpinner;
    FloatingActionButton insertOtherClass;
    Integer grade = 1;
    List<String> listGrade = new ArrayList<>();
    List<String> listGradeForSpinner = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_class_activity);
        findViews();
        getData();
        setSpinner();
        insertBtnAction();
    }

    private void insertBtnAction() {
        insertOtherClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtherClassActivity.this, InsertOtherClassActivity.class));
            }
        });
    }

    private void setSpinner() {
        listGrade.add("1");
        listGrade.add("2");
        listGrade.add("3");
        listGradeForSpinner.add("اول");
        listGradeForSpinner.add("دوم");
        listGradeForSpinner.add("سوم");
        gradeSpinner.setItems(listGradeForSpinner);
        gradeSpinner.setOnItemSelectedListener((view, position, id, item) -> {
            grade = Integer.parseInt(listGrade.get(position));
            getData();
        });
    }

    private void getData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<otherClasses>> call = apiInterface.getOtherClass(grade);
        call.enqueue(new Callback<List<otherClasses>>() {
            @Override
            public void onResponse(Call<List<otherClasses>> call, Response<List<otherClasses>> response) {
                if(response.code()==200){
                    recyOtherClass.setAdapter(new CustomAdapterOtherClass(response.body(),OtherClassActivity.this,"headmaster"));
                }
            }

            @Override
            public void onFailure(Call<List<otherClasses>> call, Throwable t) {

            }
        });
    }

    private void findViews() {
        recyOtherClass = findViewById(R.id.recyOtherClass);
        gradeSpinner = findViewById(R.id.gradeSpinner);
        insertOtherClass = findViewById(R.id.insertOtherClass);
    }
}