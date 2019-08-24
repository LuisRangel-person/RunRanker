package com.kiwi.runranker;

import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageInfo;
import android.view.Window;
import android.widget.TextView;

class AboutDialog {

    AboutDialog(Activity act){
        Dialog dialog = new Dialog(act);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_about);

        TextView tvTitle = dialog.findViewById(R.id.tv_ad_version);
        try {
            PackageInfo pInfo = act.getPackageManager().getPackageInfo(act.getPackageName(), 0);
            tvTitle.setText(String.format("Version %s", pInfo.versionName));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        dialog.show();
    }//Init


}
