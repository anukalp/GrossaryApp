
package com.rxandroid.redmarttask.data.src.network.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Facets {

    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
