package com.greet.special.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.greet.special.R
import com.greet.special.adapter.FontAdapter
import com.greet.special.util.Constant
import kotlinx.android.synthetic.main.font_layout.*
import kotlinx.android.synthetic.main.header.view.*

class FontActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.font_layout)

        font_header.headerName.text = "FONT STYLE"

        font_header.back.setOnClickListener {
            finish()
        }

        var fontLIst: ArrayList<String> = ArrayList()
        fontLIst.add("font/Beautiful People Personal Use.ttf")
        fontLIst.add("font/Beautiful People two Personal Use.ttf")
        fontLIst.add("font/Beyond Wonderland.ttf")
        fontLIst.add("font/Birds of Paradise c PERSONAL USE ONLY.ttf")
        fontLIst.add("font/ExtraOrnamentalNo2.ttf")
        fontLIst.add("font/FTHELSIN.TTF")
        fontLIst.add("font/Great Day Personal Use.ttf")
        fontLIst.add("font/Lemon Jelly Personal Use.ttf")
        fontLIst.add("font/Lovely Home.ttf")
        fontLIst.add("font/New Walt Disney.ttf")
        fontLIst.add("font/New Walt Disney UI.ttf")
        fontLIst.add("font/Painter_PERSONAL_USE_ONLY.ttf")
        fontLIst.add("font/QaskinBlack_PersonalUse.ttf")
        fontLIst.add("font/QaskinWhite_PersonalUse.ttf")
        fontLIst.add("font/Respective.ttf")
        fontLIst.add("font/Respective_Slanted.ttf")
        fontLIst.add("font/Respective_Swashes.ttf")
        fontLIst.add("font/Respective_Swashes_Slanted.ttf")
        fontLIst.add("font/Scream Real.otf")
        fontLIst.add("font/Scream Real.ttf")
        fontLIst.add("font/ShadedLarch_PERSONAL_USE.ttf")
        fontLIst.add("font/Stylish Calligraphy Demo.ttf")

        font_recycler_view.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        font_recycler_view.adapter = FontAdapter(this, fontLIst, object : FontAdapter.FontInterface {
            override fun selectedFont(font: String) {
                println("Font Selected: $font")

                var intent: Intent = Intent()
                intent.putExtra(Constant.SELECTED_FONT, font)
                setResult(Constant.SELECTED_FONT_CODE,intent)
                finish()
            }
        })


    }
}