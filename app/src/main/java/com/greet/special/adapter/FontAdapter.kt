package com.greet.special.adapter

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.greet.special.R

class FontAdapter(var context : Context, var fontList : ArrayList<String>, var fontInterface: FontInterface) : RecyclerView.Adapter<FontAdapter.MyViewHolder>() {

    companion object {
        var fontInterfaces: FontInterface? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.font_row, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return fontList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        fontInterfaces = fontInterface
        holder?.font?.text = "Sample Font"

        val type = Typeface.createFromAsset(context.getAssets(), fontList.get(position))
        holder?.font?.typeface = type

        holder.font.setOnClickListener {
         fontInterface.selectedFont(fontList.get(position))
        }
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){

        var font: TextView

        init {
            font = view.findViewById<View>(R.id.font) as TextView
        }
    }

    interface FontInterface{
        fun selectedFont(font : String)
    }
}