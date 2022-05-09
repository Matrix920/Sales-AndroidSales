package com.assignment.online.utils.sessions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.assignment.online.AdminActivity;
import com.assignment.online.MainActivity;
import com.assignment.online.SalesPersonActivity;
import com.assignment.online.utils.connections.ServerAPIs;
import com.assignment.online.utils.models.Person;

/**
 * Created by Matrix on 12/11/2018.
 */

public class SessionManagement {

    private Context context;

    private static SessionManagement session;

    private SharedPreferences sharedPref;
    public static final String SHARED_PREF_NAME="salesp";
    public static final int PRIVATE_MODE=0;

    SharedPreferences.Editor editor;

    public static SessionManagement getSession(Context context){
        if(session==null)
            session=new SessionManagement(context);
        return session;
    }

    private SessionManagement(Context context){
        this.context=context;
        sharedPref=context.getSharedPreferences(SHARED_PREF_NAME,PRIVATE_MODE);
        editor=sharedPref.edit();
    }



    public void endSession(){
        editor.clear();
        editor.commit();

        startLoginActivity();
    }



    public boolean ifNotAdmin(){
        return sharedPref.getBoolean(Person.ADMIN,false);
    }
    public void startAdminSession(){
        editor.putBoolean(Person.ADMIN,true);
        commitLogin();
        editor.commit();

        startAdminActivity();
    }

    private void commitLogin() {
        editor.putBoolean(Person.LOGIN,true);
    }

    private void startAdminActivity() {
        Intent i=new Intent(context, AdminActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(i);
    }

    public void startSalesPersonSession(String  personNumber, String regionID,String personName,String img,String date,String id){
        editor.putBoolean(Person.ADMIN,false);
        commitLogin();
        editor.putString(Person.PERSON_NUMBER,personNumber);
        editor.putString(Person.PERSON_REGION_ID,regionID);
        editor.putString(Person.PERSON_NAME,personName);
        editor.putString(Person.IMAGE, ServerAPIs.URL+img);
        editor.putString(Person.REGISTERATION_DATE,date);
        editor.putString(Person.PERSON_ID,id);
        editor.commit();

        startSalesPersonActivity();
    }

    public String getRegDate(){
        return sharedPref.getString(Person.REGISTERATION_DATE,"");
    }
    public String getName(){
        return sharedPref.getString(Person.PERSON_NAME,"");
    }
    public String getPersonId(){
        return sharedPref.getString(Person.PERSON_ID,"");
    }
    private void startLoginActivity(){
        Intent i=new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(i);
    }

    private void startSalesPersonActivity(){

        Intent i=new Intent(context, SalesPersonActivity.class);
        i.putExtra(Person.PERSON_NUMBER,sharedPref.getString(Person.PERSON_NUMBER,""));

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public void checkLogin(){
        if(isLoggedIn())
            if(isAdmin())
                startAdminSession();
            else
                startSalesPersonActivity();
    }
    public  boolean isAdmin() {
        boolean isAdmin = sharedPref.getBoolean(Person.ADMIN, false);
        return isAdmin;
    }

    public  boolean isSalesPerson() {
        boolean isAdmin = sharedPref.getBoolean(Person.ADMIN, false);
        return !isAdmin;
    }

    public String getImage(){
        return sharedPref.getString(Person.IMAGE,"");
    }

    public String getDate(){
        return sharedPref.getString(Person.REGISTERATION_DATE,"");
    }

    public String getRegion(){
        return sharedPref.getString(Person.PERSON_REGION_ID,"");
    }

    public boolean isLoggedIn(){
        return sharedPref.getBoolean(Person.LOGIN,false);
    }
}
