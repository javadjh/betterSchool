package com.betterschool.co.students;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.students.adapter.CustomAdapterStudents;
import com.betterschool.co.students.insertStudent.InsertStudentActivity;
import com.betterschool.co.students.model.studentRoot;
import com.betterschool.co.students.model.students;
import com.betterschool.co.utilityClass.payload;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentsActivity extends AppCompatActivity {
    RecyclerView recyStudents;
    NestedScrollView scroll;
    int pageId = 1;
    Boolean inGettingData = false;
    TextView familySearch;
    String searchValue = null;
    EditText edtSearchValue;
    List<students> list = new ArrayList<>();
    SpinKitView progress;
    FloatingActionButton insertStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        findViews();
        getStudents(false);
        paging();
        filter();
        insertStudentAction();
    }

    private void insertStudentAction() {
        insertStudent.setOnClickListener(v -> {
            Intent intent = new Intent(StudentsActivity.this, InsertStudentActivity.class);
            startActivity(intent);
        });
    }

    private void filter() {
        familySearch.setOnClickListener(v -> {
            searchValue = edtSearchValue.getText().toString().isEmpty() ?null :edtSearchValue.getText().toString();
            getStudents(false);
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

    private void findViews() {
        recyStudents = findViewById(R.id.recyStudents);
        scroll = findViewById(R.id.scroll);
        familySearch = findViewById(R.id.familySearch);
        edtSearchValue = findViewById(R.id.edtSearchValue);
        progress = findViewById(R.id.progress);
        insertStudent = findViewById(R.id.insertStudent);
    }

    private void getStudents(Boolean isPaging) {
        inGettingData = true;
        progress.setVisibility(View.VISIBLE);
        if(!isPaging){
            pageId = 1;
            list.clear();
        }
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<studentRoot> call = apiInterface.getStudents(pageId,12,searchValue);
        call.enqueue(new Callback<studentRoot>() {
            @Override
            public void onResponse(Call<studentRoot> call, Response<studentRoot> response) {
                progress.setVisibility(View.INVISIBLE);
                if(response.code()==200) {
                    if (response.body().getPageId() >= pageId || pageId == 1) {
                        inGettingData = false;
                        list = new ArrayList<>(list);
                        list.addAll(response.body().getStudents());
                        recyStudents.setAdapter(new CustomAdapterStudents(list, StudentsActivity.this, new payload() {
                            @Override
                            public void payload(Boolean isChange) {
                                getStudents(false);
                            }
                        }));
                    }
                }
            }

            @Override
            public void onFailure(Call<studentRoot> call, Throwable t) {
                progress.setVisibility(View.INVISIBLE);
                inGettingData = false;
                Toast.makeText(StudentsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}