package com.betterschool.co.teachers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.teachers.adapter.CustomAdapterTeachers;
import com.betterschool.co.teachers.insertTeacher.InsertTeacherActivity;
import com.betterschool.co.teachers.model.teachers;
import com.betterschool.co.teachers.model.teachersRoot;
import com.betterschool.co.utilityClass.payload;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachersActivity extends AppCompatActivity {
    EditText edtSearchValueLastName,edtSearchValueMelliCode;
    TextView setFilter;
    RecyclerView recyTeacher;
    FloatingActionButton insertTeacher;
    int pageId = 1;
    String lastName;
    String melliCode;
    List<teachers> list = new ArrayList<>();
    NestedScrollView scroll;
    SpinKitView progress;
    Boolean inGettingData = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);
        findViews();
        getTeacher(false);
        paging();
        insertTeacher();
        filterAction();
    }

    private void filterAction() {
        setFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastName = !edtSearchValueLastName.getText().toString().isEmpty()?edtSearchValueLastName.getText().toString():null;
                melliCode = !edtSearchValueMelliCode.getText().toString().isEmpty()?edtSearchValueMelliCode.getText().toString():null;
                getTeacher(false);
            }
        });
    }

    private void insertTeacher() {
        insertTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeachersActivity.this, InsertTeacherActivity.class));
            }
        });
    }

    private void paging() {
        scroll.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(scrollY==v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight() && !inGettingData) {
                pageId++;
                getTeacher(true);
            }
        });
    }

    private void findViews() {
        edtSearchValueLastName = findViewById(R.id.edtSearchValueLastName);
        edtSearchValueMelliCode = findViewById(R.id.edtSearchValueMelliCode);
        setFilter = findViewById(R.id.setFilter);
        recyTeacher = findViewById(R.id.recyTeacher);
        insertTeacher = findViewById(R.id.insertTeacher);
        scroll = findViewById(R.id.scroll);
        progress = findViewById(R.id.progress);
    }

    private void getTeacher(Boolean isPaging) {
        progress.setVisibility(View.VISIBLE);
        inGettingData = true;
        if(!isPaging){
            list.clear();
            pageId = 1;
        }
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<teachersRoot> call = apiInterface.getTeachers(pageId,12,lastName,melliCode);
        call.enqueue(new Callback<teachersRoot>() {
            @Override
            public void onResponse(Call<teachersRoot> call, Response<teachersRoot> response) {
                progress.setVisibility(View.INVISIBLE);
                inGettingData = false;
                if(response.code()==200) {
                    if (response.body().getPageId() >= pageId || pageId == 1) {
                        list = new ArrayList<>(list);
                        list.addAll(response.body().getTeachers());
                        recyTeacher.setAdapter(new CustomAdapterTeachers(TeachersActivity.this, list, new payload() {
                            @Override
                            public void payload(Boolean isChange) {
                                getTeacher(false);
                            }
                        }));
                    }
                }
            }

            @Override
            public void onFailure(Call<teachersRoot> call, Throwable t) {
                progress.setVisibility(View.INVISIBLE);
                inGettingData = false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        melliCode = null;
        lastName = null;
        edtSearchValueMelliCode.setText("");
        edtSearchValueLastName.setText("");
        getTeacher(false);
    }
}