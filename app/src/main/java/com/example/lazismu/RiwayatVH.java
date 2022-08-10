package com.example.lazismu;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RiwayatVH extends RecyclerView.ViewHolder {
    public TextView status, jumlahtransaksi, berupa, name_program, tanggal_transaksi;
    public  RiwayatVH(@NonNull View itemView)
    {
        super(itemView);
        name_program = itemView.findViewById(R.id.name_program);
        status = itemView.findViewById(R.id.status);
        jumlahtransaksi = itemView.findViewById(R.id.jumlahtransaksi);
        berupa = itemView.findViewById(R.id.berupa);
        tanggal_transaksi = itemView.findViewById(R.id.tanggal_transaksi);
    }
}
