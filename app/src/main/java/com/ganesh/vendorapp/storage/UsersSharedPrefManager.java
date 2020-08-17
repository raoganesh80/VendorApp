package com.ganesh.vendorapp.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.ganesh.vendorapp.models.User;

public class UsersSharedPrefManager {

    private static final String SHARED_PREF_NAME = "user_shared_preff";

    private static UsersSharedPrefManager mInstance;
    private Context mCtx;

    private UsersSharedPrefManager(Context mCtx){
        this.mCtx = mCtx;
    }

    public static synchronized UsersSharedPrefManager getInstance(Context mCtx) {
        if(mInstance == null) {
            mInstance = new UsersSharedPrefManager(mCtx);
        }
        return mInstance;
    }

    public void setLoginWith(String loginWith,String phoneNo) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("login_with", loginWith);
        editor.putString("phone_no", phoneNo);
        editor.apply();
    }
    public void setLoginWith(String loginWith,String email,String username) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("login_with", loginWith);
        editor.putString("email", email);
        editor.putString("fullname",username);
        editor.apply();
    }

    public String LoginWith() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("login_with",null);
    }
    public String getPhoneNo(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("phone_no",null);
    }
    public String getEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("email",null);
    }


    public void saveUser(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("uid", user.getUid());
        editor.putString("fullname", user.getFullName());
        editor.putString("phone_no", user.getPhoneNo());
        editor.putString("email", user.getEmail());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("uid",null) != null;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString("uid",null),
                sharedPreferences.getString("fullname",null),
                sharedPreferences.getString("phone_no",null),
                sharedPreferences.getString("email",null)
        );
    }

    public void clear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}