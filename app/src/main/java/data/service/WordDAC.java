package data.service;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Word;
import util.DBHelper;
import util.IndexingUtil;


public class WordDAC {
    static Context mContext;
    private static DBHelper dbhelper;
    private static WordDAC dac;
    IndexingUtil indexingUtil;

    public WordDAC instance(Context c) {
        mContext = c;
        if (dac == null) {

            dac = new WordDAC();
            indexingUtil = new IndexingUtil();
            mContext = c;
        }
        return dac;
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