package com.example.lazismu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lazismu.retrofit.response.DataItem;
import com.example.lazismu.retrofit.response.ReportData;
import com.google.firebase.database.core.Repo;

import java.util.ArrayList;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder> {

    private ArrayList<ReportData> list = new ArrayList<>();

    LaporanAdapter() { }

    void setList(ArrayList<ReportData> list) {
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
        ReportData item = list.get(position);
        holder.tanggalPenyaluran.setText("Tanggal Penyaluran: " + item.getTanggalPenyaluran());
        holder.name.setText("Nama Mustahik: " + item.getName());
        holder.nameProgram.setText("Nama Program: " + item.getNameProgram());
        holder.nominalLaporan.setText("Nominal: " + item.getJumlahDonasi());
    }

    static class LaporanViewHolder extends RecyclerView.ViewHolder {

        TextView tanggalPenyaluran, nameProgram, name, nominalLaporan;

        public LaporanViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggalPenyaluran = itemView.findViewById(R.id.tanggal_penyaluran);
            nameProgram = itemView.findViewById(R.id.name_program);
            name = itemView.findViewById(R.id.name);
            nominalLaporan = itemView.findViewById(R.id.nominal_laporan);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
