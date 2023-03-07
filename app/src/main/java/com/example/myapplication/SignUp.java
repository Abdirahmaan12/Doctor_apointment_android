package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        TextView already=findViewById(R.id.already);
        already.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent gologin=new Intent(SignUp.this, Login_activity.class);
                startActivity(gologin);
            }
        });

        Context context;
        SharedPreferences sharedPreferences;


        EditText txtusername=findViewById(R.id.txtusername);
        EditText txtpassword=findViewById(R.id.txtpassword);
        EditText txtconfirm=findViewById(R.id.txtpass);
        Button Btnseef=findViewById(R.id.buttonsave);

        Btnseef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=txtusername.getText().toString();
                String password=txtpassword.getText().toString();
                String confirm=txtconfirm.getText().toString();

                if(!password.equals(confirm)){
                    Log.i("19k","not matching");
                    Toast.makeText(SignUp.this,"two password does matching",Toast.LENGTH_LONG).show();
                    return;
                }


                SaveUsers(username, password, confirm);
            }
        });


    }



    void SaveUsers(String username,String password, String confirm)
    {

        String url="http://10.0.2.2/myproject/signup.php";
        StringRequest sr=new StringRequest(Request.Method.POST,

                url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SignUp.this, response,
                                Toast.LENGTH_LONG).show();

//                        loadAllusers();

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(SignUp.this,"error"+error.getMessage(),

                                Toast.LENGTH_LONG).show();

                    }
                }


        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params=new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                params.put("confirm_password", confirm);
                return params;
            }
        };


        RequestQueue queue= Volley.newRequestQueue(SignUp.this);
        queue.add(sr);
    }





}



