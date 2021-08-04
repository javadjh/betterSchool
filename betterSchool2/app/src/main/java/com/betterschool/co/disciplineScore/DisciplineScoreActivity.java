package com.betterschool.co.disciplineScore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import com.betterschool.co.deputyNote.adapter.CustomAdapterDeputyNote;
import com.betterschool.co.deputyNote.insertDeputyNote.InsertDeputyNoteActivity;
import com.betterschool.co.disciplineScore.model.disciplineModel;
import com.betterschool.co.disciplineScore.model.insertDisciplineModel;
import com.betterschool.co.students.model.studentRoot;
import com.betterschool.co.students.model.students;
import com.betterschool.co.utilityClass.payload;
import com.betterschool.co.utilityClass.questionDialog;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisciplineScoreActivity extends AppCompatActivity {
    EditText edtLastName;
    NestedScrollView scroll;
    SpinKitView progress;
    RecyclerView recyStudents;
    Boolean inGettingData = false;
    List<students> list = new ArrayList<>();
    int pageId = 1;
    String lastName = null;
    Boolean isFirst = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discipline_score);
        findViews();
        getStudents(false);
        paging();
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
                        recyStudents.setAdapter(new CustomAdapterAllStudents(DisciplineScoreActivity.this, list, new payloadStudent() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void students(students st) {
                                setDisciplineDialog(st);
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

    private void setDisciplineDialog(students st) {
        Dialog dialog = new Dialog(DisciplineScoreActivity.this);
        dialog.setContentView(R.layout.dialog_set_discipline);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText firstScore = dialog.findViewById(R.id.firstScore);
        EditText secondScore = dialog.findViewById(R.id.secondScore);
        TextView suggestionScore = dialog.findViewById(R.id.suggestionScore);
        RecyclerView recyNotes = dialog.findViewById(R.id.recyNotes);
        TextView insertDiscipline = dialog.findViewById(R.id.insertDiscipline);
        TextView cancelDialog = dialog.findViewById(R.id.cancelDialog);


        cancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<disciplineModel> call = apiInterface.getStudentsDiscipline(st.get_id());
        call.enqueue(new Callback<disciplineModel>() {
            @Override
            public void onResponse(Call<disciplineModel> call, Response<disciplineModel> response) {
                if(response.code()==200){
                    if(response.body().getDiscipline()!=null){
                        isFirst = false;
                        firstScore.setText(response.body().getDiscipline().getFirstScore() + "");
                        firstScore.setEnabled(false);
                        if(response.body().getDiscipline().getSecondScore()!=null){
                            secondScore.setText(response.body().getDiscipline().getSecondScore() + "");
                            secondScore.setEnabled(false);
                        }
                    }else{
                        isFirst = true;
                    }
                    suggestionScore.setText("نمره پیشنهادی : " + response.body().getSuggestionScore() + "");
                    recyNotes.setAdapter(new CustomAdapterDeputyNote(response.body().getNotes(),DisciplineScoreActivity.this));

                }
            }

            @Override
            public void onFailure(Call<disciplineModel> call, Throwable t) {
                Toast.makeText(DisciplineScoreActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });

        insertDiscipline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionDialog.show(DisciplineScoreActivity.this, "آیا از ثبت نمره مطمعن هستید؟", new payload() {
                    @Override
                    public void payload(Boolean isChange) {
                        if(isChange){
                            APIInterface apiInterface1 = APIClient.getClient().create(APIInterface.class);
                            Call<Boolean> call1 = apiInterface1.insertDiscipline(new insertDisciplineModel(
                                    isFirst?Float.parseFloat(firstScore.getText().toString()):null,
                                    isFirst?null:Float.parseFloat(secondScore.getText().toString()),
                                    st.get_id()
                            ));
                            call1.enqueue(new Callback<Boolean>() {
                                @Override
                                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                    if(response.code()==200){
                                        Toast.makeText(DisciplineScoreActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
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
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);

    }

    private void findViews() {
        edtLastName = findViewById(R.id.edtLastName);
        scroll = findViewById(R.id.scroll);
        progress = findViewById(R.id.progress);
        recyStudents = findViewById(R.id.recyStudents);
    }
}