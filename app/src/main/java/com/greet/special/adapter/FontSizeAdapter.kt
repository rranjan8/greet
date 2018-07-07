package com.greet.special.adapter

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.greet.special.R
import org.w3c.dom.Text
import java.util.ArrayList

class FontSizeAdapter(var fontSizeInterface: FontSizeInterface) : RecyclerView.Adapter<FontSizeAdapter.MyViewHolder>() {

    var fontSizeList = ArrayList<Int>()

    init {
        for (i in 8..30) {
            fontSizeList.add(i)
        }
    }

    companion object {
        var fontSizeInterfac: FontSizeInterface? = null
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.font_size_row, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
       return fontSizeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        fontSizeInterfac = fontSizeInterface
        holder?.fontSize?.text = ""+fontSizeList[position]

        holder.fontSize.setOnClickListener {
            fontSizeInterfac!!.fontSize(fontSizeList.get(position))
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var fontSize: TextView

        init {
            fontSize = view.findViewById(R.id.fontSize) as TextView
        }
    }

    interface FontSizeInterface {
        fun fontSize(size: Int)
    }

}