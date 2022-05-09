package com.assignment.online.utils.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.assignment.mobileweb.netapp.R;

public class CommissionHolder extends RecyclerView.ViewHolder {
    TextView txtName,txtNumber,txtCostal,txtEast,txtSouth,txtLebanon,txtNorth,txtComm,txtRegisterDate,txtMonth,txtYear;
    Button btnEdit,btnDelete;

    public CommissionHolder(View v) {
        super(v);
        txtComm=v.findViewById(R.id.textComCommission);
        txtCostal=v.findViewById(R.id.textComCoastel);
        txtEast=v.findViewById(R.id.textComEastern);
        txtName=v.findViewById(R.id.textName);
        txtNumber=v.findViewById(R.id.textComNumber);
        txtSouth=v.findViewById(R.id.textComSouth);
        txtLebanon=v.findViewById(R.id.textComLebanon);
        txtNorth=v.findViewById(R.id.textComNorth);
        txtRegisterDate=v.findViewById(R.id.textRegisterationDate);
        txtMonth=v.findViewById(R.id.textComMonth);
        txtYear=v.findViewById(R.id.textComYear);

        btnEdit=v.findViewById(R.id.btnEdit);
        btnDelete=v.findViewById(R.id.btnDelete);
    }
}
