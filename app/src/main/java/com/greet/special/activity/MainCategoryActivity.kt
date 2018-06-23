package com.greet.special.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.greet.special.Greeting
import com.greet.special.R
import com.greet.special.adapter.MainCategoryAdapter
import com.greet.special.model.CategoryResponse
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.main_category.*


class MainCategoryActivity : AppCompatActivity() {

    val Context.myApp: Greeting
        get() = applicationContext as Greeting

    val animals: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_category)
        header.back.visibility = View.GONE

        var mainCategoryList : MutableList<CategoryResponse> = myApp.mainCategoryList

        main_category_list.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        main_category_list.adapter = MainCategoryAdapter(mainCategoryList, object : MainCategoryAdapter.MainCategoryClick {
            override fun mainCategoryClicked(string: CategoryResponse) {
                Toast.makeText(this@MainCategoryActivity, string.getCategoryName(), Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this@MainCategoryActivity, SubCategoryActivity::class.java)
                intent.putExtra("Id",string.getCategoryId())
                startActivity(intent)
            }

        })
    }


}