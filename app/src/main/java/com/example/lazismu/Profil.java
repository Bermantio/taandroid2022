package com.example.lazismu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Profil extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button edit;
    ArrayAdapter<CharSequence> adapter;
    private TextView txtjam, txttanggal, txtnamalengkap, txtalamat, txtemail, txttelepon, txtprofesi;
    private String namalengkap,alamat,profesi,email,telepon;
    private FirebaseAuth authProfil;
    private ImageView fotoprofil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        txtnamalengkap = findViewById(R.id.txtnamalengkap);
        txtalamat = findViewById(R.id.txtalamat);
        txttelepon = findViewById(R.id.txttelepon);
        txtprofesi = findViewById(R.id.txtprofesi);
        txtemail = findViewById(R.id.txtemail);

        authProfil = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfil.getCurrentUser();

        if (firebaseUser==null){
            Toast.makeText(Profil.this,"Ada galat", Toast.LENGTH_SHORT).show();
        }
        else{
            showUserProfil(firebaseUser);
        }

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

        edit = (Button) findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profil.this, UbahProfil.class));
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

    private void showUserProfil(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();
        DatabaseReference referenceProfil = FirebaseDatabase.getInstance().getReference("Registered Users");
        referenceProfil.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if (readUserDetails != null){
                    namalengkap = firebaseUser.getDisplayName();
                    email = firebaseUser.getEmail();
                    alamat = readUserDetails.alamat;
                    profesi = readUserDetails.profesi;
                    telepon= readUserDetails.telepon;

                    txtnamalengkap.setText(namalengkap);
                    txtalamat.setText(alamat);
                    txtemail.setText(email);
                    txttelepon.setText(telepon);
                    txtprofesi.setText(profesi);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profil.this,"Ada galat", Toast.LENGTH_SHORT).show();
            }
        });
    }
}