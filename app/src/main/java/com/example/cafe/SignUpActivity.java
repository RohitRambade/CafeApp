package com.example.cafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);


        Button backtologin=findViewById(R.id.backtologin);

        Button signup=findViewById(R.id.btnsignup);

       final EditText editname=findViewById(R.id.editname);
       final EditText editEmail=findViewById(R.id.editEmail);
       final EditText editPassword=findViewById(R.id.editPassword);
       final EditText editPhonenumber=findViewById(R.id.editPhonenumber);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editname.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Your Name",Toast.LENGTH_SHORT).show();
                }
                if (editEmail.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Your Email",Toast.LENGTH_SHORT).show();
                }
                if (editPassword.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Your Password",Toast.LENGTH_SHORT).show();
                }
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

                if (!(editname.getText().toString().isEmpty() && editEmail.getText().toString().isEmpty() && editPassword.getText().toString().isEmpty() )){

                firebaseAuth.createUserWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        String uid=task.getResult().getUser().getUid();

                        Users user=new Users(uid,editname.getText().toString(),editPhonenumber.getText().toString(),editEmail.getText().toString(),editPassword.getText().toString(),0);




                        firebaseDatabase.getReference().child("Users").child(uid).setValue(user);

                        Intent in=new Intent(SignUpActivity.this,MainActivity.class);
                        startActivity(in);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    }
                    }
                });

                }


            }
        });















        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(SignUpActivity.this,Login.class);
                startActivity(in);
            }
        });
    }
}