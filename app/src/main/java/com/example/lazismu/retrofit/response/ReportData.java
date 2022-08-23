package com.example.lazismu.retrofit.response;

import com.google.gson.annotations.SerializedName;

public class ReportData {

    @SerializedName("id")
    private int id;

    @SerializedName("kode_transaksi")
    private String kodeTransaksi;

    @SerializedName("tanggal_penyaluran")
    private String tanggalPenyaluran;

    @SerializedName("name_program")
    private String nameProgram;

    @SerializedName("name")
    private String name;

    @SerializedName("jumlah_donasi")
    private String jumlahDonasi;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public int getId() {
        return id;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public String getTanggalPenyaluran() {
        return tanggalPenyaluran;
    }

    public String getNameProgram() {
        return nameProgram;
    }

    public String getName() {
        return name;
    }

    public String getJumlahDonasi() {
        return jumlahDonasi;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
