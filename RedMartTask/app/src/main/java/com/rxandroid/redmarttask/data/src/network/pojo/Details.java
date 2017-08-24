
package com.rxandroid.redmarttask.data.src.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("prod_type")
    @Expose
    private Integer prodType;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("is_new")
    @Expose
    private Double isNew;
    @SerializedName("storage_class")
    @Expose
    private String storageClass;
    @SerializedName("country_of_origin")
    @Expose
    private String countryOfOrigin;

    public Integer getProdType() {
        return prodType;
    }

    public void setProdType(Integer prodType) {
        this.prodType = prodType;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getIsNew() {
        return isNew;
    }

    public void setIsNew(Double isNew) {
        this.isNew = isNew;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

}
