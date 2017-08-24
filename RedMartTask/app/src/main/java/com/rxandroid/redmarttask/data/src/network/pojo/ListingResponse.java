
package com.rxandroid.redmarttask.data.src.network.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListingResponse {

    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("facets")
    @Expose
    private Facets facets;
    @SerializedName("filters")
    @Expose
    private Filters_ filters;
    @SerializedName("on_sale_count")
    @Expose
    private Integer onSaleCount;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("page_size")
    @Expose
    private Integer pageSize;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Facets getFacets() {
        return facets;
    }

    public void setFacets(Facets facets) {
        this.facets = facets;
    }

    public Filters_ getFilters() {
        return filters;
    }

    public void setFilters(Filters_ filters) {
        this.filters = filters;
    }

    public Integer getOnSaleCount() {
        return onSaleCount;
    }

    public void setOnSaleCount(Integer onSaleCount) {
        this.onSaleCount = onSaleCount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
