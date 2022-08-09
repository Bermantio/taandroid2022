package com.example.lazismu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class KalkulatorZakatPertanian extends AppCompatActivity {

    EditText txtjumlahhasilpanen;
    Button hitung;
    TextView txtnishab, txthasil, txtstatuszakat;
    Double jumlahhasilpanen, hasil, nishab;
    Spinner txtjenispengairan;
    String jenispengairan;
    ArrayAdapter<CharSequence> adapter;
    ImageView batalarah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_zakat_pertanian);
        batalarah = (ImageView) findViewById(R.id.kembalizakat);
        batalarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), KalkulatorZakat.class));
            }
        });

        txtjumlahhasilpanen = (EditText) findViewById(R.id.txtjumlahhasilpanen);
        txthasil = (TextView) findViewById(R.id.txthasil);
        txtnishab = (TextView) findViewById(R.id.txtnishab);
        txtstatuszakat = (TextView) findViewById(R.id.txtstatuszakat);
        hitung = (Button) findViewById(R.id.hitung);
        txtjenispengairan = (Spinner) findViewById(R.id.txtjenispengairan);
        adapter = ArrayAdapter.createFromResource(this, R.array.zakatpertanian, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtjenispengairan.setAdapter(adapter);
    }

    public void hitung(View view) {
        jumlahhasilpanen = Double.parseDouble(txtjumlahhasilpanen.getText().toString());
        nishab = Double.parseDouble(txtnishab.getText().toString());
        String jenispengairan = txtjenispengairan.getSelectedItem().toString();
        if (jenispengairan != "Dengan Biaya") {
            hasil = jumlahhasilpanen * 0.005;
            if (nishab < hasil) {
                txtstatuszakat.setText("WAJIB MEMBAYAR ZAKAT");
                txthasil.setText("" + hasil);
            } else {
                txtstatuszakat.setText("TIDAK WAJIB MEMBAYAR ZAKAT");
                txthasil.setText("" + hasil);
            }
        }
        else {
            hasil = jumlahhasilpanen * 0.1;
            if (nishab < hasil) {
                txtstatuszakat.setText("WAJIB MEMBAYAR ZAKAT");
                txthasil.setText("" + hasil);
            } else {
                txtstatuszakat.setText("TIDAK WAJIB MEMBAYAR ZAKAT");
                txthasil.setText("" + hasil);
            }
        }
    }
}