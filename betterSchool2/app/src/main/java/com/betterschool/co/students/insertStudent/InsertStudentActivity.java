package com.betterschool.co.students.insertStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.students.insertStudent.model.insertStudentModel;
import com.betterschool.co.students.model.students;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertStudentActivity extends AppCompatActivity {
    EditText edtName,edtLastName,edtMelliCode,edtFathersName;
    TextView insertStudent;
    MaterialSpinner choiceGrade;
    List<String> listGrades = new ArrayList<>();
    String grade = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_student);
        findViews();
        setGrades();
        insertAction();
    }

    private void insertAction() {
        insertStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtName.getText().toString().isEmpty()){
                    edtName.setError("نام اجباری میباشد");
                }else if(edtLastName.getText().toString().isEmpty()){
                    edtLastName.setError("نام خانوادگی اجباری میباشد");
                }else if(edtMelliCode.getText().toString().isEmpty()){
                    edtMelliCode.setError("کد ملثی اجباری میباشد");
                }else if(edtFathersName.getText().toString().isEmpty()){
                    edtFathersName.setError("نام پدر اجباری میباشد");
                }else if(grade==null) {
                    Toast.makeText(InsertStudentActivity.this, "مقطع تحصیلی اجباری میباشد", Toast.LENGTH_SHORT).show();
                }else{
                    sendData();
                }
            }
        });
    }

    private void sendData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<students> call = apiInterface.insertStudent(new insertStudentModel(
                edtName.getText().toString(),
                edtLastName.getText().toString(),
                edtFathersName.getText().toString(),
                edtMelliCode.getText().toString(),
                grade
        ));
        call.enqueue(new Callback<students>() {
            @Override
            public void onResponse(Call<students> call, Response<students> response) {
                if(response.code()==200){
                    Toast.makeText(InsertStudentActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                    edtLastName.setText("");
                    edtMelliCode.setText("");
                    edtFathersName.setText("");
                    edtName.setText("");
                    grade=null;
                }
            }

            @Override
            public void onFailure(Call<students> call, Throwable t) {

            }
        });
    }

    private void setGrades() {
        listGrades.add("پایه اول");
        listGrades.add("پایه دوم");
        listGrades.add("پایه سوم");
        choiceGrade.setItems(listGrades);
        choiceGrade.setOnItemSelectedListener((view, position, id, item) -> grade = position + 1 + "");
    }

    private void findViews() {
        edtName = findViewById(R.id.edtName);
        edtLastName = findViewById(R.id.edtLastName);
        edtMelliCode = findViewById(R.id.edtMelliCode);
        edtFathersName = findViewById(R.id.edtFathersName);
        choiceGrade = findViewById(R.id.choiceGrade);
        insertStudent = findViewById(R.id.insertStudent);
        edtName = findViewById(R.id.edtName);
    }
}