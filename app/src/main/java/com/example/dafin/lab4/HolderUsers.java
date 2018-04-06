package com.example.dafin.lab4;
/**
 * Created by dafin on 05-Apr-18.
 */
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


public class HolderUsers extends RecyclerView.ViewHolder {

    private TextView userName;

    public TextView getUserName() {
        return userName;
    }

    public void setUserName(TextView userName) {
        this.userName = userName;
    }

    public HolderUsers(View itemView) {
        super(itemView);
        userName = itemView.findViewById(R.id.userNameCard);

    }
}