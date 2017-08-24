
package com.rxandroid.redmarttask.data.src.network.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("dag")
    @Expose
    private List<Object> dag = null;

    public List<Object> getDag() {
        return dag;
    }

    public void setDag(List<Object> dag) {
        this.dag = dag;
    }

}
