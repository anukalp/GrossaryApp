
package com.rxandroid.redmarttask.data.src.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Discounts {

    @SerializedName("promo")
    @Expose
    private Promo promo;

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }

}
