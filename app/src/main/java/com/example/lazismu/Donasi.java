package com.example.lazismu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Donasi extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView txtdonasizakat, txtdonasiinfaq, txtdonasishadaqah, txtjam, txttanggal;
    ImageView infaqicon, zakaticon, shadaqahicon;
    Dialog infoinfaq, infoshadaqah, infozakat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi);

        infozakat = new Dialog(this);
        infoinfaq = new Dialog(this);
        infoshadaqah = new Dialog(this);

        txttanggal = (TextView)findViewById(R.id.txttanggal);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateTanggal = new SimpleDateFormat("dd MMMM yyyy");
        String dateTanggal = simpleDateTanggal.format(calendar.getTime());
        txttanggal.setText(dateTanggal);

        txtjam = (TextView)findViewById(R.id.txtjam);
        Calendar jam = Calendar.getInstance();
        SimpleDateFormat simpleDateTime = new SimpleDateFormat("hh:mm a");
        String dateTime = simpleDateTime.format(jam.getTime());
        txtjam.setText(dateTime);

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
        bottomNavigationView.setSelectedItemId(R.id.donasinavigasi);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.donasinavigasi:
                        startActivity(new Intent(getApplicationContext(),Donasi.class));
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