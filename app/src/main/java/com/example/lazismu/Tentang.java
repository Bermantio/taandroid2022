package com.example.lazismu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Tentang extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView txttentanglazismu, txttentangpembayaran, txttentangasnaf;
    ImageView batalarah, lazismuicon, asnaficon, pembayaranicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);

        batalarah = (ImageView)findViewById(R.id.kembalitentang);
        batalarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        txttentanglazismu = (TextView)findViewById(R.id.txttentanglazismu);
        txttentanglazismu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),TentangLazismu.class));
            }
        });

        lazismuicon = (ImageView)findViewById(R.id.lazismuicon);
        lazismuicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),TentangLazismu.class));
            }
        });

        txttentangasnaf = (TextView)findViewById(R.id.txttentangasnaf);
        txttentangasnaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),TentangAsnaf.class));
            }
        });

        asnaficon = (ImageView)findViewById(R.id.asnaficon);
        asnaficon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),TentangAsnaf.class));
            }
        });

        txttentangpembayaran = (TextView)findViewById(R.id.txttentangpembayaran);
        txttentangpembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),TentangPembayaran.class));
            }
        });

        pembayaranicon = (ImageView)findViewById(R.id.pembayaranicon);
        pembayaranicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),TentangPembayaran.class));
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
}