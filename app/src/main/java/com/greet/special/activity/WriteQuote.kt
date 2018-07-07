package com.greet.special.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.greet.special.R
import com.greet.special.util.Constant
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.write_quote.*

class WriteQuote : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.write_quote)



        header_write.headerName.text="WRITE QUOTE"

        header_write.back.setOnClickListener {
            finish()
        }

        done.setOnClickListener {
           var quoteText : String  =  quoteId.text.toString()
           var intent: Intent = Intent()
            intent.putExtra(Constant.WRITE_QUOTE,quoteText)
            setResult(Constant.WRITE_CODE,intent)
            finish()
        }
    }
}