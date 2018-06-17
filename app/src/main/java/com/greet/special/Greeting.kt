package com.greet.special

import android.app.Application
import com.greet.special.model.CategoryResponse

class Greeting : Application(){

   lateinit var mainCategoryList : MutableList<CategoryResponse>



    override fun onCreate() {
        super.onCreate()
    }
}