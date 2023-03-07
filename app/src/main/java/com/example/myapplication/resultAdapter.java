package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class resultAdapter extends RecyclerView.Adapter<resultAdapter.MyviewHolder> {
    JSONArray res;
    Context ctx;
    public resultAdapter(JSONArray response, result result) {
        res=response;
        ctx=result;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single,parent, false);
        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        try {
            JSONObject ob=res.getJSONObject(position);


            holder.username.setText(ob.getString("username"));
            holder.doctor.setText(ob.getString("doctor_name"));
            holder.date.setText(ob.getString("date"));

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {
        return res.length();
    }

    class  MyviewHolder extends RecyclerView.ViewHolder
   {
       TextView username,doctor,date;

       public MyviewHolder(@NonNull View itemView) {
           super(itemView);

           username=itemView.findViewById(R.id.username);
           doctor=itemView.findViewById(R.id.doctor);
           date=itemView.findViewById(R.id.date);
       }
   }
}
