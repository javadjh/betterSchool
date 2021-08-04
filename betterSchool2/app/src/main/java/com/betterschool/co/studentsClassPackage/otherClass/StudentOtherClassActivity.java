package com.betterschool.co.studentsClassPackage.otherClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.otherClass.adapter.CustomAdapterOtherClass;
import com.betterschool.co.studentsClassPackage.otherClass.model.studentOtherClassRoot;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentOtherClassActivity extends AppCompatActivity {
    RecyclerView recyStudentsOtherClass,recyAllOtherClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_other_class);
        findViews();
        getData();
    }

    private void getData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<studentOtherClassRoot> call = apiInterface.getStudentsOtherClass();
        call.enqueue(new Callback<studentOtherClassRoot>() {
            @Override
            public void onResponse(Call<studentOtherClassRoot> call, Response<studentOtherClassRoot> response) {
                if(response.code()==200){
                    recyAllOtherClass.setAdapter(new CustomAdapterOtherClass(response.body().getAllOtherClasses(),StudentOtherClassActivity.this,"student"));
                    recyStudentsOtherClass.setAdapter(new CustomAdapterOtherClass(response.body().getOtherClassRegistered(),StudentOtherClassActivity.this,"student",true));
                }
            }

            @Override
            public void onFailure(Call<studentOtherClassRoot> call, Throwable t) {

            }
        });
    }

    private void findViews() {
        recyStudentsOtherClass = findViewById(R.id.recyStudentsOtherClass);
        recyAllOtherClass = findViewById(R.id.recyAllOtherClass);
    }
}