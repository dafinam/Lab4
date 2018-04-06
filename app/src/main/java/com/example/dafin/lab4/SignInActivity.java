package com.example.dafin.lab4;
/**
 * Created by dafin on 05-Apr-18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity {
    private EditText nameTxt;
    private EditText emailTxt;
    private EditText passwordTxt;
    private EditText repeatPasswordTxt;
    private DatabaseReference usersReference;

    private Button signUp;

    private FirebaseAuth mAuth;

    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        nameTxt = findViewById(R.id.signUpName);
        emailTxt = findViewById(R.id.signUpEmail);
        passwordTxt = findViewById(R.id.signUpPassword);
        repeatPasswordTxt = findViewById(R.id.signUpRepeatPassword);

        signUp = findViewById(R.id.signUpButton);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(SignUpActivity.this,"Error trying to Sign Up",Toast.LENGTH_SHORT).show();
                final String emailString = emailTxt.getText().toString();
                final String nameString = nameTxt.getText().toString();

                if(validatePassword()&&validateName(nameString))
                {
                    String passwordString=repeatPasswordTxt.getText().toString();
                    mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                            .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        String deviceToken = FirebaseInstanceId.getInstance().getToken();
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(SignInActivity.this,"Sign Up Completed",Toast.LENGTH_SHORT).show();
                                        User user = new User();
                                        user.setEmail(emailString);
                                        user.setName(nameString);
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        DatabaseReference reference = database.getReference("Users/" +currentUser.getUid() );
                                        reference.setValue(user);
                                        reference.child("device_token").setValue(deviceToken);
                                        MainActivity.listOfTokens = new ArrayList<String>();
                                        MainActivity.listOfTokens.add(deviceToken);
                                        usersReference = FirebaseDatabase.getInstance().getReference().child("Users");



                                        //finish();

                                        mAuth.signInWithEmailAndPassword(emailString, passwordTxt.getText().toString())
                                                .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {

                                                            String deviceToken = FirebaseInstanceId.getInstance().getToken();
                                                            usersReference.child(mAuth.getCurrentUser().getUid()).child("device_token").setValue(deviceToken).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    // Sign in success, update UI with the signed-in user's information
                                                                    Toast.makeText(SignInActivity.this,"Succesfull Log in ",Toast.LENGTH_SHORT).show();
                                                                    startActivity(new Intent(SignInActivity.this,MainActivity.class));
                                                                    finish();                                        }
                                                            });



                                                        } else {
                                                            // If sign in fails, display a message to the user.
                                                            Toast.makeText(SignInActivity.this,"Error trying to Log in",Toast.LENGTH_SHORT).show();


                                                        }


                                                    }
                                                });

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(SignInActivity.this,"Error trying to Sign Up",Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });
                }
            }





        });



    }

    private boolean validatePassword() {
        String password, repeatedPassword;

        password = passwordTxt.getText().toString();
        repeatedPassword = repeatPasswordTxt.getText().toString();
        return password.equals(repeatedPassword);

    }

    private boolean validateName(String name)
    {
        return !name.isEmpty();
    }


}