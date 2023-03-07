package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView signup=findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override




            public void onClick(View view) {
                Intent gosignup=new Intent(Login_activity.this, SignUp.class);
                startActivity(gosignup);
            }


        });

        EditText ed_username= findViewById(R.id.txtuser);
        EditText ed_pass= findViewById(R.id.txtpass);
        Button btnLogin= findViewById(R.id.buttonsave);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user=ed_username.getText().toString();
                String pass=ed_pass.getText().toString();

                checkUser(user, pass);
            }
        });
    }




    void  checkUser(String user, String pass)
    {

        String url="http://10.0.2.2/myproject/login.php?username="+user+"&password="+pass;

        JsonArrayRequest ja=new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject ob=response.getJSONObject(0);
                            if(ob.getString("code").equals("yes")){

                                Premanager pr=new Premanager(Login_activity.this);
                                pr.set_user_id(ob.getString("id"));
                            //    pr.set_doctor_id(ob.getString("id"));

                                startActivity(new Intent(Login_activity.this,
                                        Doctors.class));

                            }else {
                                Toast.makeText(Login_activity.this,
                                        "username or password are incorrect",
                                        Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Login_activity.this,
                                "error"+error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue q= Volley.newRequestQueue(Login_activity.this);
        q.add(ja);
    }

}