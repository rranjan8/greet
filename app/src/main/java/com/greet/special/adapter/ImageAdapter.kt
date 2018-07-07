package com.greet.special.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.greet.special.R
import com.greet.special.model.ImageListModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.message_image_row.*

class ImageAdapter(private var mainImageList: MutableList<ImageListModel>, val imageClick: ImageClick) : RecyclerView.Adapter<ImageAdapter.MyviewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.message_image_row, parent, false)
        return MyviewHolder(v)
    }

    override fun getItemCount(): Int {
        return mainImageList.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        var url: String = "http://glumar-001-site1.ftempurl.com/" + mainImageList.get(position).imageUrl
        println("IMage Url: "+url)
        Picasso.get().load(url).into(holder.image)


    }


    class MyviewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView

        init {
            image = view.findViewById<View>(R.id.imageMessage) as ImageView
        }

    }

    interface ImageClick {
        fun imageSelected(imageObject: ImageListModel)
    }
}