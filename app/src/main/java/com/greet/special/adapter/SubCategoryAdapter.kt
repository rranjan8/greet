package com.greet.special.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.greet.special.R
import com.greet.special.model.CategoryResponse
import com.greet.special.model.SubCategoryResponse

class SubCategoryAdapter(private var subCategoryList: MutableList<SubCategoryResponse>, val subCategoryClicked: SubCategoryClick) : RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder>() {

    companion object {
        var mClickListener: SubCategoryClick? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.main_category_row, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return subCategoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        mClickListener = subCategoryClicked
        holder?.name?.setText(subCategoryList.get(position).SubCategoryName)
        holder.name.setOnClickListener {
            mClickListener?.subCategoryClicked(subCategoryList.get(position))
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView

        init {
            name = view.findViewById<View>(R.id.main_row) as TextView
        }
    }

    interface SubCategoryClick {
        fun subCategoryClicked(mainCategory: SubCategoryResponse)
    }
}