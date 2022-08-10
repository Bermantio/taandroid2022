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

public class FormShadaqah extends AppCompatActivity {

    private EditText txtnominal;
    private String namalengkap, alamat, profesi, telepon;
    ImageView batalarah;
    Button confirmshadaqah, batalbutton;
    Spinner pilihdonasisebagai, txtberupa;
    ArrayAdapter<CharSequence> adapter;
    TextView txttanggaltransaksi, txtnamalengkap, txtalamat, txttelepon, txtprofesi, txtprogram;
    private FirebaseAuth authProfil;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_shadaqah);

        txtnamalengkap = (TextView) findViewById(R.id.txtnamalengkap);
        txtalamat = (TextView) findViewById(R.id.txtalamat);
        txttelepon = (TextView) findViewById(R.id.txttelepon);
        txtprofesi = (TextView) findViewById(R.id.txtprofesi);
        txtprogram = (TextView) findViewById(R.id.txtprogram);
        txtnominal = (EditText) findViewById(R.id.txtnominal);
        authProfil = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfil.getCurrentUser();

        showUserProfil(firebaseUser);

        DAOTransaksiNonTunai dao = new DAOTransaksiNonTunai();

        txttanggaltransaksi = (TextView)findViewById(R.id.txttanggaltransaksi);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateTanggal = new SimpleDateFormat("dd MMMM yyyy");
        String dateTanggal = simpleDateTanggal.format(calendar.getTime());
        txttanggaltransaksi.setText(dateTanggal);

        pilihdonasisebagai = (Spinner) findViewById(R.id.pilihdonasisebagai);
        adapter = ArrayAdapter.createFromResource(this, R.array.donasisebagai, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pilihdonasisebagai.setAdapter(adapter);

        txtberupa = (Spinner) findViewById(R.id.txtberupa);
        adapter = ArrayAdapter.createFromResource(this, R.array.txtberupa, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtberupa.setAdapter(adapter);

        confirmshadaqah = (Button) findViewById(R.id.confirmshadaqah);
        confirmshadaqah.setOnClickListener(v-> {

            String tanggaltransaksi = txttanggaltransaksi.getText().toString();
            String nama = txtnamalengkap.getText().toString();
            String alamat = txtalamat.getText().toString();
            String nomor = txttelepon.getText().toString();
            String profesi = txtprofesi.getText().toString();
            String program = txtprogram.getText().toString();
            String berupa = txtberupa.getSelectedItem().toString();
            String nominal = txtnominal.getText().toString();
            String keterangan = pilihdonasisebagai.getSelectedItem().toString();

            transaksinontunai emp = new transaksinontunai(tanggaltransaksi, nama,alamat,nomor,profesi,program,keterangan,berupa,nominal);
            dao.add(emp).addOnSuccessListener(suc->
            {Toast.makeText(FormShadaqah.this,"Transaksi Sukses, Mohon Tunggu Konfirmasi",Toast.LENGTH_LONG).show();
            }).addOnFailureListener(er ->
            {Toast.makeText(FormShadaqah.this,"Transaksi Gagal",Toast.LENGTH_LONG).show();
            });

        });

        batalbutton = (Button) findViewById(R.id.batal);
        batalbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Donasi.class));
            }
        });

        batalarah = (ImageView) findViewById(R.id.kembalishadaqah);
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
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
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
                Toast.makeText(FormShadaqah.this, "Ada galat", Toast.LENGTH_SHORT).show();
            }
        });
    }
}