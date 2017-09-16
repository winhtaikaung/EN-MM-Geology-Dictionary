package com.rangon.en_mmgeologydictionary.model.configrestmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by win on 9/16/17.
 */

public class ApiConfig {

    @SerializedName("page_count")
    @Expose
    private Integer pageCount;
    @SerializedName("wordIndex")
    @Expose
    private String wordIndex;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getWordIndex() {
        return wordIndex;
    }

    public void setWordIndex(String wordIndex) {
        this.wordIndex = wordIndex;
    }

}
