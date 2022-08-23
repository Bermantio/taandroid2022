package com.example.lazismu.retrofit.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	@SerializedName("user")
	private User user;

	@SerializedName("token")
	private String token;

	public boolean isSuccess(){
		return success;
	}

	public String getMessage(){
		return message;
	}

	public User getUser(){
		return user;
	}

	public String getToken(){
		return token;
	}
}