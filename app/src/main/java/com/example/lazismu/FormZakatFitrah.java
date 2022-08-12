package com.example.lazismu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class FormZakatFitrah extends AppCompatActivity {

    EditText txtanggota, txthargaberas, txtketerangan;
    Double anggota, hargaberas, nominal;
    ImageView batalarah;
    private String namalengkap, alamat, profesi, telepon;
    Button confirmzakat, batalbutton;
    Spinner txtberupa;
    ArrayAdapter<CharSequence> adapter;
    TextView txtnominal, txttanggaltransaksi, txtnamalengkap, txtalamat, txttelepon, txtprofesi, txtprogram;
    private FirebaseAuth authProfil;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_zakat_fitrah);

        txtnamalengkap = (TextView) findViewById(R.id.txtnamalengkap);
        txtalamat = (TextView) findViewById(R.id.txtalamat);
        txttelepon = (TextView) findViewById(R.id.txttelepon);
        txtprofesi = (TextView) findViewById(R.id.txtprofesi);
        txtprogram = (TextView) findViewById(R.id.txtprogram);
        txtketerangan = (EditText) findViewById(R.id.txtketerangan);
        authProfil = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfil.getCurrentUser();

        showUserProfil(firebaseUser);

        DAOTransaksiNonTunai dao = new DAOTransaksiNonTunai();

        txtanggota = (EditText) findViewById(R.id.txtanggota);
        txthargaberas = (EditText) findViewById(R.id.txthargaberas);
        txtnominal = (TextView) findViewById(R.id.txtnominal);

        /*anggota = Double.parseDouble(txtanggota.getText().toString());
        hargaberas = Double.parseDouble(txthargaberas.getText().toString());
        nominal = anggota * hargaberas * 2.5;*/
        txtanggota.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtnominal.setText(addNumbers());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txthargaberas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtnominal.setText(addNumbers());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txttanggaltransaksi = (TextView)findViewById(R.id.txttanggaltransaksi);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateTanggal = new SimpleDateFormat("dd MMMM yyyy");
        String dateTanggal = simpleDateTanggal.format(calendar.getTime());
        txttanggaltransaksi.setText(dateTanggal);

        txtberupa = (Spinner) findViewById(R.id.txtberupa);
        adapter = ArrayAdapter.createFromResource(this, R.array.txtberupa, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtberupa.setAdapter(adapter);

        confirmzakat = (Button) findViewById(R.id.confirmzakatfitrah);
        confirmzakat.setOnClickListener(v-> {

            String tanggaltransaksi = txttanggaltransaksi.getText().toString();
            String nama = txtnamalengkap.getText().toString();
            String alamat = txtalamat.getText().toString();
            String nomor = txttelepon.getText().toString();
            String profesi = txtprofesi.getText().toString();
            String program = txtprogram.getText().toString();
            String berupa = txtberupa.getSelectedItem().toString();
            String nominal = txtnominal.getText().toString();
            String keterangan = txtketerangan.getText().toString();

            transaksinontunai emp = new transaksinontunai(tanggaltransaksi, nama,alamat,nomor,profesi,program,keterangan,berupa,nominal);
            dao.add(emp).addOnSuccessListener(suc->
            {Toast.makeText(FormZakatFitrah.this,"Transaksi Sukses, Mohon Tunggu Konfirmasi",Toast.LENGTH_LONG).show();
            }).addOnFailureListener(er ->
            {Toast.makeText(FormZakatFitrah.this,"Transaksi Gagal",Toast.LENGTH_LONG).show();
            });

        });

        batalbutton = (Button) findViewById(R.id.batal);
        batalbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FormZakat.class));
            }
        });

        batalarah = (ImageView) findViewById(R.id.kembalizakatfitrah);
        batalarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FormZakat.class));
            }
        });

    }
    private String addNumbers() {
        int number1;
        int number2;
        if(txtanggota.getText().toString() != "" && txtanggota.getText().length() > 0) {
            number1 = Integer.parseInt(txtanggota.getText().toString());
        } else {
            number1 = 0;
        }
        if(txthargaberas.getText().toString() != "" && txthargaberas.getText().length() > 0) {
            number2 = Integer.parseInt(txthargaberas.getText().toString());
        } else {
            number2 = 0;
        }

        Double hasil = number1*number2*2.5;
        Double h = new Double(hasil);
        int value = h.intValue();
        return Integer.toString(value);
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
                Toast.makeText(FormZakatFitrah.this, "Ada galat", Toast.LENGTH_SHORT).show();
            }
        });
    }
}