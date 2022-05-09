package com.assignment.online.utils.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.online.EditSalesPersonActivity;
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

public class PersonsAdapter extends RecyclerView.Adapter<PersonHolder> {
    List<Person>list;
    Context context;

    public PersonsAdapter(Context context, List<Person>list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sales_person,viewGroup,false);

        return new PersonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder personHolder, int i) {
        final Person p=list.get(i);

        personHolder.tDate.setText(String.valueOf(p.registerationDate));
        personHolder.tNumber.setText(String.valueOf(p.personNumber));
        personHolder.tName.setText(p.personName);

        final int pNumbere=p.personNumber;

        personHolder.bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteAsync().execute(String.valueOf(p.personNumber));
            }
        });

        personHolder.bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EditSalesPersonActivity.class);
                intent.putExtra(Person.PERSON,p);
                context.startActivity(intent);
            }
        });
    }

    class DeleteAsync extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
            PDialog.showProgressDialog(context,"Deleting...");
        }

        @Override
        protected JSONObject doInBackground(String... voids) {

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(Person.SALES_PERSON_NUMBER,String.valueOf(voids[0])));

            String url= ServerAPIs.DELETE_SALES_PERSON;

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
                    Warnings.show(context, "Deleted susseccfully");
                }
                else{
                    String msg=data.getString(Shared.MESSAGE);
                    Warnings.show(context,msg);
                }
            } catch (Exception e) {

            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
