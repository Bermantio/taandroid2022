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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UbahDetailAkun extends AppCompatActivity {

    private FirebaseAuth authProfil;
    private FirebaseUser firebaseUser;
    private String email;
    private String password;
    private String ulangipassword;
    boolean passwordVisible;
    private Button updateemail, updatepassword;
    private EditText txtemail, txtpassword, txtulangipassword;
    ImageView kembalieditakun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_detail_akun);

        txtemail = findViewById(R.id.txtemail);
        txtpassword = findViewById(R.id.txtpassword);
        txtulangipassword = findViewById(R.id.txtulangipassword);
        updateemail = findViewById(R.id.updateemail);
        updatepassword = findViewById(R.id.updatepassword);
        kembalieditakun = (ImageView) findViewById(R.id.kembalieditakun);
        kembalieditakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UbahDetailAkun.this, Profil.class));
            }
        });

        authProfil = FirebaseAuth.getInstance();
        firebaseUser = authProfil.getCurrentUser();

        txtpassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=txtpassword.getRight()-txtpassword.getCompoundDrawables()[Right].getBounds().width())
                    {
                        int selection=txtpassword.getSelectionEnd();
                        if(passwordVisible){
                            //set drawable image here
                            txtpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_off,0);
                            //for hide password
                            txtpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }
                        else{
                            //set drawable image here
                            txtpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_on,0);
                            //for hide password
                            txtpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        txtpassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        txtulangipassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=txtulangipassword.getRight()-txtulangipassword.getCompoundDrawables()[Right].getBounds().width())
                    {
                        int selection=txtulangipassword.getSelectionEnd();
                        if(passwordVisible){
                            //set drawable image here
                            txtulangipassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_off,0);
                            //for hide password
                            txtulangipassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }
                        else{
                            //set drawable image here
                            txtulangipassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_on,0);
                            //for hide password
                            txtulangipassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        txtulangipassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        updateemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = txtemail.getText().toString();
                if (TextUtils.isEmpty(email)){
                    txtemail.setError("Email belum diisi");
                    txtemail.requestFocus();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    txtemail.setError("Email belum diisi");
                    txtemail.requestFocus();
                }
                else{
                    updateEmail(firebaseUser);
                }
            }
        });

        updatepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = txtpassword.getText().toString();
                ulangipassword = txtulangipassword.getText().toString();
                if (TextUtils.isEmpty(password)){
                    txtpassword.setError("Password belum diisi");
                    txtpassword.requestFocus();
                }
                else if (password.length()<8){
                    txtpassword.setError("Kata sandi antara 8-12 digit");
                    txtpassword.requestFocus();
                }
                else if (password.length()>=13){
                    txtpassword.setError("Kata sandi antara 8-12 digit");
                    txtpassword.requestFocus();
                }
                else if (TextUtils.isEmpty(ulangipassword)){
                    txtulangipassword.setError("Ulangi kata sandi");
                    txtulangipassword.requestFocus();
                }
                else if (!password.equals(ulangipassword)){
                    txtulangipassword.setError("Ulangi kata sandi");
                    txtulangipassword.requestFocus();
                    txtpassword.clearComposingText();
                    txtulangipassword.clearComposingText();
                }
                else{
                    updatePassword(firebaseUser);
                }
            }
        });
    }

    private void updateEmail(FirebaseUser firebaseUser) {
        firebaseUser.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(UbahDetailAkun.this,"Email telah diubah",Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(UbahDetailAkun.this,Profil.class);
                    //startActivity(intent);
                    //finish();
                }else{
                    try{
                        throw task.getException();
                    }
                    catch (Exception e){
                        Toast.makeText(UbahDetailAkun.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                };
            }
        });
    }
    private void updatePassword(FirebaseUser firebaseUser) {
        firebaseUser.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(UbahDetailAkun.this,"Password telah diubah",Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(UbahDetailAkun.this,Profil.class);
                    //startActivity(intent);
                    //finish();
                }else{
                    try{
                        throw task.getException();
                    }
                    catch (Exception e){
                        Toast.makeText(UbahDetailAkun.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                };
            }
        });
    }
}