
package com.rxandroid.redmarttask.data.src.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Warehouse {

    @SerializedName("measure")
    @Expose
    private Measure measure;

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

}
