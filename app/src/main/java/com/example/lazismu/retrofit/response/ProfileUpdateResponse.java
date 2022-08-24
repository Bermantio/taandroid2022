package com.example.lazismu.retrofit.response;

import com.google.gson.annotations.SerializedName;

public class ProfileUpdateResponse{

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public boolean isSuccess(){
		return success;
	}

	public String getMessage(){
		return message;
	}
}