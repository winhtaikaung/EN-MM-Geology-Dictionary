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
        String sql = "SELECT DISTINCT id,word,meaning_zg,meaning_uni,type,is_fav FROM " + tableName + " WHERE word LIKE '" + o + "%' LIMIT 10";
        Log.i("after query", "after query");
        ArrayList<HashMap<String, String>> alist = null;
        List<Word> wordlist = new ArrayList<>();
        try {

            alist = db.getDataTable(sql);
            Log.i("alist", String.valueOf(alist.size()));
            for (int i = 0; i < alist.size(); i++) {
                HashMap tableRow = (HashMap) alist.get(i);
                Word w = new Word();
                w.setId(tableRow.get("id").toString());
                w.setWord(tableRow.get("word").toString().replace("''", "'").toLowerCase());
                w.setMeaningZg(tableRow.get("meaning_zg").toString().replace("''", "'"));
                w.setMeaningUni(tableRow.get("meaning_uni").toString().replace("''", "'"));
                w.setType(tableRow.get("type").toString().toLowerCase());
                w.setFav(Boolean.valueOf(tableRow.get("is_fav").toString().toLowerCase()));
                wordlist.add(w);
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return wordlist;
    }

    public boolean updateRecent(String inputWord, String id) {
        DBHelper db = new DBHelper(mContext);
        char tableName = inputWord.charAt(0);
        String sql = "UPDATE" + tableName + " SET is_fav = 'true',remark = datetime('now') WHERE id='" + id + "';";
        try {
            db.executeQuery(sql);
            return true;
        } catch (Exception ex) {
            return false;
        }
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
            String sql = "INSERT INTO " + tableName + " (id,word,type,meaning_zg,meaning_uni,is_fav) values ('" + UUID.randomUUID().toString() + "','" + o.getWord().toLowerCase().replace("'", "''") + "','" + o.getType() + "','" + o.getMeaningZg().replace("'", "''") + "','" + o.getMeaningUni().replace("'", "''") + "','" + 0 + "');";
            insertQueries.add(sql);
        }
        try {
            db.executeBulkQueries(insertQueries);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean deleteWord(Word o, String TableName) {

        DBHelper db = new DBHelper(mContext);
        String sql = "DELETE FROM " + TableName + " WHERE word='" + o.getWord() + "';";
        try {
            //db(sql);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean deleteWordTable(String[] tableNames) {

        DBHelper db = new DBHelper(mContext);
        ArrayList<String> deleteQueries = new ArrayList<>();
        try {
            for (String tableName : tableNames) {
                String sql = "DELETE FROM " + tableName;
                deleteQueries.add(sql);

            }
            db.executeBulkQueries(deleteQueries);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Word getByWord(String inputWord, String id) {
        Character firstCharacter;
        ArrayList aList;
        DBHelper db = new DBHelper(mContext);
        firstCharacter = inputWord.charAt(0);
        String TableName = indexingUtil.gettableName(firstCharacter);
        String sql = "SELECT * FROM " + TableName.toLowerCase() + " where word = '" + inputWord + "'AND id = '" + id + "'";
        aList = db.getDataRow(sql);
        HashMap tableRow = (HashMap) aList.get(0);
        Word w = new Word();
        if (tableRow.size() != 0) {
            w.setId(tableRow.get("id").toString());
            w.setWord(tableRow.get("word").toString().replace("''", "'").toLowerCase());
            w.setMeaningZg(tableRow.get("meaning_zg").toString().replace("''", "'"));
            w.setMeaningUni(tableRow.get("meaning_uni").toString().replace("''", "'"));
            w.setType(tableRow.get("type").toString().toLowerCase());
            w.setFav(Boolean.valueOf(tableRow.get("is_fav").toString().toLowerCase()));
            return w;
        } else {
            return w;
        }
    }


}