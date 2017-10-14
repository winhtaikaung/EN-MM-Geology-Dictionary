package com.rangon.en_mmgeologydictionary.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by winhtaikaung on 17/7/17.
 */

public class Word {

    private String id;

    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("meaning_zg")
    @Expose
    private String meaningZg;
    @SerializedName("meaning_uni")
    @Expose
    private String meaningUni;
    private char character;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("is_fav")
    @Expose
    private Boolean isFav;

    public Word() {

    }

    /**
     * @param word
     * @param type
     * @param meaningZg
     * @param meaningUni
     * @param remark
     * @param isFav
     */
    public Word(String word, String type, String meaningZg, String meaningUni, String remark, boolean isFav) {
        this.word = word;
        this.type = type;
        this.meaningZg = meaningZg;
        this.meaningUni = meaningUni;
        this.remark = remark;
        this.isFav = isFav;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeaningZg() {
        return meaningZg;
    }

    public void setMeaningZg(String meaningZg) {
        this.meaningZg = meaningZg;
    }

    public String getMeaningUni() {
        return meaningUni;
    }

    public void setMeaningUni(String meaningUni) {
        this.meaningUni = meaningUni;
    }

    public char getCharacter() {
        return (!this.word.isEmpty() && this.word != null) ? this.word.toCharArray()[0] : ' ';
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    @Override
    public String toString() {
        Log.e("WORD_MODEL",id +"-"+word + type + meaningZg + remark);
        return id +"-"+word + type + meaningZg + remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
