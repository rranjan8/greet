package com.greet.special

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.main_category.*


class MainCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_category)
        back.visibility = View.GONE
    }
}