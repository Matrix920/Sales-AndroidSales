package com.assignment.online;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.assignment.mobileweb.netapp.R;
import com.assignment.online.utils.adapters.CommissionsAdapter;
import com.assignment.online.utils.connections.ArrayHttpPost;
import com.assignment.online.utils.connections.ServerAPIs;
import com.assignment.online.utils.models.Commission;
import com.assignment.online.utils.models.Person;
import com.assignment.online.utils.models.Region;
import com.assignment.online.utils.models.Sale;
import com.assignment.online.utils.notifies.PDialog;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements TextWatcher{

    EditText eMonth,eYear,eName;
    String m,y,name;
    RecyclerView rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        eMonth=findViewById(R.id.edtSearchMonth);
        eYear=findViewById(R.id.edtMonthYear);
        eName=findViewById(R.id.edtSearchName);

        rec=findViewById(R.id.recyclerComms);

        eYear.addTextChangedListener(this);
        eMonth.addTextChangedListener(this);
        eName.addTextChangedListener(this);
    }

    private boolean checkValues(){
        if(!TextUtils.isEmpty(eName.getText()) && !TextUtils.isEmpty(eMonth.getText())
        && !TextUtils.isEmpty(eYear.getText())) {
            m=eMonth.getText().toString().trim().toLowerCase();
            y=eYear.getText().toString().trim();
            name=eName.getText().toString().trim();

            return true;
        }
        return false;

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(checkValues()){
            new SeachAsync().execute();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    class SeachAsync extends AsyncTask<Void, Void, JSONArray> {

        @Override
        protected void onPreExecute() {
            PDialog.showProgressDialog(SearchActivity.this,"Searching...");
        }

        @Override
        protected JSONArray doInBackground(Void... voids) {

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(Sale.MONTH, m));
            params.add(new BasicNameValuePair(Sale.YEAR, y));
            params.add(new BasicNameValuePair(Person.NAME, name));

            String url= ServerAPIs.SEARCH;

            ArrayHttpPost httpPost = new ArrayHttpPost();
            JSONArray data = httpPost.makeRequest(url,params);
            return data;

        }

        @Override
        protected void onPostExecute(JSONArray arr) {
            PDialog.hideProgressDialog();
            try {
                List<Commission>commissions=new ArrayList<>();
                for(int i=0;i<arr.length();i++) {

                    JSONObject data=arr.getJSONObject(i);
                    double cost = data.getDouble(Region.COSTAL);
                    double east = data.getDouble(Region.EASTERN);
                    double north = data.getDouble(Region.NORTHERN);
                    double south = data.getDouble(Region.SOUTHERN);
                    double leban = data.getDouble(Region.LEBANON);
                    double comm = data.getDouble(Commission.COMMISSION);
                    int m = data.getInt(Sale.MONTH);
                    int y = data.getInt(Sale.YEAR);
                    int id=data.getInt(Person.PERSON_ID);
                    int comId=data.getInt(Person.PERSON_ID);

                    Commission commission = new Commission(comId, id, south, cost, east, leban, north, comm, m, y);
                    commissions.add(commission);
                }

                CommissionsAdapter adapter = new CommissionsAdapter(SearchActivity.this, commissions);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
                rec.setLayoutManager(layoutManager);
                rec.setItemAnimator(new DefaultItemAnimator());
                rec.setAdapter(adapter);

            } catch (Exception e) {

            }
        }
    }
}
