package com.example.lazismu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TentangPembayaran extends AppCompatActivity {
    ImageView batal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_pembayaran);

        batal = (ImageView) findViewById(R.id.kembalitentangpembayaran);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Tentang.class));
            }
        });
    }
}