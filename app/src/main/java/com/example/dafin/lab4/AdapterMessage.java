package com.example.dafin.lab4;
/**
 * Created by dafin on 05-Apr-18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AdapterMessage extends RecyclerView.Adapter<HolderMessage> {

    private List<ReceiveMessage> listMessage= new ArrayList<>();
    private Context context;

    AdapterMessage(Context context){

        this.context = context;
    }

    public void addMessage (ReceiveMessage message)
    {
        listMessage.add(message);
        notifyItemInserted(listMessage.size());

    }

    @Override
    public HolderMessage onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view_messages,parent,false);
        return new HolderMessage(v);
    }

    @Override
    public void onBindViewHolder(HolderMessage holder, int position) {
        holder.getName().setText(listMessage.get(position).getName());
        holder.getMessage().setText(listMessage.get(position).getMessage());
        // holder.getTime().setText(listMessage.get(position).getTime());

        Long timeCode = listMessage.get(position).getTime();
        Date date = new Date(timeCode);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a"); //a is for pm or am
        holder.getTime().setText(sdf.format(date));

    }

    @Override
    public int getItemCount() {
        return listMessage.size();
    }
}