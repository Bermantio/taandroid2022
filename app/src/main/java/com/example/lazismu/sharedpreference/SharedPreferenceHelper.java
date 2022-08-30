package com.example.lazismu.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lazismu.retrofit.response.User;
import com.google.gson.Gson;

public class SharedPreferenceHelper {

    private static final String SHARED_PREFERENCES = "shared_preferences";
    private static final String KEY_TOKEN = "key_token";
    private static final String USER_TOKEN = "user_token";
    private final SharedPreferences sp;

    public SharedPreferenceHelper(Context context) {
        sp = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setToken(String value) {
        sp.edit().putString(KEY_TOKEN, value).apply();
    }

    public String getToken() {
        return sp.getString(KEY_TOKEN, "");
    }

    public void setUser(User user) {
        Gson gson = new Gson();
        sp.edit().putString(USER_TOKEN, gson.toJson(user)).apply();
    }

    public User getUser() {
        Gson gson = new Gson();
        String userJson = sp.getString(USER_TOKEN, "");
        return gson.fromJson(userJson, User.class);
    }

    public void clear() {
        sp.edit().clear().apply();
    }
}
