package com.example.dangnhapfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button mLoginBtn,mLoginOTP;
    TextView mCreateBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail=findViewById(R.id.editTextTextPersonName2);
        mPassword=findViewById(R.id.editTextTextPersonName3);
        fAuth=FirebaseAuth.getInstance();
        mLoginBtn=findViewById(R.id.login);
        mCreateBtn=findViewById(R.id.dangki);
        mLoginOTP=findViewById(R.id.loginOTP);
        mLoginOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, NhapSoDT.class);
                startActivity(intent);
            }
        });



        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    mPassword.setError("PassWord is Required");
                    return;
                }
                if (password.length()<6)
                {
                    mPassword.setError("PassWord Must be >=6 Characters");
                    return;
                }
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "logged is Sucessfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), com.example.dangnhapfirebase.Chinh.class));

                        }else{
                            Toast.makeText(MainActivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.example.dangnhapfirebase.DangKi.class));
            }
        });
    }
}