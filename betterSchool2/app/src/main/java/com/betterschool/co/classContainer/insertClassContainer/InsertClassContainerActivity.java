package com.betterschool.co.classContainer.insertClassContainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classContainer.insertClassContainer.adapter.CustomAdapterAllStudents;
import com.betterschool.co.classContainer.insertClassContainer.adapter.CustomAdapterStudentSelected;
import com.betterschool.co.classContainer.insertClassContainer.adapter.payloadStudent;
import com.betterschool.co.classContainer.model.insertClassContainer;
import com.betterschool.co.students.model.students;
import com.betterschool.co.utilityClass.payload;
import com.betterschool.co.utilityClass.questionDialog;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertClassContainerActivity extends AppCompatActivity {
    MaterialSpinner gradeSpinner;
    EditText edtName;
    RecyclerView recyStudents,recySelected;
    TextView insertStudent;
    List<String> listGrades = new ArrayList<>();
    String grade = null;
    List<students> list = new ArrayList<>();
    List<students> listSelected = new ArrayList<>();
    CustomAdapterAllStudents adapterAllStudents = null;
    CustomAdapterStudentSelected adapterStudentSelected = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_class_container);
        findViews();
        setGrades();
        getStudents();
        sendData();
    }

    private void sendData() {
        insertStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionDialog.show(InsertClassContainerActivity.this, "آیا از ثبت این کلاس مطمعن هستید؟", new payload() {
                    @Override
                    public void payload(Boolean isChange) {
                        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                        Call<Boolean> call = apiInterface.insertClassContainer(new insertClassContainer(
                                edtName.getText().toString(),
                                listSelected
                        ));
                        call.enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                if(response.code()==200){
                                    Toast.makeText(InsertClassContainerActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
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
        });
    }

    private void getStudents() {
        if(grade==null)
            return;
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<students>> call = apiInterface.getAllStudent(grade);
        call.enqueue(new Callback<List<students>>() {
            @Override
            public void onResponse(Call<List<students>> call, Response<List<students>> response) {
                if(response.code()==200){
                    list = response.body();
                    setList();

                }
            }

            @Override
            public void onFailure(Call<List<students>> call, Throwable t) {

            }
        });
    }

    private void setList() {
        adapterAllStudents = new CustomAdapterAllStudents(InsertClassContainerActivity.this, list, new payloadStudent() {
            @Override
            public void students(students st) {
                list.remove(st);
                listSelected.add(st);
                adapterAllStudents.notifyDataSetChanged();
                setSelected();

            }
        });
        recyStudents.setAdapter(adapterAllStudents);
        adapterAllStudents.notifyDataSetChanged();
    }

    private void setSelected(){
        adapterStudentSelected = new CustomAdapterStudentSelected(listSelected, InsertClassContainerActivity.this, new payloadStudent() {
            @Override
            public void students(students st) {
                listSelected.remove(st);
                list.add(st);
                adapterStudentSelected.notifyDataSetChanged();
                setList();
            }
        });
        recySelected.setAdapter(adapterStudentSelected);
        adapterStudentSelected.notifyDataSetChanged();
    }

    private void findViews() {
        gradeSpinner = findViewById(R.id.gradeSpinner);
        edtName = findViewById(R.id.edtName);
        insertStudent = findViewById(R.id.insertStudent);
        recyStudents = findViewById(R.id.recyStudents);
        recySelected = findViewById(R.id.recySelected);
    }

    private void setGrades() {
        listGrades.add("پایه اول");
        listGrades.add("پایه دوم");
        listGrades.add("پایه سوم");
        gradeSpinner.setItems(listGrades);
        gradeSpinner.setOnItemSelectedListener((view, position, id, item) -> {
            grade = position + 1 + "";
            list.clear();
            listSelected.clear();
            getStudents();
        });
    }
}