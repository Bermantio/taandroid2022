package com.example.lazismu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class KalkulatorZakatTabungan extends AppCompatActivity {

    EditText txtsaldotabungan, txtbunga, txthargaemas;
    Button hitung;
    TextView txtnishab, txthasil, txtstatuszakat;
    Double saldotabungan, bunga, hasil, nishabi, hargaemas;
    ImageView batalarah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_zakat_tabungan);

        batalarah = (ImageView) findViewById(R.id.kembalizakat);
        batalarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), KalkulatorZakat.class));
            }
        });

        txtsaldotabungan = (EditText) findViewById(R.id.txtsaldotabungan);
        txtbunga = (EditText) findViewById(R.id.txtbunga);
        txthasil = (TextView) findViewById(R.id.txthasil);
        txtnishab = (TextView) findViewById(R.id.txtnishab);
        txthargaemas = (EditText) findViewById(R.id.txthargaemas);
        txtstatuszakat = (TextView) findViewById(R.id.txtstatuszakat);
        hitung = (Button) findViewById(R.id.hitung);
    }

    public void hitung(View view) {
        saldotabungan = Double.parseDouble(txtsaldotabungan.getText().toString());
        bunga = Double.parseDouble(txtbunga.getText().toString());
        hargaemas = Double.parseDouble(txthargaemas.getText().toString());
        nishabi = hargaemas * 85;
        Double n = new Double(nishabi);
        int nishab = n.intValue();
        txtnishab.setText("Rp. " + nishab);
        hasil = (saldotabungan - bunga) * 0.025;
        Double h = new Double(hasil);
        int value = h.intValue();
        if (nishabi < hasil) {
            txtstatuszakat.setText("WAJIB MEMBAYAR ZAKAT");
            txthasil.setText("Rp. " + value);
        } else {
            txtstatuszakat.setText("TIDAK WAJIB MEMBAYAR ZAKAT");
            txthasil.setText("Rp. "+ value);
        }
    }
}