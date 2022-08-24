package com.example.lazismu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lazismu.retrofit.response.User;
import com.example.lazismu.sharedpreference.SharedPreferenceHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profil extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button profil, akun;
    ArrayAdapter<CharSequence> adapter;
    private TextView txtnamalengkap, txtalamat, txtemail, txttelepon, txtprofesi, txtjeniskelamin;
    private String namalengkap,alamat,profesi,email,telepon,jeniskelamin;
    private FirebaseAuth authProfil;
    CircleImageView fotoprofil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        txtnamalengkap = findViewById(R.id.txtnamalengkap);
        txtalamat = findViewById(R.id.txtalamat);
        txttelepon = findViewById(R.id.txttelepon);
        txtprofesi = findViewById(R.id.txtprofesi);
        txtemail = findViewById(R.id.txtemail);
        txtjeniskelamin = findViewById(R.id.pilihjeniskelamin);
        fotoprofil = findViewById(R.id.fotoprofil);

        SharedPreferenceHelper sp = new SharedPreferenceHelper(this);
        User user = sp.getUser();

        if (user == null) {
            sp.clear();
            startActivity(new Intent(this, Login.class));
            finish();
        }
        showUserProfil(user);

        fotoprofil = (CircleImageView) findViewById(R.id.fotoprofil);
        fotoprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profil.this, UploadFotoProfil.class));
                /*ImagePicker.Companion.getFile(Profil.this)
                        .galleryOnly()
                        .cameraOnly()
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        //.compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();*/
            }
        });

        profil = (Button) findViewById(R.id.profil);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profil.this, UbahProfil.class));
            }
        });

        akun = (Button) findViewById(R.id.akun);
        akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profil.this, UbahDetailAkun.class));
            }
        });

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigator);
        bottomNavigationView.setSelectedItemId(R.id.profilnavigasi);

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

    private void showUserProfil(User user) {
        txtnamalengkap.setText(user.getName());
        txtjeniskelamin.setText(user.getGender());
        txtalamat.setText(user.getAddress());
        txtemail.setText(user.getEmail());
        txttelepon.setText(user.getPhoneNumber());
        txtprofesi.setText(user.getProfession());
    }
}