package com.rangon.en_mmgeologydictionary.data.service;

import android.content.Context;
import android.util.Log;

import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.util.DBHelper;
import com.rangon.en_mmgeologydictionary.util.IndexingUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class WordDAL {
    static Context mContext;
    private static DBHelper dbhelper;
    private static WordDAL dac;
    IndexingUtil indexingUtil;

    public WordDAL(Context c) {
        mContext = c;
        indexingUtil = new IndexingUtil();
    }

    public List<Word> getLikelyWords(String o) {
        DBHelper db = new DBHelper(mContext);
        String tableName = indexingUtil.gettableName(o.charAt(0));
        String sql = "SELECT word,meaning_zg,type,is_fav FROM " + tableName + " WHERE word LIKE '" + o + "%'";
        Log.i("after query", "after query");
        ArrayList<HashMap<String, String>> alist = null;
        List<Word> wordlist = new ArrayList<>();
        try {

            alist = db.getDataTable(sql);
            Log.i("alist", String.valueOf(alist.size()));
            for (int i = 0; i < alist.size(); i++) {
                HashMap tablerow = (HashMap) alist.get(i);
                Word w = new Word();
                w.setWord(tablerow.get("word").toString().toLowerCase());
                w.setMeaningZg(tablerow.get("meaning_zg").toString().toLowerCase());
                w.setMeaningUni(tablerow.get("meaning_uni").toString());
                w.setType(tablerow.get("type").toString().toLowerCase());
                w.setFav(Boolean.valueOf(tablerow.get("is_fav").toString().toLowerCase()));
                wordlist.add(w);
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return wordlist;
    }

    public boolean InsertWord(Word o, String TableName) {

        DBHelper db = new DBHelper(mContext);
        String sql = "INSERT INTO " + TableName + " (word,type,meaning_zg) values ('" + o.getWord().toLowerCase() + "','" + o.getType() + "','" + o.getMeaningZg() + "',,'" + o.getMeaningUni() + "');";
        try {
            //db.(sql);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean bulkInsertWord(List<Word> listword, String tableName) {
        DBHelper db = new DBHelper(mContext);
        ArrayList<String> insertQueries = new ArrayList<>();
        for (Word o : listword) {
            String sql = "INSERT INTO " + tableName + " (id,word,type,meaning_zg,meaning_uni) values ('" + UUID.randomUUID().toString() + "','" + o.getWord().toLowerCase() + "','" + o.getType() + "','" + o.getMeaningZg() + "','" + o.getMeaningUni() + "');";
            insertQueries.add(sql);
        }
        try {
            db.bulkInsert(insertQueries);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean DeleteWord(Word o, String TableName) {

        DBHelper db = new DBHelper(mContext);
        String sql = "DELETE FROM " + TableName + " WHERE word='" + o.getWord() + "';";
        try {
            //db(sql);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Word getByWord(String inputWord) {
        Character firstCharacter;
        ArrayList aList;
        DBHelper db = new DBHelper(mContext);
        firstCharacter = inputWord.charAt(0);
        String TableName = indexingUtil.gettableName(firstCharacter);
        String sql = "SELECT * FROM " + TableName.toLowerCase() + " where word = '" + inputWord + "'";
        aList = db.getDataRow(sql);
        HashMap tableRow = (HashMap) aList.get(0);
        Word w = new Word();
        if (tableRow.size() != 0) {
            w.setWord(tableRow.get("word").toString().toLowerCase());
            w.setMeaningZg(tableRow.get("meaning_zg").toString());
            w.setMeaningUni(tableRow.get("meaning_uni").toString());
            w.setType(tableRow.get("type").toString().toLowerCase());
            w.setFav(Boolean.valueOf(tableRow.get("is_fav").toString().toLowerCase()));
            return w;
        } else {
            return w;
        }
    }


}