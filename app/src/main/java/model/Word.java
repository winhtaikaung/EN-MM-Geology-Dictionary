package model;

/**
 * Created by winhtaikaung on 17/7/17.
 */

public class Word {
    private String word;
    private String type;
    private String meaningZg;
    private String meaningUni;
    private String character;
    private String remark;
    private boolean isFav;

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

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
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
}
