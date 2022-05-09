package com.assignment.online.utils.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.assignment.mobileweb.netapp.R;

public class PersonHolder extends RecyclerView.ViewHolder {
    public TextView tNumber,tName,tDate;
    public Button bEdit,bDelete;

    public PersonHolder(@NonNull View itemView) {
        super(itemView);

        tName= itemView.findViewById(R.id.textPersonName);
        tNumber=itemView.findViewById(R.id.textPersonNumber);
        tDate=itemView.findViewById(R.id.textRegisterationDate);

        bEdit=itemView.findViewById(R.id.btnEdit);
        bDelete=itemView.findViewById(R.id.btnDelete);
    }
}
