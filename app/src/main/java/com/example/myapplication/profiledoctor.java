package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class profiledoctor extends AppCompatActivity {

    TextView speciall,doctorr,aboutt;
    ImageView profileimage;
    Button appointments;
    Bundle bundle;

    String do_id;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiledoctor);

        speciall=(TextView)  findViewById(R.id.sp);
        doctorr=(TextView)  findViewById(R.id.Dr);
        aboutt=(TextView)  findViewById(R.id.aboutt);
        profileimage=(ImageView) findViewById(R.id.profileimage);
        appointments=findViewById(R.id.appointments);



        appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Premanager pr=new Premanager(profiledoctor.this);

                 String user_id=pr.get_user_id();

                SaveBooking(user_id, do_id);
            }
        });






        bundle=getIntent().getExtras();

        if(!bundle.isEmpty() || bundle !=null){

            String special=bundle.getString("specialist");
            String name=bundle.getString("name");
            String about=bundle.getString("about");
            do_id=bundle.getString("id");

            String image=bundle.getString("img");



            speciall.setText(special);
            aboutt.setText(about);
            doctorr.setText(name);


            Glide.with(profiledoctor.this).load(image).into(profileimage);


        }

    }







    private void SaveBooking(String user_id, String do_id) {

        String url="http://10.0.2.2/myproject/booking.php?user_id="+user_id+"&doctor_id="+do_id;

        StringRequest sr=new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(profiledoctor.this, response,
                                Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(profiledoctor.this, "error"+error.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });


        Volley.newRequestQueue(profiledoctor.this).add(sr);

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

            startActivity(new Intent(profiledoctor.this, Login_activity.class));
        }

        if(item.getItemId()==R.id.result)
        {
            startActivity(new Intent(profiledoctor.this, result.class));
        }
        return super.onOptionsItemSelected(item);
    }







}
