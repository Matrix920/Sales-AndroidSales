package com.assignment.online.utils.sessions;

import android.app.Application;
import android.content.Context;

public class Controller extends Application {

    private static  SessionManagement session;

    private static Controller controller;



    private Controller(){
    }

    public static Context getInstance(){
        if(controller==null) {
            controller = new Controller();
        }
        return controller;
    }

    public  static   SessionManagement getSession(){
        if(session==null)
            session=SessionManagement.getSession(getInstance());
        return session;
    }
}
