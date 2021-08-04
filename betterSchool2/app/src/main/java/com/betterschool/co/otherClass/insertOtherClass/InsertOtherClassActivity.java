package com.betterschool.co.otherClass.insertOtherClass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classContainer.classes.InsertClassActivity;
import com.betterschool.co.classContainer.classes.adapter.CustomAdapterTeachers;
import com.betterschool.co.teachers.model.teachers;
import com.betterschool.co.utilityClass.StringPayload;
import com.betterschool.co.utilityClass.datePickerClass;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertOtherClassActivity extends AppCompatActivity {
    RecyclerView recyTeachers;
    TextView teacherName,startDateTV,filePicker,endDateTV,insertBtn;
    String teacherId = null;
    Integer grade = 1;
    String startDate = null;
    String endDate = null;
    List<String> listGrade = new ArrayList<>();
    List<String> listGradeForSpinner = new ArrayList<>();
    MaterialSpinner gradeSpinner;
    EditText edtTitle,edtPrice,edtMinParticipant,edtMaxParticipant,edtDescription,edtTimeStart,edtSessionsCount;
    String StringType  = null;
    byte[] StringBytes = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_other_class);
        findViews();
        getTeachers();
        setSpinner();
        setDatePicker();
        filePickerLogic();
        insertAction();
    }

    private void insertAction() {
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAPI(StringType,StringBytes);
            }
        });
    }

    private void filePickerLogic() {
        filePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent,100);
            }
        });
    }

    private void insertAPI(String type ,byte[] bytes) {
        if(type==null || bytes==null){
            return;
        }
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        RequestBody requestFile = RequestBody.create(MediaType.parse("*/*"),bytes);

        MultipartBody.Part file  = MultipartBody.Part.createFormData("image", type , requestFile);
        RequestBody title = RequestBody.create(edtTitle.getText().toString(),MediaType.parse("text/plain"));
        RequestBody price = RequestBody.create(edtPrice.getText().toString(),MediaType.parse("text/plain"));
        RequestBody minParticipant = RequestBody.create(edtMinParticipant.getText().toString(),MediaType.parse("text/plain"));
        RequestBody maxParticipant = RequestBody.create(edtMaxParticipant.getText().toString(),MediaType.parse("text/plain"));
        RequestBody gradeBody = RequestBody.create(grade +"",MediaType.parse("text/plain"));
        RequestBody startDateBody = RequestBody.create(startDate,MediaType.parse("text/plain"));
        RequestBody endDateBody = RequestBody.create(endDate,MediaType.parse("text/plain"));
        RequestBody description = RequestBody.create(edtDescription.getText().toString(),MediaType.parse("text/plain"));
        RequestBody teacherIdBody = RequestBody.create(teacherId,MediaType.parse("text/plain"));
        RequestBody sessionsCount = RequestBody.create(edtSessionsCount.getText().toString(),MediaType.parse("text/plain"));
        RequestBody timeStart = RequestBody.create(edtTimeStart.getText().toString(),MediaType.parse("text/plain"));

        Call<Boolean> call = apiInterface.insertOtherClass(file,title,description,minParticipant,maxParticipant,startDateBody,
                endDateBody,price,sessionsCount,teacherIdBody,timeStart,gradeBody);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.code()==200){
                    Toast.makeText(InsertOtherClassActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });

    }

    private void setDatePicker() {
        startDateTV.setOnClickListener(v -> datePickerClass.datePicker(InsertOtherClassActivity.this, (date, dateView) -> {
            startDate = date;
            startDateTV.setText(dateView);
        }));
        endDateTV.setOnClickListener(v -> datePickerClass.datePicker(InsertOtherClassActivity.this, (date, dateView) -> {
            endDate = date;
            endDateTV.setText(dateView);
        }));
    }

    private void setSpinner() {
        listGrade.add("1");
        listGrade.add("2");
        listGrade.add("3");
        listGradeForSpinner.add("اول");
        listGradeForSpinner.add("دوم");
        listGradeForSpinner.add("سوم");
        gradeSpinner.setItems(listGradeForSpinner);
        gradeSpinner.setOnItemSelectedListener((view, position, id, item) -> {
            grade = Integer.parseInt(listGrade.get(position));
        });
    }

    private void findViews() {
        recyTeachers = findViewById(R.id.recyTeachers);
        teacherName = findViewById(R.id.teacherName);
        gradeSpinner = findViewById(R.id.gradeSpinner);
        startDateTV = findViewById(R.id.startDateTV);
        filePicker = findViewById(R.id.filePicker);
        edtTitle = findViewById(R.id.edtTitle);
        edtPrice = findViewById(R.id.edtPrice);
        edtMinParticipant = findViewById(R.id.edtMinParticipant);
        edtMaxParticipant = findViewById(R.id.edtMaxParticipant);
        endDateTV = findViewById(R.id.endDateTV);
        edtDescription = findViewById(R.id.edtDescription);
        edtTimeStart = findViewById(R.id.edtTimeStart);
        edtSessionsCount = findViewById(R.id.edtSessionsCount);
        insertBtn = findViewById(R.id.insertBtn);
    }

    private void getTeachers() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<teachers>> call = apiInterface.getAllTeachers();
        call.enqueue(new Callback<List<teachers>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<teachers>> call, Response<List<teachers>> response) {
                recyTeachers.setAdapter(new CustomAdapterTeachers(InsertOtherClassActivity.this, response.body(), ts -> {
                    teacherId = ts.get_id();
                    teacherName.setText("معلم : " + ts.getName() + " " + ts.getLastName());
                }));
            }

            @Override
            public void onFailure(Call<List<teachers>> call, Throwable t) {

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
                    filePicker.setText("فایل انتخاب شد");
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