package com.example.lazismu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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

public class Register extends AppCompatActivity {

    EditText username, password1, password2, namapengguna, namalengkap, password, profesi;
    Button daftar, batal;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    boolean passwordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        password1 = (EditText) findViewById(R.id.txtpassword);
        password1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=password1.getRight()-password1.getCompoundDrawables()[Right].getBounds().width())
                    {
                        int selection=password1.getSelectionEnd();
                        if(passwordVisible){
                            //set drawable image here
                            password1.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_off,0);
                            //for hide password
                            password1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }
                        else{
                            //set drawable image here
                            password1.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_on,0);
                            //for hide password
                            password1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        password1.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        password2 = (EditText) findViewById(R.id.txtulangipassword);
        password2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=password2.getRight()-password2.getCompoundDrawables()[Right].getBounds().width())
                    {
                        int selection=password2.getSelectionEnd();
                        if(passwordVisible){
                            //set drawable image here
                            password2.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_off,0);
                            //for hide password
                            password2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }
                        else{
                            //set drawable image here
                            password2.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_on,0);
                            //for hide password
                            password2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        password2.setSelection(selection);
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

        batal = (Button) findViewById(R.id.batal);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }
}