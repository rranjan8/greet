package com.greet.special.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.greet.special.R
import com.greet.special.model.ContentListModel

class TextAdapter(var list: ArrayList<ContentListModel>, var quotationInterface: QuotationInterface) : RecyclerView.Adapter<TextAdapter.MyViewHolder>() {

    companion object {
        var quotationInterface: QuotationInterface? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.text_adapter_row, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        quotationInterface = quotationInterface

        holder.textView.text = Html.fromHtml(list.get(position).contentValue)
        holder.textView.setOnClickListener {
            quotationInterface.getQuotation(list.get(position).contentValue)
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var textView: TextView
        init {
            textView = view.findViewById<View>(R.id.quotationText) as TextView
        }
    }

    interface QuotationInterface {
        fun getQuotation(quotation: String)
    }
}