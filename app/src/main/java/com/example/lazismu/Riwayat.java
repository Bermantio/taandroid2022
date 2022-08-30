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
import com.example.lazismu.retrofit.response.DataItem;
import com.example.lazismu.retrofit.response.DonationListResponse;
import com.example.lazismu.retrofit.response.User;
import com.example.lazismu.sharedpreference.SharedPreferenceHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Riwayat extends AppCompatActivity {

    RecyclerView recyclerView;
    RiwayatAdapter adapter;
    private SwipeRefreshLayout swipeLayout;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        SharedPreferenceHelper sp = new SharedPreferenceHelper(this);
        User user = sp.getUser();
        String token = sp.getToken();

        if (user == null || token == null) {
            sp.clear();
            startActivity(new Intent(this, Login.class));
            finish();
        }

        recyclerView = findViewById(R.id.daftarriwayat);
        recyclerView.setHasFixedSize(true);

        swipeLayout = findViewById(R.id.swipe_riwayat);
        swipeLayout.setOnRefreshListener(() -> loadData(token, user.getName()));

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new RiwayatAdapter(this);
        recyclerView.setAdapter(adapter);
        loadData(token, user.getName());

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

    private void loadData(String token, String username) {
        swipeLayout.setRefreshing(true);
        RetrofitService.getAuthorizedApiService(token).getDonations().enqueue(new Callback<DonationListResponse>() {
            @Override
            public void onResponse(Call<DonationListResponse> call, Response<DonationListResponse> response) {
                DonationListResponse res = response.body();
                if (res == null) {
                    Toast.makeText(Riwayat.this,"Ada galat",Toast.LENGTH_SHORT).show();
                    return;
                }
                List<DataItem> temp = new ArrayList<>();
                for (DataItem item: res.getData()) {
                    if (item.getNameZakki().equals(username)) temp.add(item);
                }

                adapter.setItems(new ArrayList<>(temp));
                adapter.notifyDataSetChanged();
                swipeLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<DonationListResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(Riwayat.this,"Ada Galat",Toast.LENGTH_LONG).show();
                swipeLayout.setRefreshing(false);
            }
        });
    }
}