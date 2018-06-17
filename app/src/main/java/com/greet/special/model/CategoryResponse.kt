package com.greet.special.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


open class CategoryResponse {

    @SerializedName("CategoryId")
    @Expose
    private var categoryId: Int? = null
    @SerializedName("CategoryName")
    @Expose
    private var categoryName: String? = null

    fun getCategoryId(): Int? {
        return categoryId
    }

    fun setCategoryId(categoryId: Int?) {
        this.categoryId = categoryId
    }

    fun getCategoryName(): String? {
        return categoryName
    }

    fun setCategoryName(categoryName: String) {
        this.categoryName = categoryName
    }
}