package com.greet.special.network

import android.provider.ContactsContract
import com.greet.special.model.Data
import com.greet.special.model.DataSubCategory
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.Path

interface ApiInterface {
    @GET("api/Categories")
    fun getMainCategory(): Call<Data>

    @GET("api/SubCategoriesByCategory/{Id}")
    fun getSubCategory(@Path("Id") subcategoryId: Int): Call<DataSubCategory>

    @GET("api/imageAndContentBySubCatId/{Id}")
    fun getContentAndImage(@Path("Id") subcategoryId: Int): Call<DataSubCategory>
}