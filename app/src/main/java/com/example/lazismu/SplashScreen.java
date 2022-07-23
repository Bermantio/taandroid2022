package com.example.lazismu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    private int waktu_loading=5000;

    //5000=5 detik
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Login=new Intent(SplashScreen.this, Login.class);
                startActivity(Login);
                finish();
            }
        },waktu_loading);
    }
}