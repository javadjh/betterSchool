package com.betterschool.co.classPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classPackage.adapter.CustomAdapterTeachersClass;
import com.betterschool.co.classPackage.model.teachersClassesRoot;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachersClassActivity extends AppCompatActivity {
    RecyclerView recyClasses;
    CustomAdapterTeachersClass adapterTeachersClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_class);
        findViews();
        getClasses();
    }

    private void getClasses() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<teachersClassesRoot>> call = apiInterface.getTeachersClass();
        call.enqueue(new Callback<List<teachersClassesRoot>>() {
            @Override
            public void onResponse(Call<List<teachersClassesRoot>> call, Response<List<teachersClassesRoot>> response) {
                if(response.code()==200){
                    adapterTeachersClass = new CustomAdapterTeachersClass(response.body(),TeachersClassActivity.this);
                    recyClasses.setAdapter(adapterTeachersClass);
                }
            }

            @Override
            public void onFailure(Call<List<teachersClassesRoot>> call, Throwable t) {

            }
        });
    }

    private void findViews() {
        recyClasses = findViewById(R.id.recyClasses);
    }
}