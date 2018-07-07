package com.greet.special

import android.app.Application
import android.graphics.Bitmap
import com.greet.special.model.CategoryResponse

class Greeting : Application(){

   lateinit var mainCategoryList : MutableList<CategoryResponse>

    lateinit var bitmap: Bitmap

    override fun onCreate() {
        super.onCreate()
    }
}