package com.example.dafin.lab4;
/**
 * Created by dafin on 05-Apr-18.
 */

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class LogInActivity extends AppCompatActivity {


    private EditText txtEmail;
    private EditText txtPass;
    private Button logInButton;
    private Button signUpButton;
    private FirebaseAuth mAuth;


    private DatabaseReference usersReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        txtEmail =findViewById(R.id.emailLogin);
        txtPass = findViewById(R.id.passwordLogin);
        logInButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signupButton);

        mAuth=FirebaseAuth.getInstance();
        usersReference = FirebaseDatabase.getInstance().getReference().child("Users");



        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString = txtEmail.getText().toString();
                String passwordString = txtPass.getText().toString();
                mAuth.signInWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    String deviceToken = FirebaseInstanceId.getInstance().getToken();
                                    usersReference.child(mAuth.getCurrentUser().getUid()).child("device_token").setValue(deviceToken).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Toast.makeText(LogInActivity.this,"Succesfull Log in ",Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(LogInActivity.this,MainActivity.class));
                                            finish();                                        }
                                    });



                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(LogInActivity.this,"Error trying to Log in",Toast.LENGTH_SHORT).show();


                                }


                            }
                        });



            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogInActivity.this,SignInActivity.class));
                finish();
            }
        });



    }
    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            Toast.makeText(LogInActivity.this,"User already logged",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LogInActivity.this,MainActivity.class));
            finish();

        }
    }

}