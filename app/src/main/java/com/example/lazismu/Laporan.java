package com.example.lazismu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lazismu.retrofit.RetrofitService;
import com.example.lazismu.retrofit.response.ReportListResponse;
import com.example.lazismu.retrofit.response.User;
import com.example.lazismu.sharedpreference.SharedPreferenceHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Laporan extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private LaporanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);

        SharedPreferenceHelper sp = new SharedPreferenceHelper(this);
        User user = sp.getUser();
        String token = sp.getToken();

        if (user == null || token == null) {
            sp.clear();
            startActivity(new Intent(this, Login.class));
            finish();
        }

        SwipeRefreshLayout swipeLayout = findViewById(R.id.swipe_laporan);
        swipeLayout.setOnRefreshListener(() -> {
            loadData(adapter, swipeLayout, sp.getToken());
        });

        RecyclerView recyclerView = findViewById(R.id.daftarlaporan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LaporanAdapter();
        recyclerView.setAdapter(adapter);
        loadData(adapter, swipeLayout, sp.getToken());

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigator);
        bottomNavigationView.setSelectedItemId(R.id.laporannavigasi);

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

    void loadData(LaporanAdapter adapter, SwipeRefreshLayout refreshLayout, String token) {
        refreshLayout.setRefreshing(true);
        RetrofitService.getAuthorizedApiService(token).getReports().enqueue(new Callback<ReportListResponse>() {
            @Override
            public void onResponse(Call<ReportListResponse> call, Response<ReportListResponse> response) {
                ReportListResponse res = response.body();
                if (res == null) {
                    Toast.makeText(Laporan.this,"Ada galat",Toast.LENGTH_SHORT).show();
                    return;
                }
                adapter.setList(new ArrayList<>(res.getData().getData()));
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ReportListResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(Laporan.this,"Ada Galat",Toast.LENGTH_LONG).show();
                refreshLayout.setRefreshing(false);
            }
        });
    }
}