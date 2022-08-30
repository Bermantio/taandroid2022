package com.example.lazismu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class FormZakat extends AppCompatActivity {

    ImageView batalarah, zakatpenghasilanicon, zakatfitrahicon, zakattabunganicon, zakatperdaganganicon;
    TextView txtzakatpenghasilan, txtzakatfitrah, txtzakatperdagangan, txtzakattabungan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_zakat);

        txtzakatperdagangan = (TextView)findViewById(R.id.txtzakatperdagangan);
        txtzakatperdagangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormZakatPerdagangan.class));
            }
        });

        zakatperdaganganicon = (ImageView)findViewById(R.id.zakatperdaganganicon);
        zakatperdaganganicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormZakatPerdagangan.class));
            }
        });

        txtzakattabungan = (TextView)findViewById(R.id.txtzakattabungan);
        txtzakattabungan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormZakatTabungan.class));
            }
        });

        zakattabunganicon = (ImageView)findViewById(R.id.zakattabunganicon);
        zakattabunganicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormZakatTabungan.class));
            }
        });

        txtzakatpenghasilan = (TextView)findViewById(R.id.txtzakatpenghasilan);
        txtzakatpenghasilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormZakatPenghasilan.class));
            }
        });

        zakatpenghasilanicon = (ImageView)findViewById(R.id.zakatpenghasilanicon);
        zakatpenghasilanicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormZakatPenghasilan.class));
            }
        });

        txtzakatfitrah = (TextView)findViewById(R.id.txtzakatfitrah);
        txtzakatfitrah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormZakatFitrah.class));
            }
        });

        zakatfitrahicon = (ImageView)findViewById(R.id.zakatfitrahicon);
        zakatfitrahicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FormZakatFitrah.class));
            }
        });

        batalarah = (ImageView)findViewById(R.id.kembalizakat);
        batalarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Donasi.class));
            }
        });
    }
}