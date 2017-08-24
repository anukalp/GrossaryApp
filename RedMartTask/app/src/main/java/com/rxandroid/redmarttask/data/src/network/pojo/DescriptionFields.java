
package com.rxandroid.redmarttask.data.src.network.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DescriptionFields {

    @SerializedName("secondary")
    @Expose
    private List<Secondary> secondary = null;
    @SerializedName("primary")
    @Expose
    private List<Object> primary = null;

    public List<Secondary> getSecondary() {
        return secondary;
    }

    public void setSecondary(List<Secondary> secondary) {
        this.secondary = secondary;
    }

    public List<Object> getPrimary() {
        return primary;
    }

    public void setPrimary(List<Object> primary) {
        this.primary = primary;
    }

}
