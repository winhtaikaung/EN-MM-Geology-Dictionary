package com.rangon.en_mmgeologydictionary.data.sharedPref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by winhtaikaung on 19/9/17.
 */

public class MySharedPref {

    private static MySharedPref mySharedPreference;
    protected SharedPreferences mSharedPreferences;
    protected SharedPreferences.Editor mEditor;
    protected Context mcontext;

    public MySharedPref(Context context){
        mSharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        mEditor=mSharedPreferences.edit();
        this.mcontext=context;
    }

    public static synchronized MySharedPref getInstance(Context context){
        if(mySharedPreference == null){
            mySharedPreference=new MySharedPref(context);
        }
        return  mySharedPreference;
    }

    public void setStringPreference(String Key,String Value){
        mEditor.putString(Key,Value).apply();
    }

    public String getStringPreference(String Key,String DefValue){
        return mSharedPreferences.getString(Key,DefValue);
    }

    public boolean getBooleanPreference(String Key,boolean DefValue){
        return mSharedPreferences.getBoolean(Key,DefValue);
    }

    public void setIntegerPreference(String Key,int Value){
        mEditor.putInt(Key,Value).apply();
    }

    public int getIntegerPreference(String Key,int DefValue){
        return mSharedPreferences.getInt(Key,DefValue);
    }

    public void setBooleanPreference(String key, boolean value){
        mEditor.putBoolean(key, value).apply();
    }
}
