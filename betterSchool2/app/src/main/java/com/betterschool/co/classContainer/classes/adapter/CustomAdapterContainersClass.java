package com.betterschool.co.classContainer.classes.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.classContainer.classes.model.ClassRoot;
import com.betterschool.co.classContainer.classes.model.setExamModel;
import com.betterschool.co.utilityClass.StringPayload;
import com.betterschool.co.utilityClass.datePickerClass;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomAdapterContainersClass extends RecyclerView.Adapter<CustomAdapterContainersClass.viewHolderContainersClass> {
    List<ClassRoot> list;
    Context context;

    String valueFirstFinalExam;
    String valueFirstExam;
    String valueSecondFinalExam;
    String valueSecondExam;

    public CustomAdapterContainersClass(List<ClassRoot> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolderContainersClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderContainersClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolderContainersClass holder, int position) {
        ClassRoot item = list.get(position);
        holder.teacherName.setText("معلم : " + item.getTeacher().getName() + " " + item.getTeacher().getLastName());
        holder.timeStart.setText("ساعت : " + item.getTimeStart());
        switch (item.getDayStart()){
            case 0:
                holder.dayStart.setText("شنبه");
                break;
            case 1:
                holder.dayStart.setText("یک شنبه");
                break;
            case 2:
                holder.dayStart.setText("دو شنبه");
                break;
            case 3:
                holder.dayStart.setText("سه شنبه");
                break;
            case 4:
                holder.dayStart.setText("چهار شنبه");
                break;
            case 5:
                holder.dayStart.setText("پنج شنبه");
                break;
            case 6:
                holder.dayStart.setText("جمعه");
                break;
        }
        holder.name.setText("کلاس : " + item.getName());
        holder.setExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_set_exam);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView firstFinalExam = dialog.findViewById(R.id.firstFinalExam);
                TextView lastFirstFinalExam = dialog.findViewById(R.id.lastFirstFinalExam);
                TextView firstExam = dialog.findViewById(R.id.firstExam);
                TextView lastFirstExam = dialog.findViewById(R.id.lastFirstExam);
                TextView secondFinalExam = dialog.findViewById(R.id.secondFinalExam);
                TextView lastSecondFinalExam = dialog.findViewById(R.id.lastSecondFinalExam);
                TextView secondExam = dialog.findViewById(R.id.secondExam);
                TextView lastSecondExam = dialog.findViewById(R.id.lastSecondExam);
                TextView submitChanges = dialog.findViewById(R.id.submitChanges);

                lastFirstFinalExam.setText(item.getFirstFinalExam()==null?"انتخاب نشده":item.getFirstFinalExam());
                lastFirstExam.setText(item.getFirstExam()==null?"انتخاب نشده":item.getFirstExam());
                lastSecondFinalExam.setText(item.getSecondFinalExam()==null?"انتخاب نشده":item.getSecondFinalExam());
                lastSecondExam.setText(item.getSecondExam()==null?"انتخاب نشده":item.getSecondExam());


                valueFirstFinalExam = item.getFirstFinalExam();
                valueFirstExam = item.getFirstExam();
                valueSecondFinalExam = item.getSecondFinalExam();
                valueSecondExam = item.getSecondExam();


                firstFinalExam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datePickerClass.datePicker(context, new StringPayload() {
                            @Override
                            public void stringPicker(String date,String dateView) {
                                valueFirstFinalExam = date;
                                lastFirstFinalExam.setText(dateView);
                            }
                        });
                    }
                });


                firstExam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datePickerClass.datePicker(context, new StringPayload() {
                            @Override
                            public void stringPicker(String date,String dateView) {
                                valueFirstExam = date;
                                lastFirstExam.setText(dateView);
                            }
                        });
                    }
                });

                secondFinalExam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datePickerClass.datePicker(context, new StringPayload() {
                            @Override
                            public void stringPicker(String date,String dateView) {
                                valueSecondFinalExam = date;
                                lastSecondFinalExam.setText(dateView);
                            }
                        });
                    }
                });

                secondExam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datePickerClass.datePicker(context, new StringPayload() {
                            @Override
                            public void stringPicker(String date,String dateView) {
                                valueSecondExam = date;
                                lastSecondExam.setText(dateView);
                            }
                        });
                    }
                });

                submitChanges.setOnClickListener(v1 -> {
                    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                    Call<Boolean> call = apiInterface.setExam(new setExamModel(
                            valueFirstExam,
                            valueFirstFinalExam,
                            valueSecondExam,
                            valueSecondFinalExam,
                            item.get_id()
                    ));
                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if(response.code()==200){
                                Toast.makeText(context, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {

                        }
                    });
                });

                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        valueFirstFinalExam = null;
                        valueFirstExam = null;
                        valueSecondFinalExam = null;
                        valueSecondExam = null;
                    }
                });

                dialog.show();
                dialog.getWindow().setAttributes(lp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderContainersClass extends RecyclerView.ViewHolder {
        TextView name,teacherName,dayStart,timeStart,setExam;
        public viewHolderContainersClass(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            teacherName = itemView.findViewById(R.id.teacherName);
            dayStart = itemView.findViewById(R.id.dayStart);
            timeStart = itemView.findViewById(R.id.timeStart);
            setExam = itemView.findViewById(R.id.setExam);
        }
    }
}
