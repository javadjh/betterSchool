package com.betterschool.co.teachers.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.teachers.insertTeacher.InsertTeacherActivity;
import com.betterschool.co.teachers.model.teachers;
import com.betterschool.co.utilityClass.payload;
import com.betterschool.co.utilityClass.questionDialog;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomAdapterTeachers extends RecyclerView.Adapter<CustomAdapterTeachers.viewHolderTeachers> {
    Context context;
    List<teachers> list;
    payload payload;

    public CustomAdapterTeachers(Context context, List<teachers> list, com.betterschool.co.utilityClass.payload payload) {
        this.context = context;
        this.list = list;
        this.payload = payload;
    }


    @NonNull
    @Override
    public viewHolderTeachers onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewHolderTeachers(LayoutInflater.from(context).inflate(R.layout.item_teacher,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolderTeachers holder, int position) {
        teachers item = list.get(position);
        holder.name.setText("نام : " + item.getName());
        holder.lastName.setText("خانوادگی : " + item.getLastName());
        holder.title.setText("عنوان : " + item.getTitle());
        holder.melliCode.setText("ملی : " + item.getMelliCode());
        holder.createDate.setText("ساخت : " + item.getCreateDate());
        holder.indexCounter.setText(position + 1+ "");
        holder.informationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.indexCounter.getText().toString().contains("+")){
                    holder.indexCounter.setText(position + 1 +"");
                    holder.actionBlock.setVisibility(View.GONE);
                }else{
                    holder.indexCounter.setText(position +1 +"+");
                    holder.actionBlock.setVisibility(View.VISIBLE);
                }
            }
        });
        holder.deleteTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionDialog.show(context, "آیا از حذف معلم " + item.getLastName() + " اطمینان دارید؟", new payload() {
                    @Override
                    public void payload(Boolean isChange) {
                        if(isChange){
                            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                            Call<Boolean> call = apiInterface.deleteTeacher(item.get_id());
                            call.enqueue(new Callback<Boolean>() {
                                @Override
                                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                    if(response.code()==200){
                                        Toast.makeText(context, "با موفقیت حذف شد", Toast.LENGTH_SHORT).show();
                                        payload.payload(true);
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
        holder.editTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomAdapterTeachers.this.context, InsertTeacherActivity.class);
                intent.putExtra("id",item.get_id());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderTeachers extends RecyclerView.ViewHolder {
        TextView name,lastName,melliCode,title,createDate,indexCounter,editTeacher,deleteTeacher;
        CardView informationCard,actionBlock;
        public viewHolderTeachers(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            lastName = itemView.findViewById(R.id.lastName);
            melliCode = itemView.findViewById(R.id.melliCode);
            title = itemView.findViewById(R.id.title);
            createDate = itemView.findViewById(R.id.createDate);
            indexCounter = itemView.findViewById(R.id.indexCounter);
            informationCard = itemView.findViewById(R.id.informationCard);
            editTeacher = itemView.findViewById(R.id.editTeacher);
            deleteTeacher = itemView.findViewById(R.id.deleteTeacher);
            actionBlock = itemView.findViewById(R.id.actionBlock);
        }
    }
}
