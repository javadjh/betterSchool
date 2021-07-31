package com.betterschool.co.deputyNote.insertDeputyNote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classContainer.insertClassContainer.adapter.CustomAdapterAllStudents;
import com.betterschool.co.classContainer.insertClassContainer.adapter.payloadStudent;
import com.betterschool.co.deputyNote.insertDeputyNote.adapter.CustomAdapterViolations;
import com.betterschool.co.deputyNote.insertDeputyNote.model.deputyNote;
import com.betterschool.co.deputyNote.insertDeputyNote.model.violations;
import com.betterschool.co.students.model.studentRoot;
import com.betterschool.co.students.model.students;
import com.betterschool.co.teachers.model.teachers;
import com.betterschool.co.utilityClass.payload;
import com.betterschool.co.utilityClass.payloadId;
import com.betterschool.co.utilityClass.questionDialog;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertDeputyNoteActivity extends AppCompatActivity {
    TextView dialogViolation,insertDeputy;
    RecyclerView recyStudents;
    String violationId = null;
    String lastName = null;
    String studentId = null;
    int pageId = 1;
    TextView studentsName;
    List<students> list = new ArrayList<>();
    NestedScrollView scroll;
    SpinKitView progress;
    Boolean inGettingData = false;
    EditText edtLastName,edtDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_deputy_note);
        findViews();
        dialogLogic();
        getStudents(false);
        paging();
        filter();
        insertDeputyAction();
    }

    private void insertDeputyAction() {
        insertDeputy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionDialog.show(InsertDeputyNoteActivity.this, "آیا از ثبت تخلف باری دانش آموز اطمینان دارید؟", new payload() {
                    @Override
                    public void payload(Boolean isChange) {
                        if(isChange){
                            if(violationId==null){
                                Toast.makeText(InsertDeputyNoteActivity.this, "نوع تخلف رو انتخاب کنید", Toast.LENGTH_SHORT).show();
                            }else if(studentId==null){
                                Toast.makeText(InsertDeputyNoteActivity.this, "دانش آموز را انتخاب کنید", Toast.LENGTH_SHORT).show();
                            }else if(edtDescription.getText().toString().isEmpty()){
                                edtDescription.setError("لطفا توضیحات را وارد کنید");
                            }else{
                                APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                                Call<Boolean> call = apiInterface.insertDeputyNote(new deputyNote(studentId,violationId,edtDescription.getText().toString()));
                                call.enqueue(new Callback<Boolean>() {
                                    @Override
                                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                        if(response.code()==200){
                                            Toast.makeText(InsertDeputyNoteActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Boolean> call, Throwable t) {

                                    }
                                });
                            }
                        }
                    }
                });
            }
        });
    }

    private void filter() {
        edtLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lastName = s.toString();
                getStudents(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void paging() {
        scroll.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(scrollY==v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight() && !inGettingData) {
                pageId++;
                getStudents(true);
            }
        });
    }

    private void getStudents(Boolean isPaging) {
        progress.setVisibility(View.VISIBLE);
        inGettingData = true;
        if(!isPaging){
            list.clear();
            pageId = 1;
        }
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<studentRoot> call = apiInterface.getDeputyStudent(pageId,12,lastName);
        call.enqueue(new Callback<studentRoot>() {
            @Override
            public void onResponse(Call<studentRoot> call, Response<studentRoot> response) {
                progress.setVisibility(View.INVISIBLE);
                inGettingData = false;
                if(response.code()==200) {
                    if (response.body().getPageId() >= pageId || pageId == 1) {
                        list = new ArrayList<>(list);
                        list.addAll(response.body().getStudents());
                        recyStudents.setAdapter(new CustomAdapterAllStudents(InsertDeputyNoteActivity.this, list, new payloadStudent() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void students(students st) {
                                studentsName.setText(st.getName() + " " + st.getLastName());
                                studentId = st.get_id();
                            }
                        }));
                    }
                }
            }

            @Override
            public void onFailure(Call<studentRoot> call, Throwable t) {
                progress.setVisibility(View.INVISIBLE);
                inGettingData = false;
            }
        });
    }

    private void dialogLogic() {
        dialogViolation.setOnClickListener(v -> {
            Dialog dialog = new Dialog(InsertDeputyNoteActivity.this);
            dialog.setContentView(R.layout.dialog_violations);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            TextView recyTitle = dialog.findViewById(R.id.recyTitle);
            RecyclerView recyExams = dialog.findViewById(R.id.recyExams);
            EditText title = dialog.findViewById(R.id.title);
            EditText score = dialog.findViewById(R.id.score);
            TextView addViolation = dialog.findViewById(R.id.addViolation);

            addViolation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(title.getText().toString().isEmpty()){
                        title.setError("عنوان را وارد کنید");
                    }else if(score.getText().toString().isEmpty()){
                        score.setError("نمره کسری را وارد کنید");
                    }else{
                        questionDialog.show(InsertDeputyNoteActivity.this, "آیا از افزودن تخلف مطمعن هستید؟", new payload() {
                            @Override
                            public void payload(Boolean isChange) {
                                if(isChange){
                                    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                                    Call<Boolean> call = apiInterface.insertViolation(new violations(Float.parseFloat(score.getText().toString()),
                                            title.getText().toString()));
                                    call.enqueue(new Callback<Boolean>() {
                                        @Override
                                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                            if(response.code()==200){
                                                Toast.makeText(InsertDeputyNoteActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                                                getViolation(dialog, recyExams);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Boolean> call, Throwable t) {

                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });

            recyTitle.setText("لیست تخلفات");

            getViolation(dialog, recyExams);


            dialog.show();
            dialog.getWindow().setAttributes(lp);

        });
    }

    private void getViolation(Dialog dialog, RecyclerView recyExams) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<violations>> call = apiInterface.getViolations();
        call.enqueue(new Callback<List<violations>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<violations>> call, Response<List<violations>> response) {
                if(response.code()==200){
                    recyExams.setAdapter(new CustomAdapterViolations(response.body(), item -> {
                        violationId = item.get_id();
                        dialog.dismiss();
                        dialogViolation.setText(item.getTitle() + " -> " + item.getScore());
                    }));
                }
            }

            @Override
            public void onFailure(Call<List<violations>> call, Throwable t) {

            }
        });
    }

    private void findViews() {
        scroll = findViewById(R.id.scroll);
        progress = findViewById(R.id.progress);
        dialogViolation = findViewById(R.id.dialogViolation);
        recyStudents = findViewById(R.id.recyStudents);
        studentsName = findViewById(R.id.studentsName);
        edtLastName = findViewById(R.id.edtLastName);
        insertDeputy = findViewById(R.id.insertDeputy);
        edtDescription = findViewById(R.id.edtDescription);
    }
}