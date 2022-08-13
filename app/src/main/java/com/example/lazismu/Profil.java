package com.example.lazismu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

        authProfil = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfil.getCurrentUser();

        if (firebaseUser==null){
            Toast.makeText(Profil.this,"Ada galat", Toast.LENGTH_SHORT).show();
        }
        else{
            showUserProfil(firebaseUser);
        }

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

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        fotoprofil.setImageURI(uri);
    }*/

    private void showUserProfil(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();
        DatabaseReference referenceProfil = FirebaseDatabase.getInstance().getReference("Registered Users");
        referenceProfil.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if (readUserDetails != null){
                    namalengkap = readUserDetails.namalengkap;
                    jeniskelamin = readUserDetails.jeniskelamin;
                    alamat = readUserDetails.alamat;
                    email = firebaseUser.getEmail();
                    telepon= readUserDetails.telepon;
                    profesi = readUserDetails.profesi;

                    txtnamalengkap.setText(namalengkap);
                    txtjeniskelamin.setText(jeniskelamin);
                    txtalamat.setText(alamat);
                    txtemail.setText(email);
                    txttelepon.setText(telepon);
                    txtprofesi.setText(profesi);

                   Uri uri = firebaseUser.getPhotoUrl();

                   Picasso.with(Profil.this).load(uri).into(fotoprofil);
                }
                else{
                    Toast.makeText(Profil.this,"Ada galat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profil.this,"Ada galat", Toast.LENGTH_SHORT).show();
            }
        });
    }
}