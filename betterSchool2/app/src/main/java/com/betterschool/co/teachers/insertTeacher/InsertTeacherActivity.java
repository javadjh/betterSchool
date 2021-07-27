package com.betterschool.co.teachers.insertTeacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.teachers.insertTeacher.model.insertTeacher;
import com.betterschool.co.teachers.model.teachers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertTeacherActivity extends AppCompatActivity {
    EditText edtLastName,edtName,edtTitle,edtMelliCode;
    TextView insertTeacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_teacher);
        findViews();
        if(getIntent().getStringExtra("id")!=null){
            getPageData();
        }
        insertAction();
    }

    private void getPageData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<teachers> call = apiInterface.getSingleTeacher(getIntent().getStringExtra("id"));
        call.enqueue(new Callback<teachers>() {
            @Override
            public void onResponse(Call<teachers> call, Response<teachers> response) {
                if(response.code()==200){
                    edtName.setText(response.body().getName());
                    edtLastName.setText(response.body().getLastName());
                    edtMelliCode.setText(response.body().getMelliCode());
                    edtTitle.setText(response.body().getTitle());
                }
            }

            @Override
            public void onFailure(Call<teachers> call, Throwable t) {

            }
        });

    }

    private void insertAction() {
        insertTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtLastName.getText().toString().isEmpty()){
                    edtLastName.setError("نام خانوادگی اجباری میباشد");
                }else if(edtName.getText().toString().isEmpty()){
                    edtName.setError("نام اجباری میباشد");
                }else if(edtTitle.getText().toString().isEmpty()){
                    edtTitle.setError("عنوان اجباری میباشد");
                }else if(edtMelliCode.getText().toString().isEmpty()){
                    edtMelliCode.setError("شماره ملی اجباری میباشد");
                }else{
                    if(getIntent().getStringExtra("id")==null)
                        sendData();
                    else
                        updateData();
                }
            }
        });
    }

    private void updateData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Boolean> call = apiInterface.updateTeacher(new insertTeacher(
                getIntent().getStringExtra("id"),
                edtName.getText().toString(),
                edtLastName.getText().toString(),
                edtMelliCode.getText().toString(),
                edtTitle.getText().toString()));
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.code()==200){
                    Toast.makeText(InsertTeacherActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    private void sendData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<teachers> call = apiInterface.insertTeacher(new insertTeacher(edtName.getText().toString(),
                edtLastName.getText().toString(),
                edtMelliCode.getText().toString(),
                edtTitle.getText().toString()));
        call.enqueue(new Callback<teachers>() {
            @Override
            public void onResponse(Call<teachers> call, Response<teachers> response) {
                if(response.code()==200){
                    Toast.makeText(InsertTeacherActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                    edtName.setText("");
                    edtLastName.setText("");
                    edtMelliCode.setText("");
                    edtTitle.setText("");
                }
            }

            @Override
            public void onFailure(Call<teachers> call, Throwable t) {

            }
        });

    }

    private void findViews() {
        edtLastName = findViewById(R.id.edtLastName);
        edtName = findViewById(R.id.edtName);
        edtTitle = findViewById(R.id.edtTitle);
        edtMelliCode = findViewById(R.id.edtMelliCode);
        insertTeacher = findViewById(R.id.insertTeacher);
    }
}