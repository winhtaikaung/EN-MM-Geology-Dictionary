package com.rangon.en_mmgeologydictionary.util;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by winhtaikaung on 19/7/17.
 */


public class DBHelper extends SQLiteOpenHelper {
    static String DB_NAME = "db_geodictionary.sqlite";
    private static String DB_PATH = "";
    private final Context myContext;
    private SQLiteDatabase myDataBase;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        File outfile = context.getDatabasePath(DB_NAME);
        DB_PATH = outfile.getPath();

    }

    public void createDataBase() throws IOException {
        //By calling this method and empty database will be created into the default system path
        //of your application so we are gonna be able to overwrite that database with our database.
        SQLiteDatabase db = this.getReadableDatabase();
        try {

            copyDataBase();

        } catch (IOException e) {
            throw new Error("Error copying database");
        }
        db.close();
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH;
            Log.e("DB_PATH", myPath);
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            checkDB.close();
        }

        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH;
        Log.e("NEW_DB", DB_PATH);

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH;
        Log.e("NEW_DB", DB_PATH);
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    public void MakeDB() {
        // if not exist
        try {
            createDataBase();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public ArrayList<HashMap<String, String>> getDataTable(String sql) {
        ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(sql, null);

        int columcount = res.getColumnCount();
        int rowcount = 0;

        res.moveToFirst();

        while (res.isAfterLast() == false) {
            HashMap<String, String> rowHashMap = new HashMap<String, String>();
            for (int i = 0; i < columcount; i++) {
                String cname = res.getColumnName(i);
                rowHashMap.put(cname, res.getString(i));
            }
            res.moveToNext();
            rows.add(rowHashMap);
        }
        db.close();
        return rows;
    }

    public ArrayList<HashMap<String, String>> getDataRow(String sql) {
        ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor res = db.rawQuery(sql, null);

            int columcount = res.getColumnCount();
            int rowcount = 0;
            res.moveToFirst();
            HashMap<String, String> rowHashMap = new HashMap<String, String>();
            for (int i = 0; i < columcount; i++) {
                String cname = res.getColumnName(i);
                rowHashMap.put(cname, res.getString(i));
            }
            rows.add(rowHashMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        db.close();
        return rows;
    }

    public void executeQuery(String SQL) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        try {
            database.execSQL(SQL);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            database.close();
        }

    }


    public void executeBulkQueries(ArrayList<String> arrSql) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        try {
            for (String sql : arrSql) {

                database.execSQL(sql);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            database.close();
        }

    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        SQLiteDatabase database = this.getWritableDatabase();
        String create_recent;
        create_recent = " CREATE TABLE IF NOT EXISTS  Recent (RecentID int, Word varchar(255));";
        database.execSQL(create_recent);
        onCreate(db);
    }


}
