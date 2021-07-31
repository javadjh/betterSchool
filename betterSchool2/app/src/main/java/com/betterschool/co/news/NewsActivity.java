package com.betterschool.co.news;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.betterschool.co.MainActivity;
import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.news.adapter.CustomAdapterNews;
import com.betterschool.co.news.model.news;
import com.betterschool.co.news.model.newsRoot;
import com.betterschool.co.teachers.model.teachers;
import com.betterschool.co.utilityClass.IntentBetweenActivity;
import com.betterschool.co.weeklySchedule.WeeklyScheduleActivity;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

public class NewsActivity extends AppCompatActivity {
    RecyclerView recyNews;
    int pageId = 1;
    NestedScrollView scroll;
    SpinKitView progress;
    Boolean inGettingData = false;
    List<news> list = new ArrayList<>();
    FloatingActionButton insertNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        findViews();
        setMenu();
        getData(false);
        paging();
        insertAction();
        enable_button();
    }

    private void insertAction() {
        insertNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(NewsActivity.this);

            }
        });
    }

    private void paging() {
        scroll.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(scrollY==v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight() && !inGettingData) {
                pageId++;
                getData(true);
            }
        });
    }

    private void findViews() {
        scroll = findViewById(R.id.scroll);
        insertNews = findViewById(R.id.insertNews);
        progress = findViewById(R.id.progress);
        recyNews = findViewById(R.id.recyNews);
    }

    private void getData(Boolean isPaging) {
        progress.setVisibility(View.VISIBLE);
        inGettingData = true;
        if(!isPaging){
            list.clear();
            pageId = 1;
        }
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<newsRoot> call = apiInterface.getNews(pageId,12);
        call.enqueue(new Callback<newsRoot>() {
            @Override
            public void onResponse(Call<newsRoot> call, Response<newsRoot> response) {
                progress.setVisibility(View.INVISIBLE);
                inGettingData = false;
                if(response.code()==200) {
                    if (response.body().getPageId() >= pageId || pageId == 1) {
                        list = new ArrayList<>(list);
                        list.addAll(response.body().getNews());
                        recyNews.setAdapter(new CustomAdapterNews(list));
                    }
                }
            }

            @Override
            public void onFailure(Call<newsRoot> call, Throwable t) {
                progress.setVisibility(View.INVISIBLE);
                inGettingData = false;
            }
        });
    }

    private void setMenu() {
        IntentBetweenActivity intentBetweenActivity = new IntentBetweenActivity();
        LinearLayout home,message,news,classes;
        ImageView homeIcon,messageIcon,newsIcon,classesIcon;
        TextView homeTitle,messageTitle,newsTitle,classesTitle;

        homeIcon = findViewById(R.id.homeIcon);
        newsIcon = findViewById(R.id.newsIcon);
        classesIcon = findViewById(R.id.classesIcon);
        messageIcon = findViewById(R.id.messageIcon);
        homeTitle = findViewById(R.id.homeTitle);
        messageTitle = findViewById(R.id.messageTitle);
        newsTitle = findViewById(R.id.newsTitle);
        classesTitle = findViewById(R.id.classesTitle);

        homeTitle.setTextColor(getResources().getColor(R.color.disable));
        messageTitle.setTextColor(getResources().getColor(R.color.disable));
        newsTitle.setTextColor(getResources().getColor(R.color.primary));
        classesTitle.setTextColor(getResources().getColor(R.color.disable));

        homeIcon.setColorFilter(ContextCompat.getColor(NewsActivity.this, R.color.disable), android.graphics.PorterDuff.Mode.MULTIPLY);
        messageIcon.setColorFilter(ContextCompat.getColor(NewsActivity.this, R.color.disable), android.graphics.PorterDuff.Mode.MULTIPLY);
        newsIcon.setColorFilter(ContextCompat.getColor(NewsActivity.this, R.color.primary), android.graphics.PorterDuff.Mode.MULTIPLY);
        classesIcon.setColorFilter(ContextCompat.getColor(NewsActivity.this, R.color.disable), android.graphics.PorterDuff.Mode.MULTIPLY);

        home = findViewById(R.id.home);
        message = findViewById(R.id.message);
        news = findViewById(R.id.news);
        classes = findViewById(R.id.classes);

        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        if(sharedPreferences.getString("department","").equals("student")){
            classes.setVisibility(View.VISIBLE);
        }

        intentBetweenActivity.startIntent(false,home,message,news,classes,NewsActivity.this, MainActivity.class, NewsActivity.class,
                NewsActivity.class, WeeklyScheduleActivity.class);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            sendToUploadActivity();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
        }
    }

    private void enable_button() {

        insertNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent,100);
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
//                    String cuid = new Cuid(this).createCuid();
                    Log.i("rrrrtrt",f.toString());

                    SendFileForUpload("scsdcsdsd" +"."+type, getBytes(inputStream));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void SendFileForUpload(String type ,byte[] bytes) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        RequestBody requestFile = RequestBody.create(MediaType.parse("*/*"),bytes);

        MultipartBody.Part file  = MultipartBody.Part.createFormData("imageUrl", type , requestFile);
        RequestBody title = RequestBody.create(MediaType.parse("text/plain"),
                "scsdcsdsdscsdcsd");
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"),
                "descriptiondescriptiondescriptiondescriptiondescription");

        Call<Boolean> call = apiInterface.insertNews(file,title,description);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });

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
    private void sendToUploadActivity() {
        insertNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enable_button();
            }
        });
    }
}