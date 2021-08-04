package com.betterschool.co.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.betterschool.co.MainActivity;
import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.login.model.LoginAction;
import com.betterschool.co.login.model.loginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText melliCode,password;
    RadioGroup targetRadioGroup;
    RadioButton teacher,student,headmaster,deputy;
    TextView enter;
    String department = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        if(sharedPreferences.getString("token",null)!=null){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);
        findViews();
        enterAction();
        setRadioAction();
    }

    private void setRadioAction() {
        targetRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (teacher.isChecked()){
                    department = "teacher";
                }else if(student.isChecked()){
                    department = "student";
                }else if(headmaster.isChecked()){
                    department = "headmaster";
                }else if(deputy.isChecked()){
                    department = "deputy";
                }
            }
        });
    }

    private void enterAction() {
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(melliCode.getText().toString().isEmpty()){
                    melliCode.setError("شماه ملی اجباری میباشد");
                }else if(password.getText().toString().isEmpty()){
                    password.setError("کلمه ی عبور را وارد نمایید");
                }else if(department==null){
                    Toast.makeText(LoginActivity.this, "لطفا بخش مربوطه را انتخاب کنید", Toast.LENGTH_SHORT).show();
                }else{
                    SendData();
                }
            }
        });
    }

    private void SendData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<loginModel> call = apiInterface.login(new LoginAction(melliCode.getText().toString(),password.getText().toString(),
                department));
        call.enqueue(new Callback<loginModel>() {
            @Override
            public void onResponse(Call<loginModel> call, Response<loginModel> response) {
                if(response.code()==200){
                    JWT jwt = new JWT(response.body().getToken());
                    Claim test = jwt.getClaim("department");
                    Claim name = jwt.getClaim("name");
                    Claim lastName = jwt.getClaim("lastName");
                    SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token",response.body().getToken());
                    editor.putString("department",test.asString());
                    editor.putString("name",name.asString());
                    editor.putString("lastName",lastName.asString());
                    editor.apply();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<loginModel> call, Throwable t) {

            }
        });
    }

    private void findViews() {
        melliCode = findViewById(R.id.melliCode);
        password = findViewById(R.id.password);
        targetRadioGroup = findViewById(R.id.targetRadioGroup);
        teacher = findViewById(R.id.teacher);
        student = findViewById(R.id.student);
        headmaster = findViewById(R.id.headmaster);
        enter = findViewById(R.id.enter);
        deputy = findViewById(R.id.deputy);
    }
}