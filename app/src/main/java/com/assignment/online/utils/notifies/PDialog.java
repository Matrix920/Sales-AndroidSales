package com.assignment.online.utils.notifies;

import android.app.ProgressDialog;
import android.content.Context;


public class PDialog {

    private static ProgressDialog pd;

    public  static void showProgressDialog(Context context, String msg){
        pd=new ProgressDialog(context);
        pd.setTitle("please wait...");
        pd.setMessage(msg);
        pd.setCancelable(false);
        pd.show();
    }

    public static void hideProgressDialog(){
        pd.dismiss();
    }
}
