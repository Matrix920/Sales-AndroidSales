package com.assignment.online;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.assignment.mobileweb.netapp.R;
import com.assignment.online.utils.connections.ObjectHttpPost;
import com.assignment.online.utils.connections.ServerAPIs;
import com.assignment.online.utils.models.Person;
import com.assignment.online.utils.models.Shared;
import com.assignment.online.utils.notifies.PDialog;
import com.assignment.online.utils.notifies.Warnings;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddSalesPersonActivity extends AppCompatActivity{

    String[] regions = { "Southern Region", "Costel Region", "Northen Region", "Eastern Region", "Lebanon"};
    String[] days ={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
    String[] months ={"1","2","3","4","5","6","7","8","9","10","11","12"};
    String[] years ={"2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032","2033","2034","2035","2036","2037","2038","2039","2040"};
    Spinner spinRegion,spinDay,spinMonth,spinYear;
    EditText eName,eNumber,ePass;
    Button bSave,bBack;
    String month,year,day;
    int regionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales_person);
        spinRegion = (Spinner) findViewById(R.id.spinnerRegion);
        spinDay = (Spinner) findViewById(R.id.spinnerDay);
         spinMonth = (Spinner) findViewById(R.id.spinnerMonth);
        spinYear = (Spinner) findViewById(R.id.spinnerYear);

        eName=findViewById(R.id.etxtPName);
        eNumber=findViewById(R.id.etxtPNumber);
        ePass=findViewById(R.id.etxtPass);

        bSave=findViewById(R.id.bSaveSalesPerson);
        bBack=findViewById(R.id.bBackToOptions);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 new AddSync().execute();
            }
        });

        spinRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                regionID=position+1;
                Toast.makeText(AddSalesPersonActivity.this,String.valueOf(regionID) , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter regionAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,regions);
        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRegion.setAdapter(regionAdapter);
        //Creating the ArrayAdapter instance having the country list

        spinMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month=months[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter monthAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,months);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinMonth.setAdapter(monthAdapter);

        spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year=years[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter yearAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinYear.setAdapter(yearAdapter);

        spinDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day=days[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter dayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,days);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDay.setAdapter(dayAdapter);

    }

    class AddSync extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
            PDialog.showProgressDialog(AddSalesPersonActivity.this,"Adding...");
        }

        @Override
        protected JSONObject doInBackground(Void... voids) {

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(Person.PERSON_REGION_ID,String.valueOf(regionID)));
            params.add(new BasicNameValuePair(Person.REGISTERATION_DATE, day+"/"+month+"/"+year));
            params.add(new BasicNameValuePair(Person.PERSON_NAME, eName.getText().toString().trim()));
            params.add(new BasicNameValuePair(Person.PERSON_NUMBER, eNumber.getText().toString().trim()));
            params.add(new BasicNameValuePair(Person.PASSWORD, ePass.getText().toString().trim()));

            String url= ServerAPIs.ADD_SALES_PERSON;

            ObjectHttpPost httpPost = new ObjectHttpPost();
            JSONObject data = httpPost.makeReguest(url,params);
            return data;

        }

        @Override
        protected void onPostExecute(JSONObject data) {
            PDialog.hideProgressDialog();
            try {
                boolean success=data.getBoolean(Shared.SUCCESS);
                if(success) {
                    Warnings.show(AddSalesPersonActivity.this, "added susseccfully");
                    finish();
                }
                else{
                    String msg=data.getString(Shared.MESSAGE);
                    Warnings.show(AddSalesPersonActivity.this,msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
