package com.example.lazismu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Layanan extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView txtviatatapmuka,txtviatelepon,txtviawhatsapp;
    ImageView batalarah,whatsappicon, viatatapmukaicon, teleponicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layanan);

        batalarah = (ImageView)findViewById(R.id.kembalilayanan);
        batalarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        teleponicon = (ImageView)findViewById(R.id.teleponicon);
        teleponicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:0285420650"));
                startActivity(intent);
            }
        });

        txtviatelepon = (TextView) findViewById(R.id.txtviatelepon);
        txtviatelepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:0285420650"));
                startActivity(intent);
            }
        });

        viatatapmukaicon = (ImageView)findViewById(R.id.viatatapmukaicon);
        viatatapmukaicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Layanan.this, MapsActivity.class));
            }
        });

        txtviatatapmuka = (TextView) findViewById(R.id.txtviatatapmuka);
        txtviatatapmuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Layanan.this, MapsActivity.class));
            }
        });

        whatsappicon = (ImageView)findViewById(R.id.whatsappicon);
        whatsappicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean installed = appInstalledOrNot("com.whatsapp");

                if (installed){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=6285741347979"));
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Layanan.this,"Whatsapp belum terinstall",Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtviawhatsapp = (TextView) findViewById(R.id.txtviawhatsapp);
        txtviawhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean installed = appInstalledOrNot("com.whatsapp");

                if (installed){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=6285741347979"));
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Layanan.this,"Whatsapp belum terinstall",Toast.LENGTH_SHORT).show();
                }
            }
        });

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigator);
        bottomNavigationView.setSelectedItemId(R.id.beranda);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.laporannavigasi:
                        startActivity(new Intent(getApplicationContext(),Laporan.class));
                        break;
                    case R.id.beranda:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        break;
                    case R.id.riwayatnavigasi:
                        startActivity(new Intent(getApplicationContext(), Riwayat.class));
                        break;
                    case R.id.profilnavigasi:
                        startActivity(new Intent(getApplicationContext(), Profil.class));
                        break;
                }
                return false;
            }
        });
    }

    private boolean appInstalledOrNot(String url) {
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try{
            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            app_installed=true;
        }
        catch(PackageManager.NameNotFoundException e){
        app_installed=false;
        }
            return app_installed;
    }
}