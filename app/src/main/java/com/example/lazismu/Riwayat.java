package com.example.lazismu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Riwayat extends AppCompatActivity {

    RecyclerView recyclerView;
    RiwayatAdapter adapter;
    DAOTransaksiNonTunai dao;
    private SwipeRefreshLayout swipeLayout;
    /*private FirebaseAuth authProfil = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = authProfil.getCurrentUser();*/

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        recyclerView = findViewById(R.id.daftarriwayat);
        recyclerView.setHasFixedSize(true);

        swipeLayout = findViewById(R.id.swipe_riwayat);
        swipeLayout.setOnRefreshListener(() -> {
            dao.get().get().addOnSuccessListener(this, (snapshot) -> {
                ArrayList<transaksinontunai> emps = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren())
                {
                    transaksinontunai emp = data.getValue(transaksinontunai.class);
                    emps.add(emp);
                }
                adapter.setItems(emps);
                adapter.notifyDataSetChanged();
                swipeLayout.setRefreshing(false);
            }).addOnFailureListener(this, e -> {
                Toast.makeText(Riwayat.this,"Ada Galat",Toast.LENGTH_LONG).show();
                swipeLayout.setRefreshing(false);
            }).addOnCanceledListener(this, () -> swipeLayout.setRefreshing(false));
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new RiwayatAdapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOTransaksiNonTunai();
        loadData();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigator);
        bottomNavigationView.setSelectedItemId(R.id.riwayatnavigasi);

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

    /*private void setUpRecyclerView(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();
        Query query = FirebaseDatabase.getInstance().getReference(transaksinontunai).child("Registered Users").child(userID);

        FirebaseRecyclerOptions<Results> options = new FirebaseRecyclerOptions.Builder<Results>()
                .setQuery(query, Results.class)
                .build();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new RiwayatAdapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOTransaksiNonTunai();
        loadData();
    }*/

    private void loadData() {
        swipeLayout.setRefreshing(true);
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<transaksinontunai> emps = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren())
                {
                    transaksinontunai emp = data.getValue(transaksinontunai.class);
                    emps.add(emp);
                }
                adapter.setItems(emps);
                adapter.notifyDataSetChanged();
                swipeLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Riwayat.this,"Ada Galat",Toast.LENGTH_LONG).show();
                swipeLayout.setRefreshing(false);
            }
        });
    }
}