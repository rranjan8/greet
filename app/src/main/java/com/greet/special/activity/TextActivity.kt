package com.greet.special.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.greet.special.R
import com.greet.special.adapter.TextAdapter
import com.greet.special.model.ContentListModel
import com.greet.special.util.Constant
import kotlinx.android.synthetic.main.font_layout.*
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.text_activity.*


class TextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_activity)


        topLayout.headerName.text = "QUOTATION"

        topLayout.back.setOnClickListener {
            finish()
        }

        var content: ArrayList<ContentListModel> = intent.getParcelableArrayListExtra(Constant.PASS_QUOTE_CONTENT)
        println("SIze $content.size")

        contentRecyclerView.layoutManager = LinearLayoutManager(this)
        contentRecyclerView.adapter = TextAdapter(content, object : TextAdapter.QuotationInterface {
            override fun getQuotation(quotation: String) {
                println(quotation)
                var intent : Intent = Intent()
                intent.putExtra(Constant.SELECTED_TEXT,quotation)
                setResult(Constant.SELECTED_TEXT_CODE,intent)
                finish()
            }
        })
    }
}