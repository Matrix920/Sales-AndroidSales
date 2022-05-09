package com.assignment.online;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.assignment.mobileweb.netapp.R;
import com.assignment.online.utils.sessions.SessionManagement;

public class AdminActivity extends AppCompatActivity {

    SessionManagement session;
    Button bEdit,bAdd,bSearch,bLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        bEdit=findViewById(R.id.btnEdit);
        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminActivity.this,PersonsActivity.class);
                startActivity(i);
            }
        });

        bAdd=findViewById(R.id.btnAdd);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminActivity.this,AddSalesPersonActivity.class);
                startActivity(i);
            }
        });

        bSearch=findViewById(R.id.btnSearch);
        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminActivity.this,SearchActivity.class);
                startActivity(i);
            }
        });

        bLogout=findViewById(R.id.btnLogoutAdmin);
        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManagement.getSession(getApplicationContext()).endSession();
            }
        });

        session=SessionManagement.getSession(getApplicationContext());
     //   session.checkLogin();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //session.checkLogin();
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}
