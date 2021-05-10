package com.pablotorres.ifoodist.data.repository;

import androidx.preference.PreferenceManager;

import com.pablotorres.ifoodist.iu.preferences.IFoodistPreferences;

public class Account {
    private static Account userAccount;
    private String user;

    static {
       userAccount = new Account();
    }

    public static Account getInstance(){
        return userAccount;
    }

    private Account(){
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getUser(){
        return user;
    }
}
