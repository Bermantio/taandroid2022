package com.example.lazismu.retrofit.response;

import com.google.gson.annotations.SerializedName;

public class DonationResponse{

	@SerializedName("data")
	private DonationItem donationData;

	public DonationItem getData(){
		return donationData;
	}
}