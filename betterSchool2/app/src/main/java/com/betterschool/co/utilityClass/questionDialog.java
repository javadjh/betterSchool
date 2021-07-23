package com.betterschool.co.utilityClass;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.betterschool.co.R;

public class questionDialog {
    public static void show(Context context,String title,payload payload){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_question);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView titleQuestion = dialog.findViewById(R.id.titleQuestion);
        TextView doAction = dialog.findViewById(R.id.doAction);
        TextView dontDo = dialog.findViewById(R.id.dontDo);
        titleQuestion.setText(title);
        doAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payload.payload(true);
                dialog.dismiss();
            }
        });
        dontDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payload.payload(false);
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
}
