package com.betterschool.co.teacherClassPackage.teachersFile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.TextView;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.teacherClassPackage.teachersFile.adapter.CustomAdapterTeachersFile;
import com.betterschool.co.teacherClassPackage.teachersFile.model.teachersFile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachersFileActivity extends AppCompatActivity {
    RecyclerView recyTeachersFile;
    FloatingActionButton insertTeachersFile;
    TextView choiceFile,addFile;
    EditText edtTitle;
    String StringType  = null;
    byte[] StringBytes = null;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_file);
        findViews();
        getData();
        insertTeachersFileAction();
    }

    private void insertTeachersFileAction() {
        insertTeachersFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(TeachersFileActivity.this);
                dialog.setContentView(R.layout.dialog_insert_teachers_file);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                edtTitle = dialog.findViewById(R.id.edtTitle);
                choiceFile = dialog.findViewById(R.id.choiceFile);
                addFile = dialog.findViewById(R.id.addFile);

                choiceFile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("*/*");
                        startActivityForResult(intent,100);
                    }
                });

                addFile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        insertAPI(StringType,StringBytes);
                    }
                });

                dialog.show();
                dialog.getWindow().setAttributes(lp);
            }
        });
    }

    private void getData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<teachersFile>> call = apiInterface.getTeachersFile(getIntent().getStringExtra("id"));
        call.enqueue(new Callback<List<teachersFile>>() {
            @Override
            public void onResponse(Call<List<teachersFile>> call, Response<List<teachersFile>> response) {
                if(response.code()==200){
                    recyTeachersFile.setAdapter(new CustomAdapterTeachersFile(response.body(),TeachersFileActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<teachersFile>> call, Throwable t) {

            }
        });
    }



    private void findViews() {
        recyTeachersFile = findViewById(R.id.recyTeachersFile);
        insertTeachersFile = findViewById(R.id.insertTeachersFile);
    }



    private void insertAPI(String type ,byte[] bytes) {
        if(type==null || bytes==null){
            return;
        }
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        RequestBody requestFile = RequestBody.create(MediaType.parse("*/*"),bytes);

        MultipartBody.Part file  = MultipartBody.Part.createFormData("file", type , requestFile);
        RequestBody title = RequestBody.create(MediaType.parse("text/plain"),
                edtTitle.getText().toString());

        RequestBody classContainerId = RequestBody.create(MediaType.parse("text/plain"),
                getIntent().getStringExtra("classContainerId"));

        RequestBody classId = RequestBody.create(MediaType.parse("text/plain"),
                getIntent().getStringExtra("id"));

        Call<Boolean> call = apiInterface.insertTeachersFile(file,title,classContainerId,classId);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.code()==200){
                    getData();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    String type = getFileExtension(data.getData());
                    File f = new File("" + data.getData());
                    f.getName();
                    String cuid = "csdsdscdsdc";

                    StringType = cuid +"."+type;
                    Log.i("eeeeeeeee",f.getPath());
                    choiceFile.setText("فایل انتخاب شد");
                    StringBytes = getBytes(inputStream);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = this.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
}