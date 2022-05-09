package com.assignment.online;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.mobileweb.netapp.R;
import com.assignment.online.utils.connections.ObjectHttpPost;
import com.assignment.online.utils.connections.ServerAPIs;
import com.assignment.online.utils.models.Commission;
import com.assignment.online.utils.models.Person;
import com.assignment.online.utils.models.Region;
import com.assignment.online.utils.models.Sale;
import com.assignment.online.utils.notifies.PDialog;
import com.assignment.online.utils.sessions.Controller;
import com.assignment.online.utils.sessions.SessionManagement;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SalesActivity extends AppCompatActivity {

    EditText eMonth,eYear,eSouth,eEast,eNorth,eCost,eLeban;
    Button bAdd;
    TextView tName,tDate,tRegion;
    ImageView img;
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        tDate=findViewById(R.id.textDate);
        tRegion=findViewById(R.id.textRegion);
        tName=findViewById(R.id.textName);

        eMonth=findViewById(R.id.etxtSalesMonth);
        eYear=findViewById(R.id.etxtSalesYear);
        eSouth=findViewById(R.id.etxtSalesSouth);
        eNorth=findViewById(R.id.etxtSalesNorth);
        eEast=findViewById(R.id.etxtSalesEastern);
        eCost=findViewById(R.id.etxtSalesCoastel);
        eLeban=findViewById(R.id.etxtSalesLebanon);

        img=findViewById(R.id.imgSales);

        bAdd=findViewById(R.id.btnSalesAdd);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SaveSalesAsync().execute();
            }
        });

        session= Controller.getSession();

        setData();
    }

    private void setData() {
        tDate.setText(session.getDate());
        tName.setText(session.getName());
        tRegion.setText(session.getRegion());

        new DownloadImageTask().execute();

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SaveSalesAsync().execute();
            }
        });
    }


    private class DownloadImageTask extends AsyncTask<Void, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PDialog.showProgressDialog(SalesActivity.this,"Loading Picture");
        }

        protected Bitmap doInBackground(Void... params) {
            String url=session.getImage();
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            PDialog.hideProgressDialog();
            img.setImageBitmap(result);
        }
    }

    class SaveSalesAsync extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
            PDialog.showProgressDialog(SalesActivity.this,"saving...");
        }

        @Override
        protected JSONObject doInBackground(Void... voids) {

            String m=eMonth.getText().toString().trim();
            String y=eYear.getText().toString().trim();
            String south=eSouth.getText().toString().trim();
            String north=eNorth.getText().toString().trim();
            String east=eEast.getText().toString().trim();
            String cost=eCost.getText().toString().trim();
            String leban=eLeban.getText().toString().trim();

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(Sale.MONTH, m));
            params.add(new BasicNameValuePair(Sale.YEAR, y));
            params.add(new BasicNameValuePair(Region.COASTAL,cost));
            params.add(new BasicNameValuePair(Region.SOUTHERN,south));
            //params.add(new BasicNameValuePair(Region.SOUTHERN,"9000000"));
            params.add(new BasicNameValuePair(Region.NORTHERN,north));
            params.add(new BasicNameValuePair(Region.EASTERN,east));
            params.add(new BasicNameValuePair(Region.LEBANON,leban));
            params.add(new BasicNameValuePair(Person.PERSON_REGION_ID,session.getRegion()));
            params.add(new BasicNameValuePair(Person.PERSON_NUMBER,session.getPersonId()));

            String url= ServerAPIs.INSERT_SALES;

            ObjectHttpPost httpPost = new ObjectHttpPost();
            JSONObject data = httpPost.makeReguest(url, params);
            return data;

        }
        @Override
        protected void onPostExecute(JSONObject data) {

            PDialog.hideProgressDialog();
            try {

                double cost=data.getDouble(Region.COSTAL);
                double east=data.getDouble(Region.EASTERN);
                double north=data.getDouble(Region.NORTHERN);
                double south=data.getDouble(Region.SOUTHERN);
                double leban=data.getDouble(Region.LEBANON);
                double comm=data.getDouble(Commission.COMMISSION);
                int m=data.getInt(Sale.MONTH);
                int y=data.getInt(Sale.YEAR);

                Commission commission=new Commission(0,0,south,cost,east,leban,north,comm,m,y);
                Intent intent=new Intent(SalesActivity.this,SalesCommissionActivity.class);
                intent.putExtra(Commission.COMMISSION,commission);
                startActivity(intent);
                finish();


            } catch (Exception e) {

            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
