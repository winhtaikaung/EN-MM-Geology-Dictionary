package com.rangon.en_mmgeologydictionary.model;

/**
 * Created by win on 9/16/17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rangon.en_mmgeologydictionary.model.configrestmodels.ConfigData;
import com.rangon.en_mmgeologydictionary.model.restmodels.MetaData;

public class ConfigResponse {

    @SerializedName("meta_data")
    @Expose
    private MetaData metaData;
    @SerializedName("data")
    @Expose
    private ConfigData data;

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public ConfigData getData() {
        return data;
    }

    public void setData(ConfigData data) {
        this.data = data;
    }

}
