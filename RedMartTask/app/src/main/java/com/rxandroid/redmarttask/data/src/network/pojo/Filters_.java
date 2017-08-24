
package com.rxandroid.redmarttask.data.src.network.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filters_ {

    @SerializedName("options")
    @Expose
    private List<Option> options = null;
    @SerializedName("toggles")
    @Expose
    private List<Toggle> toggles = null;

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Toggle> getToggles() {
        return toggles;
    }

    public void setToggles(List<Toggle> toggles) {
        this.toggles = toggles;
    }

}
