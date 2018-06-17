package com.greet.special.model

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

open class Data {
    @SerializedName("data")
    @Expose
    private var data: MutableList<CategoryResponse>? = null

    fun getData(): MutableList<CategoryResponse>? {
        return data
    }

    fun setData(data: MutableList<CategoryResponse>) {
        this.data = data
    }

    override fun toString(): String {
        return super.toString()
    }

}