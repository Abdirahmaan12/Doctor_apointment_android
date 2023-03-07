package com.example.myapplication;

import static android.widget.LinearLayout.*;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.MyViewHolder> {
    JSONArray res;
    Context ctx;
    public DoctorAdapter(JSONArray response, Doctors doctors) {
        res=response;
        ctx=doctors;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.doctorssinglerow,parent, false);
        return new MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        try {
            JSONObject ob=res.getJSONObject(position);


            holder.doctorname.setText(ob.getString("name"));
            holder.specialist.setText(ob.getString("specialist"));
            holder.Aboot.setText(ob.getString("about"));



            String imagurl="http://10.0.2.2/myproject/"+ob.getString("img");


            Glide.with(ctx).load(imagurl).into(holder.doctorimg);


           holder.linearLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {


                   Intent profile=new Intent(ctx, profiledoctor.class);

                   try {
                       profile.putExtra("specialist", ob.getString("specialist"));
                       profile.putExtra("name", ob.getString("name"));
                       profile.putExtra("id", ob.getString("id"));
                       profile.putExtra("about", ob.getString("about"));
                       profile.putExtra("img", imagurl);



                       ctx.startActivity(profile);
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }


               }
           });



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    @Override
    public int getItemCount() {
        return res.length();
    }


    class  MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView doctorimg;
        TextView doctorname, specialist,Aboot;
        LinearLayout linearLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorimg=itemView.findViewById(R.id.doctorimg);
            doctorname=itemView.findViewById(R.id.doctorname);
            specialist=itemView.findViewById(R.id.specialist);
            Aboot=itemView.findViewById(R.id.Aboot);
            linearLayout= (LinearLayout) itemView.findViewById(R.id.linier_layout);
        }
    }
}
