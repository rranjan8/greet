package com.greet.special.model

import com.google.gson.annotations.SerializedName

class SubCategoryResponse(@SerializedName("SubCategoryId") val SubCategoryId: Int, @SerializedName("SubCategoryName") val SubCategoryName: String)