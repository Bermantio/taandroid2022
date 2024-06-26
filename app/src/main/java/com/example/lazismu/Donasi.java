package com.example.lazismu;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Donasi extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView txtdonasizakat, txtdonasiinfaq, txtdonasishadaqah;
    ImageView batalarah, infaqicon, zakaticon, shadaqahicon;
    Dialog infoinfaq, infoshadaqah, infozakat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi);

        batalarah = (ImageView)findViewById(R.id.kembalidonasi);
        batalarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        infozakat = new Dialog(this);
        infoinfaq = new Dialog(this);
        infoshadaqah = new Dialog(this);

        txtdonasizakat = (TextView)findViewById(R.id.txtdonasizakat);
        txtdonasizakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormZakat.class));
            }
        });

        zakaticon = (ImageView)findViewById(R.id.zakaticon);
        zakaticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormZakat.class));
            }
        });

        txtdonasiinfaq = (TextView)findViewById(R.id.txtdonasiinfaq);
        txtdonasiinfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormInfaq.class));
            }
        });

        infaqicon = (ImageView)findViewById(R.id.infaqicon);
        infaqicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormInfaq.class));
            }
        });

        txtdonasishadaqah = (TextView)findViewById(R.id.txtdonasishadaqah);
        txtdonasishadaqah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormShadaqah.class));
            }
        });

        shadaqahicon = (ImageView)findViewById(R.id.shadaqahicon);
        shadaqahicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormShadaqah.class));
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
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
    public void infozakat(View view) {
        ImageView tutupinfozakat;
        infozakat.setContentView(R.layout.infozakat);

        tutupinfozakat = infozakat.findViewById(R.id.tutupinfozakat);
        tutupinfozakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infozakat.dismiss();
            }
        });
        infozakat.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        infozakat.show();
    }
    public void infoinfaq(View view) {
        ImageView tutupinfoinfaq;
        infoinfaq.setContentView(R.layout.infoinfaq);

        tutupinfoinfaq = infoinfaq.findViewById(R.id.tutupinfoinfaq);
        tutupinfoinfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoinfaq.dismiss();
            }
        });
        infoinfaq.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        infoinfaq.show();
    }
    public void infoshadaqah(View view) {
        ImageView tutupinfoshadaqah;
        infoshadaqah.setContentView(R.layout.infoshadaqah);

        tutupinfoshadaqah = infoshadaqah.findViewById(R.id.tutupinfoshadaqah);
        tutupinfoshadaqah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoshadaqah.dismiss();
            }
        });
        infoshadaqah.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        infoshadaqah.show();
    }
}