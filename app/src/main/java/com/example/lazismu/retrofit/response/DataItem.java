package com.example.lazismu.retrofit.response;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("image")
	private String image;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("jumlah_transaksi")
	private String jumlahTransaksi;

	@SerializedName("berupa")
	private String berupa;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("profesi")
	private String profesi;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("name_program")
	private String nameProgram;

	@SerializedName("notelepon")
	private String notelepon;

	@SerializedName("tanggal_transaksi")
	private String tanggalTransaksi;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("id")
	private int id;

	@SerializedName("name_zakki")
	private String nameZakki;

	@SerializedName("status")
	private String status;

	public String getImage(){
		return image;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public String getJumlahTransaksi(){
		return jumlahTransaksi;
	}

	public String getBerupa(){
		return berupa;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getProfesi(){
		return profesi;
	}

	public String getAlamat(){
		return alamat;
	}

	public String getNameProgram(){
		return nameProgram;
	}

	public String getNotelepon(){
		return notelepon;
	}

	public String getTanggalTransaksi(){
		return tanggalTransaksi;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getId(){
		return id;
	}

	public String getNameZakki(){
		return nameZakki;
	}

	public String getStatus(){
		return status;
	}
}