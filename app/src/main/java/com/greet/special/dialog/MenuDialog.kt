package com.greet.special.dialog

import android.app.Activity
import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.greet.special.R
import com.greet.special.adapter.MenuAdapter
import com.greet.special.viewModel.NormalViewModel
import kotlinx.android.synthetic.main.dialog_header.view.*
import kotlinx.android.synthetic.main.menu_dialog.*

class MenuDialog : DialogFragment() {

    var rootView: View? = null
    lateinit var menuList: MutableList<String>
    var normalViewModel: NormalViewModel? = null


    companion object {

        fun newInstance(): MenuDialog {
            val fragmentDialog: MenuDialog = MenuDialog()
            return fragmentDialog
        }

    }


    fun setList(list: MutableList<String>) {
        this.menuList = list
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        println("ON CREATE DIALOG CALLED")
        if (activity != null)
            normalViewModel = ViewModelProviders.of(activity!!).get(NormalViewModel::class.java)
        return super.onCreateDialog(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.menu_dialog, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView(view)
    }

    fun setView(view: View) {
        dialogHeader.dialog_header.setText("MENU")
        menu_list.layoutManager = LinearLayoutManager(activity)
        menu_list.adapter = MenuAdapter(menuList, object : MenuAdapter.MenuSelected {
            override fun selectedMenu(selected: String) {
                //Toast.makeText(activity, selected, Toast.LENGTH_SHORT).show()
                println("Selected: $selected")
                normalViewModel?.menuNameSelected?.value = selected
                dialog.dismiss()
            }

        })

        dialogHeader.cancel.setOnClickListener {
            dismiss()
        }
    }
}