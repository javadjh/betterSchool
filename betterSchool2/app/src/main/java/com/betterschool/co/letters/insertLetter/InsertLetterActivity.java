package com.betterschool.co.letters.insertLetter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classContainer.classes.adapter.CustomAdapterTeachers;
import com.betterschool.co.classContainer.classes.adapter.payloadTeacher;
import com.betterschool.co.classContainer.insertClassContainer.adapter.CustomAdapterAllStudents;
import com.betterschool.co.classContainer.insertClassContainer.adapter.payloadStudent;
import com.betterschool.co.letters.insertLetter.model.insertLetterModel;
import com.betterschool.co.letters.insertLetter.model.receiverModel;
import com.betterschool.co.letters.insertLetter.model.studentAndTeacherModel;
import com.betterschool.co.students.model.students;
import com.betterschool.co.studentsClassPackage.exam.adapter.CustomAllStudentsExam;
import com.betterschool.co.teachers.model.teachers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertLetterActivity extends AppCompatActivity {
    EditText title,description;
    RadioButton sendAllStudent,sendAllTeacher,sendStudent,sendTeacher;
    TextView choiceUser,insertLetter;
    RadioGroup typeRadio;
    String userId = null;
    String type = null;
    receiverModel receiverModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_letter);
        findViews();
        typeLogic();
        choiceUserLogic();
        sendData();
    }

    private void sendData() {
        insertLetter.setOnClickListener(v -> {
            if(title.getText().toString().isEmpty()){
                title.setError("عنوان اجباری میباشد");
            }else if(description.getText().toString().isEmpty()){
                description.setError("توضیحات اجباری میباشد");
            }else if(userId==null && (type.equals("Student") || type.equals("Teacher"))){
                Toast.makeText(this, "انتخاب کاربر اجباری میباشد", Toast.LENGTH_SHORT).show();
            }else {
                APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<Boolean> call = apiInterface.insertLetter(new insertLetterModel(title.getText().toString(),
                        description.getText().toString(), receiverModel, type, userId));
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.code() == 200) {
                            Toast.makeText(InsertLetterActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
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

    private void choiceUserLogic() {
        choiceUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(InsertLetterActivity.this);
                dialog.setContentView(R.layout.dialog_simple_recycler_view);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView recyTitle = dialog.findViewById(R.id.recyTitle);
                RecyclerView recyExams = dialog.findViewById(R.id.recyExams);
                EditText searchValue = dialog.findViewById(R.id.searchValue);
                recyTitle.setText("انتخاب کاربر");
                setDialogData(dialog, recyExams, searchValue);
                dialog.show();
                dialog.getWindow().setAttributes(lp);

            }
        });
    }

    private void setDialogData(Dialog dialog, RecyclerView recyExams, EditText searchValue) {
        if(type.equals("Teacher")){
            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
            Call<studentAndTeacherModel> call = apiInterface.getStudentsAndTeachers("teacher", searchValue.getText().toString());
            call.enqueue(new Callback<studentAndTeacherModel>() {
                @Override
                public void onResponse(Call<studentAndTeacherModel> call, Response<studentAndTeacherModel> response) {
                    if(response.code()==200) {
                        recyExams.setAdapter(new CustomAdapterTeachers(InsertLetterActivity.this, response.body().getTeacher(), new payloadTeacher() {
                            @Override
                            public void teacher(teachers ts) {
                                receiverModel = new receiverModel(ts.getName(), ts.getLastName(), ts.get_id());
                                userId = ts.get_id();
                                choiceUser.setText(ts.getName() + " " + ts.getLastName());
                                dialog.dismiss();
                            }
                        }));
                    }
                }

                @Override
                public void onFailure(Call<studentAndTeacherModel> call, Throwable t) {

                }
            });

        }else if(type.equals("Student")){
            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
            Call<studentAndTeacherModel> call = apiInterface.getStudentsAndTeachers("student", searchValue.getText().toString());
            call.enqueue(new Callback<studentAndTeacherModel>() {
                @Override
                public void onResponse(Call<studentAndTeacherModel> call, Response<studentAndTeacherModel> response) {
                    if(response.code()==200) {
                        recyExams.setAdapter(new CustomAdapterAllStudents(InsertLetterActivity.this, response.body().getStudents(), new payloadStudent() {
                            @Override
                            public void students(students st) {
                                receiverModel = new receiverModel(st.getName(), st.getLastName(), st.get_id());
                                userId = st.get_id();
                                choiceUser.setText(st.getName() + " " + st.getLastName());
                                dialog.dismiss();
                            }
                        }));
                    }
                }

                @Override
                public void onFailure(Call<studentAndTeacherModel> call, Throwable t) {

                }
            });
        }
    }

    private void typeLogic() {
        typeRadio.setOnCheckedChangeListener((group, checkedId) -> {
            choiceUser.setText("کاربر را انتخاب کنید");
            userId = null;
            receiverModel = null;
            if(sendAllStudent.isChecked()){
                type = "AllStudent";
                choiceUser.setVisibility(View.GONE);
            }else if(sendAllTeacher.isChecked()){
                type = "AllTeacher";
                choiceUser.setVisibility(View.GONE);
            }else if(sendStudent.isChecked()){
                type ="Student";
                choiceUser.setVisibility(View.VISIBLE);
            }else if(sendTeacher.isChecked()){
                type = "Teacher";
                choiceUser.setVisibility(View.VISIBLE);
            }
        });
    }

    private void findViews() {
        choiceUser = findViewById(R.id.choiceUser);
        description = findViewById(R.id.description);
        sendAllStudent = findViewById(R.id.sendAllStudent);
        sendAllTeacher = findViewById(R.id.sendAllTeacher);
        sendStudent = findViewById(R.id.sendStudent);
        sendTeacher = findViewById(R.id.sendTeacher);
        title = findViewById(R.id.title);
        typeRadio = findViewById(R.id.typeRadio);
        insertLetter = findViewById(R.id.insertLetter);
    }
}