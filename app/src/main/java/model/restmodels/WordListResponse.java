package model.restmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import model.Word;

/**
 * Created by win on 9/9/17.
 */

public class WordListResponse {

    @SerializedName("meta_data")
    @Expose
    private MetaData metaData;
    @SerializedName("data")
    @Expose
    private List<Word> data = null;

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<Word> getData() {
        return data;
    }

    public void setData(List<Word> data) {
        this.data = data;
    }
}
