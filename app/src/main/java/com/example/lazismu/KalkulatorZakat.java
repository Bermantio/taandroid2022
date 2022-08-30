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

public class KalkulatorZakat extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView txtzakatpenghasilan, txtzakattabungan, txtzakatemasperak, txtzakatperdagangan, txtzakatpertanian;
    ImageView zakatpenghasilanicon, zakattabunganicon, zakatemasperakicon, zakatperdaganganicon, zakatpertanianicon,batal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_zakat);

        batal = (ImageView) findViewById(R.id.kembalikalkulator);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        txtzakatperdagangan = (TextView)findViewById(R.id.txtzakatperdagangan);
        txtzakatperdagangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KalkulatorZakatPerdagangan.class));
            }
        });

        zakatperdaganganicon = (ImageView)findViewById(R.id.zakatperdaganganicon);
        zakatperdaganganicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KalkulatorZakatPerdagangan.class));
            }
        });

        txtzakatpertanian = (TextView)findViewById(R.id.txtzakatpertanian);
        txtzakatpertanian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KalkulatorZakatPertanian.class));
            }
        });

        zakatpertanianicon = (ImageView)findViewById(R.id.zakatpertanianicon);
        zakatpertanianicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KalkulatorZakatPertanian.class));
            }
        });

        txtzakatemasperak = (TextView)findViewById(R.id.txtzakatemasperak);
        txtzakatemasperak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KalkulatorZakatEmasPerak.class));
            }
        });

        zakatemasperakicon = (ImageView)findViewById(R.id.zakatemasperakicon);
        zakatemasperakicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KalkulatorZakatEmasPerak.class));
            }
        });

        txtzakatpenghasilan = (TextView)findViewById(R.id.txtzakatpenghasilan);
        txtzakatpenghasilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KalkulatorZakatPenghasilan.class));
            }
        });

        zakatpenghasilanicon = (ImageView)findViewById(R.id.zakatpenghasilanicon);
        zakatpenghasilanicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KalkulatorZakatPenghasilan.class));
            }
        });

        txtzakattabungan = (TextView)findViewById(R.id.txtzakattabungan);
        txtzakattabungan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KalkulatorZakatTabungan.class));
            }
        });

        zakattabunganicon = (ImageView)findViewById(R.id.zakattabunganicon);
        zakattabunganicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KalkulatorZakatTabungan.class));
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