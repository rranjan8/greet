package com.greet.special.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.greet.special.R
import android.widget.TextView
import com.greet.special.model.CategoryResponse


class MainCategoryAdapter(private var mainCategoryList: MutableList<CategoryResponse>, val mainCategoryClicked: MainCategoryClick) : RecyclerView.Adapter<MainCategoryAdapter.ViewHolder>() {

    companion object {
        var mClickListener: MainCategoryClick? = null
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.main_category_row, parent, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return mainCategoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        mClickListener = mainCategoryClicked
        holder?.name?.setText(mainCategoryList.get(position).getCategoryName())

        holder.name.setOnClickListener {
            mClickListener?.mainCategoryClicked(mainCategoryList.get(position))
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView

        init {
            name = view.findViewById<View>(R.id.main_row) as TextView
        }
    }

    interface MainCategoryClick {
        fun mainCategoryClicked(mainCategory: CategoryResponse)
    }


}