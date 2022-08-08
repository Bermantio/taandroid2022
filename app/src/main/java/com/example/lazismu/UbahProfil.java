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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UbahProfil extends AppCompatActivity {

    private EditText txtpassword, txtulangipassword, txtnamalengkap, txtalamat, txtemail, txttelepon, txtprofesi;
    private String namalengkap,alamat,profesi,email,telepon,jeniskelamin,password,ulangipassword;
    Button simpan, batal;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    boolean passwordVisible;
    private FirebaseAuth authProfil;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profil);

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

        authProfil = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfil.getCurrentUser();

        showUserProfil(firebaseUser);

        simpan = (Button) findViewById(R.id.simpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfil(firebaseUser);
            }
            });

        batal = (Button) findViewById(R.id.batal);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UbahProfil.this, Profil.class));
            }
        });
    }

    private void updateProfil(FirebaseUser firebaseUser) {
        if (TextUtils.isEmpty(namalengkap)){
            txtnamalengkap.setError("Nama Lengkap belum diisi");
            txtnamalengkap.requestFocus();
        }
        else if (TextUtils.isEmpty(alamat)){
            txtalamat.setError("Alamat belum diisi");
            txtalamat.requestFocus();
        }
        else if (TextUtils.isEmpty(jeniskelamin)){
            spinner.requestFocus();
        }
        else if (TextUtils.isEmpty(email)){
            txtemail.setError("Email belum diisi");
            txtemail.requestFocus();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
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
            namalengkap = txtnamalengkap.getText().toString();
            alamat = txtalamat.getText().toString();
            telepon = txttelepon.getText().toString();
            profesi = txtprofesi.getText().toString();
            jeniskelamin = spinner.getSelectedItem().toString();
            email = txtemail.getText().toString();
            password = txtpassword.getText().toString();
            ulangipassword = txtulangipassword.getText().toString();

            ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails ( namalengkap, alamat, telepon, profesi, jeniskelamin);

            DatabaseReference referenceProfil = FirebaseDatabase.getInstance().getReference("Registered Users");

            String userID = firebaseUser.getUid();

            referenceProfil.child(userID).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){

                        UserProfileChangeRequest profilUpdates = new UserProfileChangeRequest.Builder().
                                setDisplayName(namalengkap).build();
                        firebaseUser.updateProfile(profilUpdates);

                        Toast.makeText(UbahProfil.this,"Profil berhasil disimpan",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(UbahProfil.this, Profil.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        try{
                            throw task.getException();
                        }
                        catch (Exception e){
                            Toast.makeText(UbahProfil.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    }

    private void showUserProfil(FirebaseUser firebaseUser) {
        String userIDofRegistered = firebaseUser.getUid();
        DatabaseReference referenceProfil = FirebaseDatabase.getInstance().getReference("Registered Users");
        referenceProfil.child(userIDofRegistered).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadUserDetails readUserDetails = snapshot.getValue(ReadUserDetails.class);
                if (readUserDetails != null){
                    namalengkap = readUserDetails.namalengkap;
                    email = firebaseUser.getEmail();
                    alamat = readUserDetails.alamat;
                    jeniskelamin = readUserDetails.jeniskelamin;
                    profesi = readUserDetails.profesi;
                    telepon= readUserDetails.telepon;

                    txtnamalengkap.setText(namalengkap);
                    txtalamat.setText(alamat);
                    txtemail.setText(email);
                    spinner.setSelection(Integer.parseInt(jeniskelamin));
                    txttelepon.setText(telepon);
                    txtprofesi.setText(profesi);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UbahProfil.this,"Ada galat", Toast.LENGTH_SHORT).show();
            }
        });
    }
}