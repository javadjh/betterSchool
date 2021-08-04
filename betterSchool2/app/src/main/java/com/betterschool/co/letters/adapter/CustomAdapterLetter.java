package com.betterschool.co.letters.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.betterschool.co.R;
import com.betterschool.co.WebService.APIClient;
import com.betterschool.co.WebService.APIInterface;
import com.betterschool.co.letters.model.letters;
import com.betterschool.co.letters.model.lettersModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomAdapterLetter extends RecyclerView.Adapter<CustomAdapterLetter.viewHolderLetter> {
    List<lettersModel> list;
    Context context;

    public CustomAdapterLetter(List<lettersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NotNull
    @Override
    public viewHolderLetter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderLetter(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_letter,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolderLetter holder, int position) {
        lettersModel item = list.get(position);
        holder.title.setText( "عنوان نامه : " + item.getTitle());
        if(item.getSeen()){
            holder.title.setTextColor(context.getResources().getColor(R.color.black));
        }else{
            holder.title.setTextColor(context.getResources().getColor(R.color.primary));
        }
        holder.description.setText("توضیحات : " + item.getDescription());
        holder.createDate.setText("تاریخ ساخت : " + item.getCreateDate());
        holder.letterCard.setOnClickListener(v -> {
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_single_letter);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            TextView titleDetail = dialog.findViewById(R.id.titleDetail);
            TextView description = dialog.findViewById(R.id.description);
            TextView sender = dialog.findViewById(R.id.sender);
            TextView createDateDetail = dialog.findViewById(R.id.createDateDetail);
            TextView closeDialog = dialog.findViewById(R.id.closeDialog);

            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
            Call<lettersModel> call = apiInterface.getSingleLetter(item.get_id());
            call.enqueue(new Callback<lettersModel>() {
                @Override
                public void onResponse(Call<lettersModel> call, Response<lettersModel> response) {
                    if(response.code()==200){
                        titleDetail.setText("عنوان : " + response.body().getTitle());
                        description.setText("توضیحات : " + response.body().getDescription());
                        sender.setText("فرستنده : " + response.body().getSenderInformation().getName() + " " + response.body().getSenderInformation().getLastName());
                        createDateDetail.setText("ارسال : " + response.body().getCreateDate());
                    }
                }

                @Override
                public void onFailure(Call<lettersModel> call, Throwable t) {

                }
            });
            closeDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
            dialog.getWindow().setAttributes(lp);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolderLetter extends RecyclerView.ViewHolder {
        TextView title,description,createDate;
        CardView letterCard;
        public viewHolderLetter(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            letterCard = itemView.findViewById(R.id.letterCard);
            createDate = itemView.findViewById(R.id.createDate);

        }
    }
}
