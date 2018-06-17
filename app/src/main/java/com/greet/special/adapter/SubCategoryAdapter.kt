package com.greet.special.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.greet.special.R
import com.greet.special.model.CategoryResponse

class SubCategoryAdapter(private var mainCategoryList: MutableList<CategoryResponse>, val subCategoryClicked:SubCategoryClick) : RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder>() {

    companion object {
        var mClickListener: SubCategoryClick? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.main_category_row, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mainCategoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        mClickListener = subCategoryClicked
        holder?.name?.setText(mainCategoryList.get(position).getCategoryName())
        holder.name.setOnClickListener {
            MainCategoryAdapter.mClickListener?.mainCategoryClicked(mainCategoryList.get(position))
        }
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var name: TextView
        init {
            name = view.findViewById<View>(R.id.main_row) as TextView
        }
    }

    interface SubCategoryClick {
        fun subCategoryClicked(mainCategory: CategoryResponse)
    }
}