package com.example.lazismu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class KalkulatorZakatPenghasilan extends AppCompatActivity {

    EditText txtpendapatanlain, txtpendapatan, txthutang, txthargaemas;
    Button hitung;
    TextView txtnishab, txthasil,txtstatuszakat;
    Double pendapatan, pendapatanlain, hutang, hasil, nishabi, hargaemas, penghasilan;
    ImageView batalarah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_zakat_penghasilan);

        batalarah = (ImageView)findViewById(R.id.kembalizakat);
        batalarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), KalkulatorZakat.class));
            }
        });

        txtpendapatan = (EditText) findViewById(R.id.txtpendapatan);
        txtpendapatanlain = (EditText) findViewById(R.id.txtpendapatanlain);
        txthutang = (EditText) findViewById(R.id.txthutang);
        txthasil = (TextView) findViewById(R.id.txthasil);
        txtnishab = (TextView) findViewById(R.id.txtnishab);
        txthargaemas = (EditText) findViewById(R.id.txthargaemas);
        txtstatuszakat = (TextView) findViewById(R.id.txtstatuszakat);
        hitung = (Button) findViewById(R.id.hitung);
    }

    public void hitung(View view) {
        pendapatan = Double.parseDouble(txtpendapatan.getText().toString());
        pendapatanlain = Double.parseDouble(txtpendapatanlain.getText().toString());
        hutang = Double.parseDouble(txthutang.getText().toString());
        hargaemas = Double.parseDouble(txthargaemas.getText().toString());
        nishabi = hargaemas * 85;
        Double n = new Double(nishabi);
        int nishab = n.intValue();
        txtnishab.setText("Rp. "+nishab);
        hasil = (pendapatan + pendapatanlain - hutang) * 0.025;
        penghasilan = pendapatan + pendapatanlain - hutang;
        Double h = new Double(hasil);
        int value = h.intValue();
        if (nishabi<penghasilan){
            txtstatuszakat.setText("WAJIB ZAKAT");
            txthasil.setText("Rp. "+value);
        }
        else {
            txtstatuszakat.setText("TIDAK WAJIB ZAKAT");
            txthasil.setText("-");
        }
    }
}