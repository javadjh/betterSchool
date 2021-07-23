package com.betterschool.co.students.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.students.model.students;
import com.betterschool.co.utilityClass.payload;
import com.betterschool.co.utilityClass.questionDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomAdapterStudents extends RecyclerView.Adapter<CustomAdapterStudents.viewHolderStudents> {
    List<students> list ;
    Context context;
    payload payload;

    public CustomAdapterStudents(List<students> list, Context context, com.betterschool.co.utilityClass.payload payload) {
        this.list = list;
        this.context = context;
        this.payload = payload;
    }


    @NonNull
    @Override
    public viewHolderStudents onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderStudents(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CustomAdapterStudents.viewHolderStudents holder, int position) {
        students item = list.get(position);
        holder.indexCounter.setText(position+1+"");
        holder.name.setText("نام : " + item.getName());
        holder.lastName.setText("خانوادگی : " + item.getLastName());
        holder.melliCode.setText(" ملی : " + item.getMelliCode());
        holder.fathersName.setText("پدر : "  + item.getFathersName());
        switch (item.getGrade()){
            case 1:
                holder.grade.setText("مقطع : اول");
                break;
            case 2:
                holder.grade.setText("مقطع : دوم");
                break;
            case 3:
                holder.grade.setText("مقطع : سه");
                break;
            case 4:
                holder.grade.setText("مقطع : چهار");
                break;
            case 5:
                holder.grade.setText("مقطع : پنج");
                break;
        }
        holder.createDate.setText("ساخت : " + item.getCreateDate());
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
        if(holder.indexCounter.getText().toString().contains("+")){
            holder.indexCounter.setText(position + 1 +"+");
            holder.actionBlock.setVisibility(View.VISIBLE);
        }else{
            holder.indexCounter.setText(position +1 +"");
            holder.actionBlock.setVisibility(View.GONE);
        }
        holder.deleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionDialog.show(context, "آیا از حذف دانش آموز " + item.getLastName() + " مطمعن هستید؟", new payload() {
                    @Override
                    public void payload(Boolean isChange) {
                        if(isChange){
                            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                            Call<Boolean> call = apiInterface.deleteStudent(item.get_id());
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
                                    Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderStudents extends RecyclerView.ViewHolder {
        TextView indexCounter,name,lastName,melliCode,fathersName,grade,createDate,deleteStudent;
        CardView informationCard,actionBlock;
        public viewHolderStudents(@NonNull View itemView) {
            super(itemView);
            indexCounter = itemView.findViewById(R.id.indexCounter);
            name = itemView.findViewById(R.id.name);
            lastName = itemView.findViewById(R.id.lastName);
            melliCode = itemView.findViewById(R.id.melliCode);
            fathersName = itemView.findViewById(R.id.fathersName);
            grade = itemView.findViewById(R.id.grade);
            createDate = itemView.findViewById(R.id.createDate);
            informationCard = itemView.findViewById(R.id.informationCard);
            actionBlock = itemView.findViewById(R.id.actionBlock);
            deleteStudent = itemView.findViewById(R.id.deleteStudent);
        }
    }
}
