package com.greet.special.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.greet.special.R
import com.greet.special.adapter.FontSizeAdapter
import com.greet.special.adapter.TextAdapter
import com.greet.special.model.ContentListModel
import com.greet.special.util.Constant
import kotlinx.android.synthetic.main.font_layout.*
import kotlinx.android.synthetic.main.font_size.*
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.text_activity.*

class FontSizeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.font_size)


        fontSizeLayout.headerName.text = "FONT SIZE"

        fontSizeLayout.back.setOnClickListener {
            finish()
        }


        fontSizeRecyclerView.layoutManager = LinearLayoutManager(this)
        fontSizeRecyclerView.adapter = FontSizeAdapter(object : FontSizeAdapter.FontSizeInterface {
            override fun fontSize(size: Int) {
                var intent = Intent()
                intent.putExtra(Constant.FONT_SIZE, size)
                setResult(Constant.FONT_SIZE_CODE, intent)
                finish()
            }
        })
    }
}