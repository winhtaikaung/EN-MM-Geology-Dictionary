package com.rangon.en_mmgeologydictionary.model.configrestmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by win on 9/16/17.
 */

public class ConfigData {
    @SerializedName("config")
    @Expose
    private List<ApiConfig> config = null;

    public List<ApiConfig> getConfig() {
        return config;
    }

    public void setConfig(List<ApiConfig> config) {
        this.config = config;
    }
}
