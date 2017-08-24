
package com.rxandroid.redmarttask.data.src.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filters {

    @SerializedName("natural")
    @Expose
    private Integer natural;
    @SerializedName("vegan")
    @Expose
    private Integer vegan;
    @SerializedName("vendor_name")
    @Expose
    private String vendorName;
    @SerializedName("brand_uri")
    @Expose
    private String brandUri;
    @SerializedName("mfr_name")
    @Expose
    private String mfrName;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("frequency")
    @Expose
    private Integer frequency;
    @SerializedName("trending_frequency")
    @Expose
    private Integer trendingFrequency;
    @SerializedName("country_of_origin")
    @Expose
    private String countryOfOrigin;

    public Integer getNatural() {
        return natural;
    }

    public void setNatural(Integer natural) {
        this.natural = natural;
    }

    public Integer getVegan() {
        return vegan;
    }

    public void setVegan(Integer vegan) {
        this.vegan = vegan;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getBrandUri() {
        return brandUri;
    }

    public void setBrandUri(String brandUri) {
        this.brandUri = brandUri;
    }

    public String getMfrName() {
        return mfrName;
    }

    public void setMfrName(String mfrName) {
        this.mfrName = mfrName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getTrendingFrequency() {
        return trendingFrequency;
    }

    public void setTrendingFrequency(Integer trendingFrequency) {
        this.trendingFrequency = trendingFrequency;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

}
