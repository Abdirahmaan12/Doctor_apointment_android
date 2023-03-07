package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class Premanager {

    SharedPreferences pr;
    SharedPreferences.Editor editor;


    public Premanager(Context ctx){

        pr= ctx.getSharedPreferences("samafale", Context.MODE_PRIVATE);

        editor=pr.edit();


    }

    public  void  set_user_id(String U_ID)

    {
        editor.putString("u_id", U_ID);
        editor.commit();
    }

    public  String get_user_id(){return pr.getString("u_id", "");}


    public void set_doctor_id(String id)

    {
        editor.putString("doctor_id", id);
        editor.commit();
    }

    public  String get_doctor_id(){return pr.getString("doctor_id", "");

    }

}
