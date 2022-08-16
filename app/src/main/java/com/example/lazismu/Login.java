package com.example.lazismu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    TextView daftar, bantuan;
    Button masuk;
    private EditText inputemail, inputpassword;
    String email, password;
    private FirebaseAuth authProfil;
    boolean passwordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authProfil = FirebaseAuth.getInstance();

        daftar = (TextView)findViewById(R.id.buatakunbaru);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        bantuan = (TextView)findViewById(R.id.lupapassword);
        bantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, BantuanLogin.class));
            }
        });

        inputemail = (EditText) findViewById(R.id.email);
        inputpassword = (EditText) findViewById(R.id.katasandi);

        inputpassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=inputpassword.getRight()-inputpassword.getCompoundDrawables()[Right].getBounds().width())
                    {
                        int selection=inputpassword.getSelectionEnd();
                        if(passwordVisible){
                            //set drawable image here
                            inputpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_off,0);
                            //for hide password
                            inputpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }
                        else{
                            //set drawable image here
                            inputpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_on,0);
                            //for hide password
                            inputpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        inputpassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        masuk = (Button) findViewById(R.id.masuk);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputemail.getText().toString();
                String password = inputpassword.getText().toString();

                if (TextUtils.isEmpty(email)){
                    inputemail.setError("Email belum diisi");
                    inputemail.requestFocus();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    inputemail.setError("Email belum diisi");
                    inputemail.requestFocus();
                }
                else if (TextUtils.isEmpty(password)){
                    inputpassword.setError("Password belum diisi");
                    inputpassword.requestFocus();
                }
                else {
                    LoginUser(password, email);
                }
            }
        });
    }

    private void LoginUser(String password, String email) {
        authProfil.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = authProfil.getCurrentUser();
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this,"Username atau Password Anda salah",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}