package com.example.lazismu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Program extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView txtprogramekonomi, txtprogramkemanusiaan, txtprogramkesehatan,
            txtprogramlingkungan, txtprogrampendidikan, txtprogramsosialdakwah, txtjam, txttanggal;
    ImageView ekonomiicon, kemanusiaanicon, kesehatanicon, lingkunganicon, pendidikanicon, sosialdakwahicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

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

        txtprogramekonomi = (TextView)findViewById(R.id.txtprogramekonomi);
        txtprogramekonomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProgramEkonomi.class));
            }
        });

        ekonomiicon = (ImageView)findViewById(R.id.ekonomiicon);
        ekonomiicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProgramEkonomi.class));
            }
        });

        txtprogramkemanusiaan = (TextView)findViewById(R.id.txtprogramkemanusiaan);
        txtprogramkemanusiaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProgramKemanusiaan.class));
            }
        });

        kemanusiaanicon = (ImageView)findViewById(R.id.kemanusiaanicon);
        kemanusiaanicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProgramKemanusiaan.class));
            }
        });

        txtprogramkesehatan = (TextView)findViewById(R.id.txtprogramkesehatan);
        txtprogramkesehatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProgramKesehatan.class));
            }
        });

        kesehatanicon = (ImageView)findViewById(R.id.kesehatanicon);
        kesehatanicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProgramKesehatan.class));
            }
        });

        txtprogramlingkungan = (TextView)findViewById(R.id.txtprogramlingkungan);
        txtprogramlingkungan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProgramLingkungan.class));
            }
        });

        lingkunganicon = (ImageView)findViewById(R.id.lingkunganicon);
        lingkunganicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProgramLingkungan.class));
            }
        });

        txtprogrampendidikan = (TextView)findViewById(R.id.txtprogrampendidikan);
        txtprogrampendidikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProgramPendidikan.class));
            }
        });

        pendidikanicon = (ImageView)findViewById(R.id.pendidikanicon);
        pendidikanicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProgramPendidikan.class));
            }
        });

        txtprogramsosialdakwah = (TextView)findViewById(R.id.txtprogramsosialdakwah);
        txtprogramsosialdakwah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProgramSosialDakwah.class));
            }
        });

        sosialdakwahicon = (ImageView)findViewById(R.id.sosialdakwahicon);
        sosialdakwahicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProgramSosialDakwah.class));
            }
        });

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigator);
        bottomNavigationView.setSelectedItemId(R.id.beranda);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.donasinavigasi:
                        startActivity(new Intent(getApplicationContext(),Donasi.class));
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