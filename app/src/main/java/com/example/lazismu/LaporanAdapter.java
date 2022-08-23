package com.example.lazismu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lazismu.retrofit.response.DataItem;
import com.example.lazismu.retrofit.response.DonationItem;

import java.util.ArrayList;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder> {

    private ArrayList<DataItem> list;

    LaporanAdapter(ArrayList<DataItem> list) {
        this.list = list;
    }

    void setList(ArrayList<DataItem> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    @NonNull
    @Override
    public LaporanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.laporanitem, parent, false);
        return new LaporanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaporanViewHolder holder, int position) {
        DataItem item = list.get(position);
        holder.name.setText(item.getNameZakki());
        holder.alamat.setText(item.getAlamat());
        holder.nameProgram.setText(item.getNameProgram());
        holder.tanggalPenyaluran.setText(item.getTanggalTransaksi());
    }

    static class LaporanViewHolder extends RecyclerView.ViewHolder {

        TextView tanggalPenyaluran, nameProgram, name, alamat;

        public LaporanViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggalPenyaluran = itemView.findViewById(R.id.tanggal_penyaluran);
            nameProgram = itemView.findViewById(R.id.name_program);
            name = itemView.findViewById(R.id.name);
            alamat = itemView.findViewById(R.id.alamat);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
