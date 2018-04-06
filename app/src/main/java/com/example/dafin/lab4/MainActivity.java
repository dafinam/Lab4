package com.example.dafin.lab4;
/**
 * Created by dafin on 05-Apr-18.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    public static AdapterMessage adapter;
    private CircleImageView userPic;
    private TextView name;
    private RecyclerView rvMessages;
    private EditText txtMessage;
    private Button sendButton;
    private Button logOutButton;
    private Button userListButton;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public static ArrayList<String> listOfTokens;

    private FirebaseAuth mAuth;


    private String globalName;
    private String userId;
    public Alarm alarm;

    public static int lastItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarm = new Alarm();
        userPic = findViewById(R.id.userPic);
        name = findViewById(R.id.name);
        rvMessages = findViewById(R.id.messages);
        txtMessage= findViewById(R.id.txtMessage);
        sendButton = findViewById(R.id.sendButton);
        logOutButton = findViewById(R.id.logOutButton);
        userListButton = findViewById(R.id.userList);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat"); //Chat room
        mAuth= FirebaseAuth.getInstance();



        FirebaseUser currentUser = mAuth.getCurrentUser();

        userId =currentUser.getUid();




        adapter = new AdapterMessage(this);
        final LinearLayoutManager layoutM = new LinearLayoutManager(this);
        rvMessages.setLayoutManager(layoutM);
        rvMessages.setAdapter(adapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.push().setValue(new SendMessage(txtMessage.getText().toString(),name.getText().toString(),"","1", ServerValue.TIMESTAMP));
                txtMessage.setText("");


            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,LogInActivity.class));
                finish();
            }
        });
        userListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,UserList.class));

            }
        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollBar();

            }
        });

/**
 * Retrieves the chat messages from the databse
 */
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ReceiveMessage message = dataSnapshot.getValue(ReceiveMessage.class);
                adapter.addMessage(message);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void setScrollBar(){
        rvMessages.scrollToPosition(adapter.getItemCount()-1);
    }


    protected void onResume() {
        super.onResume();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        alarm.cancelAlarm(MainActivity.this);
        if(currentUser!=null){

            sendButton.setEnabled(false);
            DatabaseReference reference = database.getReference("Users/" +currentUser.getUid() );
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    //Toast.makeText(MainActivity.this,"Name: "+user.getName(),Toast.LENGTH_SHORT).show();

                    Toast.makeText(MainActivity.this,"Name: "+user.getName(),Toast.LENGTH_SHORT).show();
                    globalName = user.getName();
                    userId =currentUser.getUid();
                    name.setText(globalName);
                    sendButton.setEnabled(true);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }else {
            startActivity(new Intent(MainActivity.this,LogInActivity.class));
            finish();
        }
    }
    @Override
    protected void onPause() {

        super.onPause();
        lastItems = adapter.getItemCount();
        alarm.setAlarm(MainActivity.this);
    }
}