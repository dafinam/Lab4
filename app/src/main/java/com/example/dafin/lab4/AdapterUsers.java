package com.example.dafin.lab4;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AdapterUsers extends RecyclerView.Adapter<HolderUsers> {

    private List<User> listUsers = new ArrayList<>();
    private Context context;

    AdapterUsers(Context context){
        this.context= context;
    }


    public void addUser (User user){
        listUsers.add(user);
        Log.d("USER ADDED", "USER ADDED");
        notifyItemInserted(listUsers.size());
    }
    public HolderUsers onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view_users,parent,false);
        return new HolderUsers(v);
    }

    @Override
    public void onBindViewHolder(HolderUsers holder, int position) {
        holder.getUserName().setText(listUsers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }
}