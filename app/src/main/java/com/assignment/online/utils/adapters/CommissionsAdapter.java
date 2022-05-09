package com.assignment.online.utils.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.mobileweb.netapp.R;
import com.assignment.online.utils.models.Commission;

import java.util.List;

public class CommissionsAdapter extends RecyclerView.Adapter<CommissionHolder> {

    Context context;
    List<Commission>list;

    public CommissionsAdapter(@NonNull Context context,List<Commission>list) {
        this.context=context;
        this.list=list;
    }



    @NonNull
    @Override
    public CommissionHolder onCreateViewHolder(@NonNull ViewGroup v, int i) {
        View view=LayoutInflater.from(v.getContext()).inflate(R.layout.item_commission,v,false);

        return  new CommissionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommissionHolder holder, int i) {
        Commission com=list.get(i);

//        holder.txtRegisterDate.setText(String.valueOf(com.registerationDate));
        holder.txtNorth.setText(String.valueOf(com.northern));
        holder.txtLebanon.setText(String.valueOf(com.lebanon));
        holder.txtSouth.setText(String.valueOf(com.southern));
        holder.txtEast.setText(String.valueOf(com.eastern));
        holder.txtCostal.setText(String.valueOf(com.costal));
        holder.txtNumber.setText(String.valueOf(com.personId));
        holder.txtComm.setText(String.valueOf(com.commission));
        holder.txtMonth.setText(String.valueOf(com.month));
        holder.txtYear.setText(String.valueOf(com.year));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}


