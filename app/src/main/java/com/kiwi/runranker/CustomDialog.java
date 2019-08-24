package com.kiwi.runranker;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

class CustomDialog {//This is a class to deliver a message to the user

    CustomDialog(Activity act, String title, String message, Boolean showPos, Boolean showNeg){
        final Dialog dialog = new Dialog(act);//Give context
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom);

        TextView tvTitle = dialog.findViewById(R.id.dialog_title);
        tvTitle.setText(title);

        TextView tvMessage = dialog.findViewById(R.id.dialog_message);
        tvMessage.setText(message);

        Button buPositive = dialog.findViewById(R.id.dialog_positiveButton);
        if(showPos){//Do we show the positive button
            buPositive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }//Show Positive Button
        else{
            buPositive.setVisibility(View.GONE);
        }

        Button buNegative = dialog.findViewById(R.id.dialog_negativeButton);
        if(showNeg){//Do we show the negative button
            buNegative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }
        else{
            buNegative.setVisibility(View.GONE);
        }
        dialog.show();
    }//Init
}
