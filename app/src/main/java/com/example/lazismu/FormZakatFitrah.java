package com.example.lazismu;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormZakatFitrah extends AppCompatActivity {

    EditText txtanggota, txthargaberas;
    Double anggota, hargaberas, nominal;
    ImageView batalarah;
    Button batalbutton;
    Spinner txtberupa;
    ArrayAdapter<CharSequence> adapter;
    TextView txttanggaltransaksi, txtnominal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_zakat_fitrah);

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

        return Double.toString(number1 * number2 * 2.5);
    }
}