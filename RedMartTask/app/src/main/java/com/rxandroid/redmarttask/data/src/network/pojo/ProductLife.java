
package com.rxandroid.redmarttask.data.src.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductLife {

    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("metric")
    @Expose
    private String metric;
    @SerializedName("is_minimum")
    @Expose
    private Boolean isMinimum;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Boolean getIsMinimum() {
        return isMinimum;
    }

    public void setIsMinimum(Boolean isMinimum) {
        this.isMinimum = isMinimum;
    }

}
