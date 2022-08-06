package com.example.lazismu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private EditText txtpassword, txtulangipassword, txtnamalengkap, txtalamat, txtemail, txttelepon, txtprofesi;
    Button daftar, batal;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    boolean passwordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtnamalengkap = (EditText) findViewById(R.id.txtnamalengkap);
        txtalamat = (EditText) findViewById(R.id.txtalamat);
        txttelepon = (EditText) findViewById(R.id.txttelepon);
        txtprofesi = (EditText) findViewById(R.id.txtprofesi);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        txtulangipassword = (EditText) findViewById(R.id.txtulangipassword);

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

        spinner = (Spinner) findViewById(R.id.pilihjeniskelamin);
        adapter = ArrayAdapter.createFromResource(this, R.array.jeniskelamin, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        daftar = (Button) findViewById(R.id.daftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namalengkap = txtnamalengkap.getText().toString();
                String alamat = txtalamat.getText().toString();
                String telepon = txttelepon.getText().toString();
                String profesi = txtprofesi.getText().toString();
                String email = txtemail.getText().toString();
                String password = txtpassword.getText().toString();
                String ulangipassword = txtulangipassword.getText().toString();

                if (TextUtils.isEmpty(namalengkap)){
                    txtnamalengkap.setError("Nama Lengkap belum diisi");
                    txtnamalengkap.requestFocus();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    txtemail.setError("Email belum diisi");
                    txtemail.requestFocus();
                }
                else if (TextUtils.isEmpty(alamat)){
                    txtalamat.setError("Alamat belum diisi");
                    txtalamat.requestFocus();
                }
                else if (TextUtils.isEmpty(email)){
                    txtemail.setError("Email belum diisi");
                    txtemail.requestFocus();
                }
                else if (TextUtils.isEmpty(telepon)){
                    txttelepon.setError("Telepon belum diisi");
                    txttelepon.requestFocus();
                }
                else if (telepon.length() < 10){
                    txttelepon.setError("Telepon minimal 10 digit");
                    txttelepon.requestFocus();
                }
                else if (telepon.length() >= 14){
                    txttelepon.setError("Telepon maksimal 13 digit");
                    txttelepon.requestFocus();
                }
                else if (TextUtils.isEmpty(password)){
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
                else if (TextUtils.isEmpty(profesi)){
                    txtprofesi.setError("Profesi belum diisi");
                    txtprofesi.requestFocus();
                }
                else {
                    registerUser(password, ulangipassword, namalengkap, alamat, email, telepon, profesi);
                }
            }
        });

        batal = (Button) findViewById(R.id.batal);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }

    private void registerUser(String password, String ulangipassword, String namalengkap, String alamat, String email, String telepon, String profesi) {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){
                FirebaseUser firebaseUser = auth.getCurrentUser();
                firebaseUser.sendEmailVerification();

                Intent intent = new Intent(Register.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }
    });
    }
}