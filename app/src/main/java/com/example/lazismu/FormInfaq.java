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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormInfaq extends AppCompatActivity {

    private EditText txtnominal;
    private String namalengkap, alamat, profesi, telepon, program, berupa;
    ImageView batalarah;
    Button confirminfaq, batalbutton;
    Spinner pilihdonasisebagai;
    ArrayAdapter<CharSequence> adapter;
    TextView txttanggaltransaksi, txtnamalengkap, txtalamat, txttelepon, txtprofesi, txtprogram, txtberupa;
    private FirebaseAuth authProfil;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_infaq);

        txtnamalengkap = (TextView) findViewById(R.id.txtnamalengkap);
        txtalamat = (TextView) findViewById(R.id.txtalamat);
        txttelepon = (TextView) findViewById(R.id.txttelepon);
        txtprofesi = (TextView) findViewById(R.id.txtprofesi);
        txtprogram = (TextView) findViewById(R.id.txtprogram);
        txtberupa = (TextView) findViewById(R.id.txtberupa);

        authProfil = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfil.getCurrentUser();

        showUserProfil(firebaseUser);

        txttanggaltransaksi = (TextView) findViewById(R.id.txttanggaltransaksi);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateTanggal = new SimpleDateFormat("dd MMMM yyyy");
        String dateTanggal = simpleDateTanggal.format(calendar.getTime());
        txttanggaltransaksi.setText(dateTanggal);

        pilihdonasisebagai = (Spinner) findViewById(R.id.pilihdonasisebagai);
        adapter = ArrayAdapter.createFromResource(this, R.array.donasisebagai, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pilihdonasisebagai.setAdapter(adapter);

        batalbutton = (Button) findViewById(R.id.batal);
        batalbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Donasi.class));
            }
        });

        confirminfaq = (Button) findViewById(R.id.confirminfaq);
        confirminfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Donasi.class));
            }
        });

        batalarah = (ImageView) findViewById(R.id.kembaliinfaq);
        batalarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Donasi.class));
            }
        });
    }
    private void showUserProfil(FirebaseUser firebaseUser){
        String userIDofRegistered = firebaseUser.getUid();
        DatabaseReference referenceProfil = FirebaseDatabase.getInstance().getReference("Registered Users");
        referenceProfil.child(userIDofRegistered).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadUserDetails readUserDetails = snapshot.getValue(ReadUserDetails.class);
                if (readUserDetails != null) {
                    namalengkap = readUserDetails.namalengkap;
                    alamat = readUserDetails.alamat;
                    profesi = readUserDetails.profesi;
                    telepon = readUserDetails.telepon;

                    txtnamalengkap.setText(namalengkap);
                    txtalamat.setText(alamat);
                    txttelepon.setText(telepon);
                    txtprofesi.setText(profesi);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FormInfaq.this, "Ada galat", Toast.LENGTH_SHORT).show();
            }
        });
    }
}