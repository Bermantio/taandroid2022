package com.example.lazismu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class FormZakatPenghasilan extends AppCompatActivity {

    ImageView batalarah;
    Button batalbutton;
    Spinner pilihdonasisebagai;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_zakat_penghasilan);

        pilihdonasisebagai = (Spinner) findViewById(R.id.pilihdonasisebagai);
        adapter = ArrayAdapter.createFromResource(this, R.array.donasisebagai, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pilihdonasisebagai.setAdapter(adapter);

        batalbutton = (Button) findViewById(R.id.batal);
        batalbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FormZakat.class));
            }
        });

        batalarah = (ImageView) findViewById(R.id.kembalizakatpenghasilan);
        batalarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FormZakat.class));
            }
        });

    }
}