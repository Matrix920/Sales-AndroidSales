package com.assignment.online;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.assignment.mobileweb.netapp.R;
import com.assignment.online.utils.connections.ObjectHttpPost;
import com.assignment.online.utils.connections.ServerAPIs;
import com.assignment.online.utils.models.Person;
import com.assignment.online.utils.notifies.PDialog;
import com.assignment.online.utils.notifies.Warnings;
import com.assignment.online.utils.sessions.SessionManagement;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText login,pass;
    Button bLogin;
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.etxtLogin);
        pass=findViewById(R.id.etxtPass);

        bLogin=findViewById(R.id.btnLogin);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginSync().execute();
            }
        });

        session= SessionManagement.getSession(getApplicationContext());

        session.checkLogin();
    }

    class LoginSync extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
            PDialog.showProgressDialog(MainActivity.this,"Logging in");
        }

        @Override
        protected JSONObject doInBackground(Void... voids) {

            String username = login.getText().toString().trim().toLowerCase();
            String password = pass.getText().toString();

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(Person.USERNAME, username));

            params.add(new BasicNameValuePair(Person.PASSWORD, password));

            String url= ServerAPIs.LOGIN;

            ObjectHttpPost httpPost = new ObjectHttpPost();
            JSONObject data = httpPost.makeReguest(url, params);
            return data;

        }

        @Override
        protected void onPostExecute(JSONObject data) {
            PDialog.hideProgressDialog();
            try {
                boolean success = data.getBoolean(Person.LOGIN);
                if (success) {
                    boolean admiLogin = data.getBoolean(Person.ADMIN);
                    if (!admiLogin) {

                        int personNumber=data.getInt(Person.PERSON_NUMBER);
                        int personId=data.getInt(Person.PERSON_ID);
                        int personRegionId=data.getInt(Person.PERSON_REGION_ID);
                        String personName=data.getString(Person.PERSON_NAME);
                        String img=data.getString(Person.IMAGE);
                        String regDate=data.getString(Person.REGISTERATION_DATE);

                        session.startSalesPersonSession(String.valueOf(personNumber),String.valueOf(personRegionId),personName,img,regDate,String.valueOf(personId));
                        finish();
                    }else{
                        session.startAdminSession();
                        finish();
                    }



                } else {
                    Warnings.show(MainActivity.this,"Authentication error");
                }
            } catch (Exception e) {

            }


        }
    }


    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}
