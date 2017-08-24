
package com.rxandroid.redmarttask.data.src.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pricing {

    @SerializedName("promo_id")
    @Expose
    private Integer promoId;
    @SerializedName("on_sale")
    @Expose
    private Integer onSale;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("promo_price")
    @Expose
    private Double promoPrice;
    @SerializedName("savings")
    @Expose
    private Double savings;
    @SerializedName("savings_amount")
    @Expose
    private Double savingsAmount;
    @SerializedName("savings_type")
    @Expose
    private Integer savingsType;
    @SerializedName("savings_text")
    @Expose
    private String savingsText;
    @SerializedName("discounts")
    @Expose
    private Discounts discounts;
    @SerializedName("applicable_discount")
    @Expose
    private String applicableDiscount;

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public Integer getOnSale() {
        return onSale;
    }

    public void setOnSale(Integer onSale) {
        this.onSale = onSale;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(Double promoPrice) {
        this.promoPrice = promoPrice;
    }

    public Double getSavings() {
        return savings;
    }

    public void setSavings(Double savings) {
        this.savings = savings;
    }

    public Double getSavingsAmount() {
        return savingsAmount;
    }

    public void setSavingsAmount(Double savingsAmount) {
        this.savingsAmount = savingsAmount;
    }

    public Integer getSavingsType() {
        return savingsType;
    }

    public void setSavingsType(Integer savingsType) {
        this.savingsType = savingsType;
    }

    public String getSavingsText() {
        return savingsText;
    }

    public void setSavingsText(String savingsText) {
        this.savingsText = savingsText;
    }

    public Discounts getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Discounts discounts) {
        this.discounts = discounts;
    }

    public String getApplicableDiscount() {
        return applicableDiscount;
    }

    public void setApplicableDiscount(String applicableDiscount) {
        this.applicableDiscount = applicableDiscount;
    }

}
