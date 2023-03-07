package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
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

public class result extends AppCompatActivity {
    RecyclerView result_rec;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result_rec=findViewById(R.id.result_rec);
        result_rec.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(result.this);
        result_rec.setLayoutManager(layoutManager);


        loadresult();
    }
    private void loadresult() {
        Premanager pr=new Premanager(result.this);
        String user_id=pr.get_user_id();
        String url="http://10.0.2.2/myproject/singlebooking.php?user_id="+user_id;

        JsonArrayRequest ja= new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONObject ob=response.getJSONObject( 0);

                            if(ob.getString("code").equals("yes"))
                            {
//                                Toast.makeText(result.this, response.toString(), Toast.LENGTH_LONG).show();
//
                                adapter=new resultAdapter(response,result.this);
                              result_rec.setAdapter(adapter);




                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(result.this, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue q= Volley.newRequestQueue(result.this);
        q.add(ja);

    }
}