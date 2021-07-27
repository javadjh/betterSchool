package com.betterschool.co.classContainer.moveStudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classContainer.insertClassContainer.adapter.CustomAdapterAllStudents;
import com.betterschool.co.classContainer.insertClassContainer.adapter.payloadStudent;
import com.betterschool.co.classContainer.moveStudent.model.moveStudentModel;
import com.betterschool.co.classContainer.moveStudent.model.moveStudentModelSingle;
import com.betterschool.co.students.model.students;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModeStudentActivity extends AppCompatActivity {
    MaterialSpinner receiverClassSpinner;
    TextView moveStudent,studentSelected;
    RecyclerView recyStudents;
    List<String> listSpinner = new ArrayList<>();
    List<String> listSpinnerForShow = new ArrayList<>();
    String classContainer = null;
    String studentId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_student);
        findViews();
        getPageData();
        moveAction();
    }

    private void moveAction() {
        moveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<Boolean> call = apiInterface.moveStudent(new moveStudentModel(getIntent().getStringExtra("id"),
                        studentId,classContainer));
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.code()==200){
                            Toast.makeText(ModeStudentActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
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

    private void getPageData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<moveStudentModelSingle> call = apiInterface.getMoveSingle(getIntent().getStringExtra("id"));
        call.enqueue(new Callback<moveStudentModelSingle>() {
            @Override
            public void onResponse(Call<moveStudentModelSingle> call, Response<moveStudentModelSingle> response) {
                if(response.code()==200){
                    for (int i = 0 ; i<response.body().getClassContainer().size() ; i++){
                        listSpinner.add(response.body().getClassContainer().get(i).get_id());
                        listSpinnerForShow.add(response.body().getClassContainer().get(i).getName());
                    }
                    Toast.makeText(ModeStudentActivity.this, "" +  response.body().getStudents().getStudents().size()  , Toast.LENGTH_SHORT).show();
                    recyStudents.setAdapter(new CustomAdapterAllStudents(ModeStudentActivity.this, response.body().getStudents().getStudents(), new payloadStudent() {
                        @Override
                        public void students(students st) {
                            studentId = st.get_id();
                            studentSelected.setText("دانش آموز انتخاب شده : " + st.getName() + " " + st.getLastName());
                        }
                    }));
                    setSpinner();
                }
            }

            @Override
            public void onFailure(Call<moveStudentModelSingle> call, Throwable t) {
                Toast.makeText(ModeStudentActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setSpinner() {
        Toast.makeText(this, listSpinnerForShow.size() + "", Toast.LENGTH_SHORT).show();
        receiverClassSpinner.setItems(listSpinnerForShow);
        receiverClassSpinner.setOnItemSelectedListener((view, position, id, item) -> classContainer = listSpinner.get(position));
    }


    private void findViews() {
        receiverClassSpinner = findViewById(R.id.receiverClassSpinner);
        moveStudent = findViewById(R.id.moveStudent);
        studentSelected = findViewById(R.id.studentSelected);
        recyStudents = findViewById(R.id.recyStudents);
    }
}