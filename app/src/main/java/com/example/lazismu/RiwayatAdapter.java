package com.example.lazismu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RiwayatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<transaksinontunai> list = new ArrayList<>();
    public RiwayatAdapter(Context ctx){
        this.context = ctx;
    }
    public void setItems (ArrayList<transaksinontunai> emp){
        list.addAll(emp);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.riwayatitem,parent,false);
        return new RiwayatVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RiwayatVH vh = (RiwayatVH) holder;
        transaksinontunai emp = list.get(position);
        vh.tanggal_transaksi.setText(emp.getTanggaltransaksi());
        vh.name_program.setText(emp.getProgram());
        vh.berupa.setText(emp.getBerupa());
        vh.jumlahtransaksi.setText(emp.getNominal());
        vh.status.setText(emp.getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
