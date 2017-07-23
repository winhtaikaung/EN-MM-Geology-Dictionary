package data.repository.service;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Word;
import util.DBHelper;
import util.IndexingUtil;


public class WordDAC {
    private static List<Word> lstword = new ArrayList();
    private static List<String> strlstword = new ArrayList();
    static Context mContext;
    private static DBHelper dbhelper;
    private static WordDAC dac;

    public WordDAC instance(Context c) {
        mContext = c;
        if (dac == null) {

            dac = new WordDAC();
        }
        return dac;
    }

    public static List<Word> getLikelyWords(Word o, String TableName) {
        DBHelper db = new DBHelper(mContext);
        String sql = "SELECT word,desc,type,IsFavourite FROM " + TableName + " WHERE word LIKE '" + o.getWord() + "%'";
        Log.i("after query", "after query");
        ArrayList<HashMap<String, String>> alist = null;
        List<Word> wordlist = new ArrayList<>();
        alist = db.getDataTable(sql);
        Log.i("alist", String.valueOf(alist.size()));
        for (int i = 0; i < alist.size(); i++) {
            HashMap tablerow = (HashMap) alist.get(i);
            Word w = new Word();
            w.setWord(tablerow.get("word").toString().toLowerCase());
            w.setMeaningZg(tablerow.get("desc").toString().toLowerCase());
            w.setType(tablerow.get("type").toString().toLowerCase());
            w.setFav(Boolean.valueOf(tablerow.get("IsFavourite").toString().toLowerCase()));
            wordlist.add(w);
        }
        return wordlist;
    }

    public boolean InsertWord(Word o, String TableName) {

        DBHelper db = new DBHelper(mContext);
        String sql = "INSERT INTO " + TableName + " (word,type,desc) values ('" + o.getWord().toLowerCase() + "','" + o.getType() + "','" + o.getMeaningZg() + "',,'" + o.getMeaningUni() + "');";
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
        IndexingUtil index = new IndexingUtil();
        String TableName = index.gettableName(firstCharacter);
        String sql = "SELECT * FROM " + TableName.toLowerCase() + " where word = '" + inputWord + "'";
        aList = db.getDataRow(sql);
        HashMap tableRow = (HashMap) aList.get(0);
        Word w = new Word();
        if (tableRow.size() != 0) {
            w.setWord(tableRow.get("word").toString().toLowerCase());
            w.setMeaningZg(tableRow.get("desc").toString());
            w.setType(tableRow.get("type").toString().toLowerCase());
            w.setFav(Boolean.valueOf(tableRow.get("IsFavourite").toString().toLowerCase()));
            return w;
        } else {
            return w;
        }
    }
}