package com.greet.special.activity

import android.app.ProgressDialog
import android.media.Image
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.greet.special.R
import com.greet.special.dialog.ImageDialog
import com.greet.special.model.ContentAndImageListModel
import com.greet.special.model.ContentListModel
import com.greet.special.model.DataImageContent
import com.greet.special.model.ImageListModel
import com.greet.special.network.ApiCall
import com.greet.special.network.ApiInterface
import com.greet.special.util.Constant
import kotlinx.android.synthetic.main.normal_message.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NormalMessageActivity : AppCompatActivity() {

    lateinit var contentList: MutableList<ContentListModel>
    lateinit var imageList: MutableList<ImageListModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.normal_message)

        var id = intent.extras.getInt("SUBCATEGORY_ID")

        leaderImage.setOnClickListener {
            val imageDialog: ImageDialog = ImageDialog.newInstance()
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            imageDialog.setList(imageList)
            imageDialog.show(fragmentTransaction, "ImageDialog")
        }


        getImageAndContent(id)



    }

    fun getImageAndContent(id: Int) {
        val progress = ProgressDialog(this)
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progress.show()

        var apiInterface: ApiInterface = ApiCall.create()
        var call: Call<DataImageContent> = apiInterface.getContentAndImage(id)

        call.enqueue(object : Callback<DataImageContent> {
            override fun onFailure(call: Call<DataImageContent>?, t: Throwable?) {
                progress.dismiss()
                println(t.toString())
            }

            override fun onResponse(call: Call<DataImageContent>?, response: Response<DataImageContent>?) {
                progress.dismiss()

                var dataImageContent: DataImageContent? = response!!.body()
                var contentAndImageList: ContentAndImageListModel = dataImageContent!!.data
                contentList = contentAndImageList.contentList
                imageList = contentAndImageList.imageList
                println("SIze: " + imageList.size)
            }
        })

    }
}