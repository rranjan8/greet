package com.greet.special.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.greet.special.Greeting
import com.greet.special.R
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.preview.*


class PreviewActivity : AppCompatActivity() {

    val Context.myApp: Greeting
        get() = applicationContext as Greeting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preview)

        preview_header.headerName.text = "PREVIEW"

        preview_header.back.setOnClickListener {
            finish()
        }

        previewImage.setImageBitmap(myApp.bitmap)
    }
}