
package com.rxandroid.redmarttask.data.src.network.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("category_tags")
    @Expose
    private List<String> categoryTags = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("categories")
    @Expose
    private List<Integer> categories = null;
    @SerializedName("types")
    @Expose
    private List<Integer> types = null;
    @SerializedName("details")
    @Expose
    private Details details;
    @SerializedName("product_life")
    @Expose
    private ProductLife productLife;
    @SerializedName("filters")
    @Expose
    private Filters filters;
    @SerializedName("img")
    @Expose
    private Img img;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("measure")
    @Expose
    private Measure measure;
    @SerializedName("pricing")
    @Expose
    private Pricing pricing;
    @SerializedName("warehouse")
    @Expose
    private Warehouse warehouse;
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;
    @SerializedName("description_fields")
    @Expose
    private DescriptionFields descriptionFields;
    @SerializedName("promotions")
    @Expose
    private List<Promotion> promotions = null;
    @SerializedName("inventory")
    @Expose
    private Inventory inventory;
    @SerializedName("pr")
    @Expose
    private Integer pr;

    public List<String> getCategoryTags() {
        return categoryTags;
    }

    public void setCategoryTags(List<String> categoryTags) {
        this.categoryTags = categoryTags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<Integer> getTypes() {
        return types;
    }

    public void setTypes(List<Integer> types) {
        this.types = types;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public ProductLife getProductLife() {
        return productLife;
    }

    public void setProductLife(ProductLife productLife) {
        this.productLife = productLife;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public Img getImg() {
        return img;
    }

    public void setImg(Img img) {
        this.img = img;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public DescriptionFields getDescriptionFields() {
        return descriptionFields;
    }

    public void setDescriptionFields(DescriptionFields descriptionFields) {
        this.descriptionFields = descriptionFields;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Integer getPr() {
        return pr;
    }

    public void setPr(Integer pr) {
        this.pr = pr;
    }

}
