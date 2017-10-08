package com.rangon.en_mmgeologydictionary.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by winhtaikaung on 9/10/17.
 */
public class MySharedPreference {
    private static MySharedPreference mySharedPreference;
    protected SharedPreferences mSharedPreferences;
    protected SharedPreferences.Editor mEditor;
    protected Context mcontext;

    public MySharedPreference(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mSharedPreferences.edit();
        this.mcontext = context;
    }

    public static synchronized MySharedPreference getInstance(Context context) {
        if (mySharedPreference == null) {
            mySharedPreference = new MySharedPreference(context);
        }
        return mySharedPreference;
    }

    public void setStringPreference(String Key, String Value) {
        mEditor.putString(Key, Value).apply();
    }

    public String getStringPreference(String Key, String DefValue) {
        return mSharedPreferences.getString(Key, DefValue);
    }

    public boolean getBooleanPreference(String Key, boolean DefValue) {
        return mSharedPreferences.getBoolean(Key, DefValue);
    }

    public void setIntegerPreference(String Key, int Value) {
        mEditor.putInt(Key, Value).apply();
    }

    public int getIntegerPreference(String Key, int DefValue) {
        return mSharedPreferences.getInt(Key, DefValue);
    }

    public void setBooleanPreference(String key, boolean value) {
        mEditor.putBoolean(key, value).apply();
    }
}
