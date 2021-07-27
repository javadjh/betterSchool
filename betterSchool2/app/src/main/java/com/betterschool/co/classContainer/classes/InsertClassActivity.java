package com.betterschool.co.classContainer.classes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classContainer.classes.adapter.CustomAdapterTeachers;
import com.betterschool.co.classContainer.classes.adapter.payloadTeacher;
import com.betterschool.co.classContainer.classes.model.insertClassModel;
import com.betterschool.co.teachers.model.teachers;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertClassActivity extends AppCompatActivity {
    EditText edtName,edtTimeStart;
    MaterialSpinner weekDaySpinner;
    TextView teacherName,insertClass;
    RecyclerView recyTeachers;
    String teacherId;
    List<String> listSpinner = new ArrayList<>();
    String dayStart = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_class);
        findViews();
        getTeachers();
        setSpinner();
        insertAction();
    }

    private void insertAction() {
        insertClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<Boolean> call = apiInterface.insertClass(new insertClassModel(
                        edtName.getText().toString(),
                        teacherId,
                        dayStart,
                        edtTimeStart.getText().toString(),
                        getIntent().getStringExtra("id")
                ));
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.code()==200){
                            Toast.makeText(InsertClassActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
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

    private void setSpinner() {
        listSpinner.add("شنبه");
        listSpinner.add("یک شنبه");
        listSpinner.add("دو شنبه");
        listSpinner.add("سه شنبه");
        listSpinner.add("چهار شنبه");
        listSpinner.add("پنج شنبه");
        weekDaySpinner.setItems(listSpinner);
        weekDaySpinner.setOnItemSelectedListener((view, position, id, item) -> dayStart = position + "");
    }

    private void getTeachers() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<teachers>> call = apiInterface.getAllTeachers();
        call.enqueue(new Callback<List<teachers>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<teachers>> call, Response<List<teachers>> response) {
                recyTeachers.setAdapter(new CustomAdapterTeachers(InsertClassActivity.this, response.body(), ts -> {
                    teacherId = ts.get_id();
                    teacherName.setText("معلم : " + ts.getName() + " " + ts.getLastName());
                }));
            }

            @Override
            public void onFailure(Call<List<teachers>> call, Throwable t) {

            }
        });
    }

    private void findViews() {
        edtName = findViewById(R.id.edtName);
        edtTimeStart = findViewById(R.id.edtTimeStart);
        weekDaySpinner = findViewById(R.id.weekDaySpinner);
        teacherName = findViewById(R.id.teacherName);
        recyTeachers = findViewById(R.id.recyTeachers);
        edtName = findViewById(R.id.edtName);
        insertClass = findViewById(R.id.insertClass);
    }
}