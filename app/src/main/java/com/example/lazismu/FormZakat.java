package com.example.lazismu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class FormZakat extends AppCompatActivity {

    ImageView batalarah, zakatpenghasilanicon;
    TextView txtzakatpenghasilan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_zakat);

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

        batalarah = (ImageView)findViewById(R.id.kembalizakat);
        batalarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Donasi.class));
            }
        });
    }
}