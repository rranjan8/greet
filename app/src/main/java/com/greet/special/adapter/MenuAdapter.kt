package com.greet.special.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.greet.special.R

class MenuAdapter(var menuList: MutableList<String>, var menuSelected: MenuSelected) : RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {

    companion object {
        var menuSelected: MenuSelected? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.menu_row, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        menuSelected = menuSelected
        holder.menuName.text = menuList.get(position)
        holder.menuName.setOnClickListener {
            menuSelected.selectedMenu(menuList.get(position))
        }
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var menuName: TextView

        init {
            menuName = view.findViewById<View>(R.id.rowName) as TextView
        }
    }

    interface MenuSelected {
        fun selectedMenu(selected: String)
    }
}