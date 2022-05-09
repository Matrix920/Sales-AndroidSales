package com.assignment.online.utils.notifies;

import android.content.Context;
import android.widget.Toast;


public class Warnings {

    public static void show(Context context , String msg){
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
    }
}
