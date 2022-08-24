package com.example.lazismu.retrofit.response;

import com.google.gson.annotations.SerializedName;

public class User{
	public void setRole(String role) {
		this.role = role;
	}

	public void setNotelepon(String notelepon) {
		this.notelepon = notelepon;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProfesi(String profesi) {
		this.profesi = profesi;
	}

	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	@SerializedName("image")
	private String image;

	@SerializedName("role")
	private String role;

	@SerializedName("notelepon")
	private String notelepon;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("email_verified_at")
	private String emailVerifiedAt;

	@SerializedName("id")
	private int id;

	@SerializedName("profesi")
	private String profesi;

	@SerializedName("jenis_kelamin")
	private String jenisKelamin;

	@SerializedName("email")
	private String email;

	@SerializedName("alamat")
	private String alamat;

	public String getImage(){
		return image;
	}

	public String getRole(){
		return role;
	}

	public String getPhoneNumber(){
		return notelepon;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getName(){
		return name;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public int getId(){
		return id;
	}

	public String getProfession(){
		return profesi;
	}

	public String getGender(){
		return jenisKelamin;
	}

	public String getEmail(){
		return email;
	}

	public String getAddress(){
		return alamat;
	}
}