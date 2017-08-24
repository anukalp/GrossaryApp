
package com.rxandroid.redmarttask.data.src.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AtpLot {

    @SerializedName("from_date")
    @Expose
    private String fromDate;
    @SerializedName("to_date")
    @Expose
    private String toDate;
    @SerializedName("stock_status")
    @Expose
    private Integer stockStatus;
    @SerializedName("qty_in_stock")
    @Expose
    private Integer qtyInStock;
    @SerializedName("qty_in_carts")
    @Expose
    private Integer qtyInCarts;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Integer getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Integer stockStatus) {
        this.stockStatus = stockStatus;
    }

    public Integer getQtyInStock() {
        return qtyInStock;
    }

    public void setQtyInStock(Integer qtyInStock) {
        this.qtyInStock = qtyInStock;
    }

    public Integer getQtyInCarts() {
        return qtyInCarts;
    }

    public void setQtyInCarts(Integer qtyInCarts) {
        this.qtyInCarts = qtyInCarts;
    }

}
