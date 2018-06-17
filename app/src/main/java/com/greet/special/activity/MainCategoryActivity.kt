package com.greet.special.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.greet.special.R
import com.greet.special.adapter.MainCategoryAdapter
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.main_category.*


class MainCategoryActivity : AppCompatActivity() {

    val animals: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_category)
        header.back.visibility = View.GONE
        addAnimals()
        // Creates a vertical Layout Manager
        main_category_list.layoutManager = LinearLayoutManager(this)
        main_category_list.adapter = MainCategoryAdapter(animals, object : MainCategoryAdapter.MainCategoryClick {
            override fun mainCategoryClicked(string: String) {
                Toast.makeText(this@MainCategoryActivity, string, Toast.LENGTH_SHORT).show()
            }

        })
    }

    // Adds animals to the empty animals ArrayList
    fun addAnimals() {
        animals.add("dog")
        animals.add("cat")
        animals.add("owl")
        animals.add("cheetah")
        animals.add("raccoon")
        animals.add("bird")
        animals.add("snake")
        animals.add("lizard")
        animals.add("hamster")
        animals.add("bear")
        animals.add("lion")
        animals.add("tiger")
        animals.add("horse")
        animals.add("frog")
        animals.add("fish")
        animals.add("shark")
        animals.add("turtle")
        animals.add("elephant")
        animals.add("cow")
        animals.add("beaver")
        animals.add("bison")
        animals.add("porcupine")
        animals.add("rat")
        animals.add("mouse")
        animals.add("goose")
        animals.add("deer")
        animals.add("fox")
        animals.add("moose")
        animals.add("buffalo")
        animals.add("monkey")
        animals.add("penguin")
        animals.add("parrot")
    }
}