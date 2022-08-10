package com.example.lazismu;

public class transaksinontunai {
    public String tanggaltransaksi, nama,alamat,nomor,profesi,program,keterangan,berupa,nominal;

    public transaksinontunai(){}

    public transaksinontunai(String tanggaltransaksi, String nama, String alamat, String nomor, String profesi, String program, String keterangan, String berupa, String nominal) {
        this.tanggaltransaksi = tanggaltransaksi;
        this.nama = nama;
        this.alamat = alamat;
        this.nomor = nomor;
        this.profesi = profesi;
        this.program = program;
        this.keterangan = keterangan;
        this.berupa = berupa;
        this.nominal = nominal;
    }

    public String getTanggaltransaksi() {
        return tanggaltransaksi;
    }

    public void setTanggaltransaksi(String tanggaltransaksi) {
        this.tanggaltransaksi = tanggaltransaksi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getProfesi() {
        return profesi;
    }

    public void setProfesi(String profesi) {
        this.profesi = profesi;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getBerupa() {
        return berupa;
    }

    public void setBerupa(String berupa) {
        this.berupa = berupa;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }
}
