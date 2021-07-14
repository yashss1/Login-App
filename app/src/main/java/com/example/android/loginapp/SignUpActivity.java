package com.example.android.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpActivity extends AppCompatActivity {

    EditText emailId, password;
    Button btnSignUp;
    TextView tvLoginIn;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        tvLoginIn = findViewById(R.id.login_page);
        btnSignUp = findViewById(R.id.signUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please Enter Email id");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Please valid Password");
                    password.requestFocus();
                }
                else if(pwd.isEmpty() && email.isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
                }
                else if(!(pwd.isEmpty() && email.isEmpty())){
                   mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                           if(!task.isSuccessful()){
                               Toast.makeText(SignUpActivity.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                           }
                           else{
                               startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                               finish();
                           }
                       }
                   });
                }
                else {
                    Toast.makeText(SignUpActivity.this, "There was some Error, Plz try again Later", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvLoginIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}