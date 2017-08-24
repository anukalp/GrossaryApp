
package com.rxandroid.redmarttask.data.src.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Measure {


    @SerializedName("wt_or_vol")
    @Expose
    private String measureMetric;
    @SerializedName("vol_metric")
    @Expose
    private String volMetric;
    @SerializedName("wt")
    @Expose
    private Double wt;
    @SerializedName("wt_metric")
    @Expose
    private String wtMetric;
    @SerializedName("l")
    @Expose
    private Double l;
    @SerializedName("w")
    @Expose
    private Double w;
    @SerializedName("h")
    @Expose
    private Double h;

    public String getVolMetric() {
        return volMetric;
    }

    public void setVolMetric(String volMetric) {
        this.volMetric = volMetric;
    }

    public Double getWt() {
        return wt;
    }

    public void setWt(Double wt) {
        this.wt = wt;
    }

    public String getWtMetric() {
        return wtMetric;
    }

    public void setWtMetric(String wtMetric) {
        this.wtMetric = wtMetric;
    }

    public Double getL() {
        return l;
    }

    public void setL(Double l) {
        this.l = l;
    }

    public Double getW() {
        return w;
    }

    public void setW(Double w) {
        this.w = w;
    }

    public Double getH() {
        return h;
    }

    public void setH(Double h) {
        this.h = h;
    }

    public String getMeasureMetric() {
        return measureMetric;
    }

    public void setMeasureMetric(String measureMetric) {
        this.measureMetric = measureMetric;
    }


}
