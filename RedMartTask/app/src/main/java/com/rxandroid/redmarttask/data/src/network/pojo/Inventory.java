
package com.rxandroid.redmarttask.data.src.network.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inventory {

    @SerializedName("atp_status")
    @Expose
    private Integer atpStatus;
    @SerializedName("max_sale_qty")
    @Expose
    private Integer maxSaleQty;
    @SerializedName("qty_in_carts")
    @Expose
    private Integer qtyInCarts;
    @SerializedName("qty_in_stock")
    @Expose
    private Integer qtyInStock;
    @SerializedName("stock_status")
    @Expose
    private Integer stockStatus;
    @SerializedName("stock_type")
    @Expose
    private Integer stockType;
    @SerializedName("atp_lots")
    @Expose
    private List<AtpLot> atpLots = null;
    @SerializedName("next_available_date")
    @Expose
    private String nextAvailableDate;
    @SerializedName("limited_stock_status")
    @Expose
    private Integer limitedStockStatus;

    public Integer getAtpStatus() {
        return atpStatus;
    }

    public void setAtpStatus(Integer atpStatus) {
        this.atpStatus = atpStatus;
    }

    public Integer getMaxSaleQty() {
        return maxSaleQty;
    }

    public void setMaxSaleQty(Integer maxSaleQty) {
        this.maxSaleQty = maxSaleQty;
    }

    public Integer getQtyInCarts() {
        return qtyInCarts;
    }

    public void setQtyInCarts(Integer qtyInCarts) {
        this.qtyInCarts = qtyInCarts;
    }

    public Integer getQtyInStock() {
        return qtyInStock;
    }

    public void setQtyInStock(Integer qtyInStock) {
        this.qtyInStock = qtyInStock;
    }

    public Integer getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Integer stockStatus) {
        this.stockStatus = stockStatus;
    }

    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

    public List<AtpLot> getAtpLots() {
        return atpLots;
    }

    public void setAtpLots(List<AtpLot> atpLots) {
        this.atpLots = atpLots;
    }

    public String getNextAvailableDate() {
        return nextAvailableDate;
    }

    public void setNextAvailableDate(String nextAvailableDate) {
        this.nextAvailableDate = nextAvailableDate;
    }

    public Integer getLimitedStockStatus() {
        return limitedStockStatus;
    }

    public void setLimitedStockStatus(Integer limitedStockStatus) {
        this.limitedStockStatus = limitedStockStatus;
    }

}
