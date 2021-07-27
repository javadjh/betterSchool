package com.betterschool.co.classPackage.startClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classPackage.mainPageClass.MainPageClassActivity;
import com.betterschool.co.classPackage.mainPageClass.adapter.CustomAdapterMainPageClassStudents;
import com.betterschool.co.classPackage.mainPageClass.model.mainClassStudentsRoot;
import com.betterschool.co.classPackage.startClass.adapter.CustomAdapterAttendance;
import com.betterschool.co.classPackage.startClass.adapter.changeStudentStatus;
import com.betterschool.co.classPackage.startClass.model.insertAttendanceModel;
import com.betterschool.co.students.model.students;
import com.betterschool.co.utilityClass.payload;
import com.betterschool.co.utilityClass.questionDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartClassActivity extends AppCompatActivity {
    RecyclerView recyStudents;
    List<students> list = new ArrayList<>();
    TextView btnAttendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_class);
        findViews();
        getStudents();
        sendData();
    }

    private void findViews() {
        btnAttendance = findViewById(R.id.btnAttendance);
        recyStudents = findViewById(R.id.recyStudents);
    }

    private void sendData() {
        btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionDialog.show(StartClassActivity.this, "آیا از ثبت این کلاس مطمعن هستید؟", new payload() {
                    @Override
                    public void payload(Boolean isChange) {
                        if(isChange){
                            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                            Call<Boolean> call = apiInterface.insertAttendance(new insertAttendanceModel(list,getIntent().getStringExtra("id"),
                                    getIntent().getStringExtra("classContainerId")));
                            call.enqueue(new Callback<Boolean>() {
                                @Override
                                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                    if(response.code()==200){
                                        Toast.makeText(StartClassActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                                        finish();
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
    }

    private void getStudents() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<mainClassStudentsRoot> call = apiInterface.getMainClassPageStudents(getIntent().getStringExtra("classContainerId"));
        call.enqueue(new Callback<mainClassStudentsRoot>() {
            @Override
            public void onResponse(Call<mainClassStudentsRoot> call, Response<mainClassStudentsRoot> response) {
                if(response.code()==200){
                    list = response.body().getStudents();
                    recyStudents.setAdapter(new CustomAdapterAttendance(response.body().getStudents(), StartClassActivity.this, new changeStudentStatus() {
                        @Override
                        public void onChange(Boolean isPresent, Boolean hasNegativeScore, Boolean hasPositiveScore, String id) {
                            for (int i  = 0 ; i<list.size() ; i++){
                                if(list.get(i).get_id().equals(id)){
                                    list.get(i).setHasNegativeScore(hasNegativeScore);
                                    list.get(i).setHasPositiveScore(hasPositiveScore);
                                    list.get(i).setPresent(isPresent);
                                }
                            }
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<mainClassStudentsRoot> call, Throwable t) {

            }
        });
    }
}