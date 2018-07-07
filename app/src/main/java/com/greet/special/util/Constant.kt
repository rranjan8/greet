package com.greet.special.util

import android.graphics.Bitmap

class Constant {

    companion object {
        val CHANGE_IMAGE: String = "CHANGE IMAGE"
        val CHANGE_BACKGROUND_COLOR: String = "CHANGE BACKGROUND COLOR"
        val CHANGE_FONT: String = "CHANGE FONT"
        val CHANGE_FONT_COLOR: String = "CHANGE FONT COLOR"
        val CHANGE_FONT_SIZE: String = "CHANGE FONT SIZE"
        val CHANGE_QUOTE: String = "CHANGE QUOTE"
        val WRITE_YOUR_QUOTE: String = "WRITE YOUR QUOTE"


        val SELECTED_TEXT_CODE: Int = 200
        val SELECTED_FONT_CODE: Int = 201
        val WRITE_CODE: Int = 202
        val FONT_SIZE_CODE: Int = 203
        val PASS_QUOTE_CONTENT: String = "quotationList"
        val SELECTED_TEXT: String = "selectedText"
        val SELECTED_FONT: String = "selectedFont"
        val WRITE_QUOTE: String = "writeQuote"
        val FONT_SIZE: String = "fontSize"

        var PREVIEW_BITMAP : Bitmap? = null
    }

}