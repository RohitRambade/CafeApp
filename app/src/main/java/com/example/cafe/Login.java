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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

//        firebaseDatabase.getReference().child("Node").setValue("NodeValue");

        Button btnLogin=findViewById(R.id.btnLogin);

        EditText login_editEmail=findViewById(R.id.login_editEmail);
        EditText login_editPassword=findViewById(R.id.login_editPassword);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (login_editEmail.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Your Email",Toast.LENGTH_SHORT).show();
                }
                if (login_editPassword.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Your Password",Toast.LENGTH_SHORT).show();
                }

                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

                if (!(login_editEmail.getText().toString().isEmpty() && login_editPassword.getText().toString().isEmpty())){

                    firebaseAuth.signInWithEmailAndPassword(login_editEmail.getText().toString(),login_editPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if(task.isSuccessful()){



                                String uid=task.getResult().getUser().getUid();

                                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                                firebaseDatabase.getReference().child("Users").child(uid).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                        int usertype=snapshot.getValue(Integer.class);
                                        if(usertype==0){

                                            Intent in=new Intent(Login.this,MainActivity.class);
                                            startActivity(in);
                                        }

                                        if(usertype==1){
                                            Intent in=new Intent(Login.this,Admin.class);
                                            startActivity(in);

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                    }
                                });



                                Intent in=new Intent(Login.this,MainActivity.class);
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





        Button btn_button2=findViewById(R.id.button2);


        btn_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Login.this,SignUpActivity.class);
                startActivity(in);

            }
        });
    }
}