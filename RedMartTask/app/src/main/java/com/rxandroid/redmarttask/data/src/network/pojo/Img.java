
package com.rxandroid.redmarttask.data.src.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Img {

    @SerializedName("h")
    @Expose
    private Integer h;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("w")
    @Expose
    private Integer w;
    @SerializedName("position")
    @Expose
    private Integer position;

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

}
