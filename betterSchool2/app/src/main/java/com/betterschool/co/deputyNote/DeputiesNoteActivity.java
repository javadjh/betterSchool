package com.betterschool.co.deputyNote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.deputyNote.adapter.CustomAdapterDeputyNote;
import com.betterschool.co.deputyNote.model.deputyNoteRoot;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeputiesNoteActivity extends AppCompatActivity {
    RecyclerView recyDeputyNote;
    int pageId = 1;
    String lastName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deputy_note);
        findViews();
        getData();
    }

    private void getData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<deputyNoteRoot> call = apiInterface.getDeputiesNote(pageId,12,lastName);
        call.enqueue(new Callback<deputyNoteRoot>() {
            @Override
            public void onResponse(Call<deputyNoteRoot> call, Response<deputyNoteRoot> response) {
                if(response.code()==200){
                    recyDeputyNote.setAdapter(new CustomAdapterDeputyNote(response.body().getDeputyNote(),DeputiesNoteActivity.this));
                }
            }

            @Override
            public void onFailure(Call<deputyNoteRoot> call, Throwable t) {

            }
        });
    }

    private void findViews() {
        recyDeputyNote = findViewById(R.id.recyDeputyNote);
    }
}