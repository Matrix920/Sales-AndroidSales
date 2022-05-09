package com.assignment.online;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.assignment.mobileweb.netapp.R;
import com.assignment.online.utils.models.Commission;
import com.assignment.online.utils.sessions.SessionManagement;

public class SalesCommissionActivity extends AppCompatActivity {

    SessionManagement session;
    TextView tCost,tSouth,tNorth,tLeban,tEast,tMonth,tYear,tName,tRegisterDate,tComm;
    Button bInsert,bLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_commission);
        
        tCost=findViewById(R.id.textComCoastel);
        tSouth=findViewById(R.id.textComSouth);
        tName=findViewById(R.id.textComName);
        tNorth=findViewById(R.id.textComNorth);
        tEast=findViewById(R.id.textComEastern);
        tLeban=findViewById(R.id.textComLebanon);
        tMonth=findViewById(R.id.textComMonth);
        tYear=findViewById(R.id.textComYear);
        tRegisterDate=findViewById(R.id.textComRegisterD);
        tComm=findViewById(R.id.textComCommission);

        bLogout=findViewById(R.id.btnLogoutCom);
        bInsert=findViewById(R.id.btnNewInsert);

        session= SessionManagement.getSession(getApplicationContext());

        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.endSession();
            }
        });

        bInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SalesCommissionActivity.this,SalesPersonActivity.class);
                startActivity(i);
                finish();
            }
        });

        setData();
    }

    private void setData() {
        Intent intent=getIntent();
        Commission com=(Commission)intent.getSerializableExtra(Commission.COMMISSION);

        tCost.setText(String.valueOf(com.costal));
        tSouth.setText(String.valueOf(com.southern));
        tName.setText(session.getName());
        tNorth.setText(String.valueOf(com.northern));
        tEast.setText(String.valueOf(com.eastern));
        tLeban.setText(String.valueOf(com.lebanon));
        tMonth.setText(String.valueOf(com.month));
        tYear.setText(String.valueOf(com.year));
        tRegisterDate.setText(session.getRegDate());
        tComm.setText(String.valueOf(com.commission));
    }
}
