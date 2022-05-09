package com.assignment.online;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.assignment.mobileweb.netapp.R;
import com.assignment.online.utils.adapters.PersonsAdapter;
import com.assignment.online.utils.connections.ArrayHttpPost;
import com.assignment.online.utils.connections.ServerAPIs;
import com.assignment.online.utils.models.Person;
import com.assignment.online.utils.models.Sale;
import com.assignment.online.utils.notifies.PDialog;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonsActivity extends AppCompatActivity {

    RecyclerView rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);

        rec=findViewById(R.id.recyViewPersons);

        new GetSalesPersonsSync().execute();
    }

    class GetSalesPersonsSync extends AsyncTask<Void, Void, JSONArray> {

        @Override
        protected void onPreExecute() {
            PDialog.showProgressDialog(PersonsActivity.this,"Addin...");
        }

        @Override
        protected JSONArray doInBackground(Void... voids) {

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(Sale.MONTH, ""));

            String url= ServerAPIs.ALL_SALES_PERSONS;

            ArrayHttpPost httpPost = new ArrayHttpPost();
            JSONArray data = httpPost.makeRequest(url,params);
            return data;

        }

        @Override
        protected void onPostExecute(JSONArray data) {
            PDialog.hideProgressDialog();
            try {
                List<Person>personList=new ArrayList<>();
                for(int i=0;i<data.length();i++){
                    JSONObject p=data.getJSONObject(i);

                    int id=p.getInt(Person.PERSON_ID);
                    int pNumber=p.getInt(Person.PERSON_NUMBER);
                    String pName=p.getString(Person.PERSON_NAME);
                    String pssword=p.getString(Person.PERSON_PASSWORD);
                    String regDate=p.getString(Person.REGISTERATION_DATE);
                    int personRegionId=p.getInt(Person.PERSON_REGION_ID);

                    Person person=new Person(id,pNumber,pName,pssword,"",new Date(regDate),personRegionId);

                    personList.add(person);
                }

                PersonsAdapter adapter = new PersonsAdapter(PersonsActivity.this, personList);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PersonsActivity.this);
                rec.setLayoutManager(layoutManager);
                rec.setItemAnimator(new DefaultItemAnimator());
                rec.setAdapter(adapter);

            } catch (Exception e) {

            }
        }
    }
}
