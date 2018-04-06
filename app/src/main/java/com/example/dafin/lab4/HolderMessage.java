package com.example.dafin.lab4;
/**
 * Created by dafin on 05-Apr-18.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;


public class HolderMessage extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView message;
    private TextView time;
    private CircleImageView msgPic;

    public HolderMessage(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.nameMessage);
        message = itemView.findViewById(R.id.messageMessage);
        time = itemView.findViewById(R.id.timeMessage);
        msgPic = itemView.findViewById(R.id.userPicMessage);
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getMessage() {
        return message;
    }

    public void setMessage(TextView message) {
        this.message = message;
    }

    public TextView getTime() {
        return time;
    }

    public void setTime(TextView time) {
        this.time = time;
    }

    public CircleImageView getMsgPic() {
        return msgPic;
    }

    public void setMsgPic(CircleImageView msgPic) {
        this.msgPic = msgPic;
    }
}
