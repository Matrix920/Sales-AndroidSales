package com.assignment.online;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.assignment.mobileweb.netapp.R;
import com.assignment.online.utils.sessions.SessionManagement;

public class SalesPersonActivity extends AppCompatActivity {

    ImageView img;
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_person);

        img=findViewById(R.id.imgSalesPerson);



        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SalesPersonActivity.this,SalesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        SessionManagement.getSession(getApplicationContext()).endSession();
        super.onBackPressed();
    }
}
