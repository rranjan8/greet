package com.greet.special.model

import com.google.gson.annotations.SerializedName

data class DataSubCategory(@SerializedName("data") val data: MutableList<SubCategoryResponse>)