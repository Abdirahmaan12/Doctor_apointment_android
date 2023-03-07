package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class Doctors extends AppCompatActivity {

    RecyclerView doc_rec;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        doc_rec=findViewById(R.id.doc_rec);
        doc_rec.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(Doctors.this);
        doc_rec.setLayoutManager(layoutManager);

        LoadAlldoctors();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logout)
        {

            startActivity(new Intent(Doctors.this, Login_activity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadAlldoctors() {

        String url="http://10.0.2.2/myproject/doctors.php";

        JsonArrayRequest ja= new JsonArrayRequest(Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONObject ob=response.getJSONObject( 0);

                            if(ob.getString("code").equals("yes"))
                            {
//                                Premanager pr=new Premanager(Doctors.this);
//                                String set_doctor_id=ob.getString("id");


                                String id=ob.getString("id");

                                adapter=new DoctorAdapter(response,Doctors.this);
                               doc_rec.setAdapter(adapter);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Doctors.this, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue q= Volley.newRequestQueue(Doctors.this);
        q.add(ja);

    }
}