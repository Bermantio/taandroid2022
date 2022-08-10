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
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3960.5834737235377!2d109.6528430146259!3d-6.9402804949856725!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x2e70215afdda2cad%3A0x48e085a229a45b!2sJl.%20Raya%20Bligo%20No.7%2C%20Bligo%20Utara%2C%20Bligo%2C%20Kec.%20Buaran%2C%20Kabupaten%20Pekalongan%2C%20Jawa%20Tengah%2051171!5e0!3m2!1sid!2sid!4v1659583176956!5m2!1sid!2sid"));
                startActivity(intent);
            }
        });

        txtviatatapmuka = (TextView) findViewById(R.id.txtviatatapmuka);
        txtviatatapmuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3960.5834737235377!2d109.6528430146259!3d-6.9402804949856725!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x2e70215afdda2cad%3A0x48e085a229a45b!2sJl.%20Raya%20Bligo%20No.7%2C%20Bligo%20Utara%2C%20Bligo%2C%20Kec.%20Buaran%2C%20Kabupaten%20Pekalongan%2C%20Jawa%20Tengah%2051171!5e0!3m2!1sid!2sid!4v1659583176956!5m2!1sid!2sid"));
                startActivity(intent);
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