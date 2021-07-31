package com.betterschool.co.teacherClassPackage.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
import com.betterschool.co.teacherClassPackage.note.adapter.CustomAdapterNote;
import com.betterschool.co.teacherClassPackage.note.model.notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteActivity extends AppCompatActivity {
    FloatingActionButton insertNote;
    RecyclerView recyNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        findViews();
        getNotes();
        insertAction();
    }

    private void insertAction() {
        insertNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(NoteActivity.this);
                dialog.setContentView(R.layout.dialog_insert_note);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                EditText edtTitle = dialog.findViewById(R.id.edtTitle);
                EditText edtDescription = dialog.findViewById(R.id.edtDescription);
                TextView insertBtn = dialog.findViewById(R.id.insertBtn);
                TextView closeDialog = dialog.findViewById(R.id.closeDialog);
                closeDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                insertBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                        Call<Boolean> call = apiInterface.insertNote(new notes(
                                edtTitle.getText().toString(),
                                edtDescription.getText().toString(),
                                getIntent().getStringExtra("id")
                        ));
                        call.enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                if(response.code()==200){
                                    Toast.makeText(NoteActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    getNotes();
                                }
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {

                            }
                        });
                    }
                });
                dialog.show();
                dialog.getWindow().setAttributes(lp);
            }
        });
    }

    private void getNotes() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<notes>> call = apiInterface.getNotes(getIntent().getStringExtra("id"));
        call.enqueue(new Callback<List<notes>>() {
            @Override
            public void onResponse(Call<List<notes>> call, Response<List<notes>> response) {
                if(response.code()==200){
                    recyNote.setAdapter(new CustomAdapterNote(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<notes>> call, Throwable t) {

            }
        });
    }

    private void findViews() {
        insertNote = findViewById(R.id.insertNote);
        recyNote = findViewById(R.id.recyNote);
    }
}