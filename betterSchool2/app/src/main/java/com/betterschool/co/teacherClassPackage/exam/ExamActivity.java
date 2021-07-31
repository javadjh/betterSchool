package com.betterschool.co.teacherClassPackage.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.teacherClassPackage.exam.adapter.CustomAdapterExam;
import com.betterschool.co.teacherClassPackage.exam.model.exams;
import com.betterschool.co.teacherClassPackage.exam.model.insertExamModel;
import com.betterschool.co.utilityClass.StringPayload;
import com.betterschool.co.utilityClass.datePickerClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamActivity extends AppCompatActivity {
    RecyclerView recyExams;
    FloatingActionButton insertExam;
    String startDate = null;
    String type = null;
    List<String> listTypes = new ArrayList<>();
    List<String> listTypesForSpinner = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        findViews();
        getExams();
        addExam();
    }

    private void addExam() {
        insertExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ExamActivity.this);
                dialog.setContentView(R.layout.dialog_insert_exam);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                EditText edtTitle = dialog.findViewById(R.id.edtTitle);
                EditText edtDescription = dialog.findViewById(R.id.edtDescription);
                LinearLayout startDatePicker  = dialog.findViewById(R.id.startDatePicker);
                MaterialSpinner typeSpinner  = dialog.findViewById(R.id.typeSpinner);
                TextView insertNewExam = dialog.findViewById(R.id.insertNewExam);
                TextView cancelBtn = dialog.findViewById(R.id.cancelBtn);
                TextView dateViewTx = dialog.findViewById(R.id.dateView);
                startDate = null;
                type = null;
                typeSpinner.setItems(listTypesForSpinner);
                typeSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        type = listTypes.get(position);
                    }
                });
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                insertNewExam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                        Call<Boolean> call = apiInterface.insertExam(new insertExamModel(edtTitle.getText().toString(),
                                edtDescription.getText().toString(),
                                startDate,getIntent().getStringExtra("id"),getIntent().getStringExtra("classContainerId"),type));
                        call.enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                if(response.code()==200){
                                    Toast.makeText(ExamActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    getExams();
                                }
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {

                            }
                        });
                    }
                });

                startDatePicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datePickerClass.datePicker(ExamActivity.this, new StringPayload() {
                            @Override
                            public void stringPicker(String date, String dateView) {
                                dateViewTx.setText(dateView);
                                startDate = date;
                            }
                        });
                    }
                });

                dialog.show();
                dialog.getWindow().setAttributes(lp);
            }
        });
    }

    private void getExams() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<exams>> call = apiInterface.getExams(getIntent().getStringExtra("id"));
        call.enqueue(new Callback<List<exams>>() {
            @Override
            public void onResponse(Call<List<exams>> call, Response<List<exams>> response) {
                if(response.code()==200){
                    recyExams.setAdapter(new CustomAdapterExam(response.body(),ExamActivity.this,getIntent().getStringExtra("id"),
                            getIntent().getStringExtra("classContainerId")));
                }
            }

            @Override
            public void onFailure(Call<List<exams>> call, Throwable t) {

            }
        });
    }

    private void findViews() {
        listTypes.add("class");
        listTypes.add("firstFinalExam");
        listTypes.add("firstExam");
        listTypes.add("secondFinalExam");
        listTypes.add("secondExam");

        listTypesForSpinner.add("امتحان کلاسی");
        listTypesForSpinner.add("امتحان نوبت اول");
        listTypesForSpinner.add("امتحان مستمر اول");
        listTypesForSpinner.add("امتحان نوبت دوم");
        listTypesForSpinner.add("امتحان مستمر دوم");
        recyExams = findViewById(R.id.recyExams);
        insertExam = findViewById(R.id.insertExam);
    }
}