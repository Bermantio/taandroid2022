package com.example.lazismu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BantuanLogin extends AppCompatActivity {
    TextView txtviatatapmuka,txtviatelepon,txtviawhatsapp;
    ImageView whatsappicon, viatatapmukaicon, teleponicon;
    Button kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan_login);

        kembali = (Button) findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BantuanLogin.this, Login.class));
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
                    finish();
                }
                else{
                    Toast.makeText(BantuanLogin.this,"Whatsapp belum terinstall",Toast.LENGTH_SHORT).show();
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
                    finish();
                }
                else{
                    Toast.makeText(BantuanLogin.this,"Whatsapp belum terinstall",Toast.LENGTH_SHORT).show();
                }
            }
        });

        teleponicon = (ImageView)findViewById(R.id.teleponicon);
        teleponicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("tel:0285420650"));
                    startActivity(intent);
                finish();
            }
        });

        txtviatelepon = (TextView) findViewById(R.id.txtviatelepon);
        txtviatelepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:0285420650"));
                startActivity(intent);
                finish();
            }
        });

        viatatapmukaicon = (ImageView)findViewById(R.id.viatatapmukaicon);
        viatatapmukaicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BantuanLogin.this, MapsActivity.class));
                finish();
            }
        });

        txtviatatapmuka = (TextView) findViewById(R.id.txtviatatapmuka);
        txtviatatapmuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BantuanLogin.this, MapsActivity.class));
                finish();
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