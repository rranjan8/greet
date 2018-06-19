package com.greet.special.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.greet.special.Greeting
import com.greet.special.R
import com.greet.special.adapter.SubCategoryAdapter
import com.greet.special.model.CategoryResponse
import com.greet.special.model.Data
import com.greet.special.model.DataSubCategory
import com.greet.special.model.SubCategoryResponse
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

        header.headerName.setText("Sub Category")

        var id:Int = intent.getIntExtra("Id",0)
        sub_category_list.layoutManager = LinearLayoutManager(this)
        getMainCategory(id)
    }

    fun getMainCategory(id:Int) {
        val progress = ProgressDialog(this)
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progress.show()
        var apiInterface: ApiInterface = ApiCall.create()
        var call: Call<DataSubCategory> = apiInterface.getSubCategory(id)

        call.enqueue(object : Callback<DataSubCategory> {
            override fun onFailure(call: Call<DataSubCategory>?, t: Throwable?) {
                println(t.toString())
                progress.dismiss()
            }

            override fun onResponse(call: Call<DataSubCategory>?, response: Response<DataSubCategory>?) {
                println(response.toString())
                var dataSubCategory = response?.body()
                var dataList: MutableList<SubCategoryResponse> = dataSubCategory?.data!!

                sub_category_list.adapter = SubCategoryAdapter(dataList,object:SubCategoryAdapter.SubCategoryClick{
                    override fun subCategoryClicked(subCategory: SubCategoryResponse) {
                        Toast.makeText(this@SubCategoryActivity,subCategory.SubCategoryName,Toast.LENGTH_SHORT).show()
                    }
                })
                progress.dismiss()
            }

        })
    }
}