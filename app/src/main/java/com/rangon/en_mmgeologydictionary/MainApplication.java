package com.rangon.en_mmgeologydictionary;

import android.app.Application;

import com.rangon.en_mmgeologydictionary.util.DBHelper;

/**
 * Created by winhtaikaung on 14/9/17.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.MakeDB();
    }
}
