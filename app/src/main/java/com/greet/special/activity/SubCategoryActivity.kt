package com.greet.special.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.greet.special.Greeting
import com.greet.special.R
import com.greet.special.model.CategoryResponse
import com.greet.special.model.Data
import com.greet.special.network.ApiCall
import com.greet.special.network.ApiInterface
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.sub_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubCategoryActivity : AppCompatActivity() {

    lateinit var myApplication: Greeting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sub_category)
        header.back.visibility = View.GONE

        sub_category_list.layoutManager = LinearLayoutManager(this)
    }

    fun getMainCategory() {
        val progress = ProgressDialog(this)
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progress.show()
        var apiInterface: ApiInterface = ApiCall.create()
        var call: Call<Data> = apiInterface.getMainCategory()

        call.enqueue(object : Callback<Data> {
            override fun onFailure(call: Call<Data>?, t: Throwable?) {
                println(t.toString())
                progress.dismiss()
            }

            override fun onResponse(call: Call<Data>?, response: Response<Data>?) {
                println(response.toString())
                var data = response?.body()
                var dataList: MutableList<CategoryResponse> = data?.getData()!!
                myApplication.mainCategoryList = dataList
                for (ca in dataList) {
                    println("Id: " + ca.getCategoryId() + " name: " + ca.getCategoryName())
                }
                progress.dismiss()
            }

        })
    }
}