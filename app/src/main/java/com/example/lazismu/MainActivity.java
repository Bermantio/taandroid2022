package com.example.lazismu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView txtdonasi, txtprogram, txtlayanan, txttentang, txtkalkulatorzakat, txtbantuan, txtjam, txttanggal,
            selamatdatanguser;
    ImageView donasiicon, programicon, layananicon, kalkulatorzakaticon, bantuanicon, tentangicon, keluaricon;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        keluaricon = (ImageView) findViewById(R.id.kembalimenu);
        keluaricon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                signOutUser();
            }
        });

        selamatdatanguser = (TextView)findViewById(R.id.selamatdatanguser);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }

        txttanggal = (TextView)findViewById(R.id.txttanggal);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateTanggal = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String dateTanggal = simpleDateTanggal.format(calendar.getTime());
        txttanggal.setText(dateTanggal);

        txtjam = (TextView)findViewById(R.id.txtjam);
        Calendar jam = Calendar.getInstance();
        SimpleDateFormat simpleDateTime = new SimpleDateFormat("hh:mm a");
        String dateTime = simpleDateTime.format(jam.getTime());
        txtjam.setText(dateTime);

        txtdonasi = (TextView)findViewById(R.id.txtdonasi);
        txtdonasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Donasi.class));
            }
        });

        donasiicon = (ImageView)findViewById(R.id.donasiicon);
        donasiicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Donasi.class));
            }
        });

        txtkalkulatorzakat = (TextView)findViewById(R.id.txtkalkulatorzakat);
        txtkalkulatorzakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KalkulatorZakat.class));
            }
        });

        kalkulatorzakaticon = (ImageView)findViewById(R.id.kalkulatorzakaticon);
        kalkulatorzakaticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),KalkulatorZakat.class));
            }
        });

        txtbantuan = (TextView)findViewById(R.id.txtbantuan);
        txtbantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Bantuan.class));
            }
        });

        bantuanicon = (ImageView)findViewById(R.id.bantuanicon);
        bantuanicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Bantuan.class));
            }
        });

        txtlayanan = (TextView)findViewById(R.id.txtlayanan);
        txtlayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Layanan.class));
            }
        });

        layananicon = (ImageView)findViewById(R.id.layananicon);
        layananicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Layanan.class));
            }
        });

        txtprogram = (TextView)findViewById(R.id.txtprogram);
        txtprogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Program.class));
            }
        });

        programicon = (ImageView)findViewById(R.id.programicon);
        programicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Program.class));
            }
        });

        txttentang = (TextView)findViewById(R.id.txttentang);
        txttentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Tentang.class));
            }
        });

        tentangicon = (ImageView)findViewById(R.id.tentangicon);
        tentangicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Tentang.class));
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

    private void signOutUser() {
        Intent login = new Intent(MainActivity.this, Login.class);
        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(login);
        finish();
    }
}