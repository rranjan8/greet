package com.greet.special.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.greet.special.R
import com.greet.special.adapter.ImageAdapter
import com.greet.special.model.ImageListModel
import kotlinx.android.synthetic.main.dialog_header.view.*
import kotlinx.android.synthetic.main.image_dialog.*

class ImageDialog : DialogFragment() {

    var rootView: View? = null




    lateinit var imageList: MutableList<ImageListModel>

    companion object {

        fun newInstance(): ImageDialog {
            val fragmentDialog: ImageDialog = ImageDialog()
            return fragmentDialog
        }

    }

    fun setList(list: MutableList<ImageListModel>){
        this.imageList = list
    }



    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.image_dialog, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView(view)
    }

    fun setView(view: View){
        headerDialog.dialog_header.setText("SELECT IMAGE")
        image_list.layoutManager = LinearLayoutManager(activity)
        image_list.adapter = ImageAdapter(imageList,object:ImageAdapter.ImageClick{
            override fun imageSelected(imageObject: ImageListModel) {
                Toast.makeText(activity,"Image clicked",Toast.LENGTH_SHORT).show()
            }
        })

        headerDialog.cancel.setOnClickListener {
            dismiss()
        }
    }
}