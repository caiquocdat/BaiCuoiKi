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

import com.example.dangnhapfirebase.Chinh;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class DangKi extends AppCompatActivity {

    EditText mEmail,mFullName,mPassword;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        mEmail=findViewById(R.id.editTextTextPersonName5);
        mFullName=findViewById(R.id.editTextTextPersonName2);
        mPassword=findViewById(R.id.editTextTextPersonName3);
        mRegisterBtn=findViewById(R.id.button);
        mLoginBtn=findViewById(R.id.dangNhap);
        fAuth=FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser()!=null) {
            startActivity(new Intent(getApplicationContext(), Chinh.class));
            finish();

        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
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
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(DangKi.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Chinh.class));
                        }else {
                            Toast.makeText(DangKi.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}