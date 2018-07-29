package com.greet.special.activity

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.widget.Toast
import com.greet.special.R
import com.greet.special.dialog.ImageDialog
import com.greet.special.dialog.MenuDialog
import com.greet.special.model.ContentAndImageListModel
import com.greet.special.model.ContentListModel
import com.greet.special.model.DataImageContent
import com.greet.special.model.ImageListModel
import com.greet.special.network.ApiCall
import com.greet.special.network.ApiInterface
import com.greet.special.util.Constant
import com.greet.special.viewModel.NormalViewModel
import kotlinx.android.synthetic.main.normal_message.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import yuku.ambilwarna.AmbilWarnaDialog
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Environment
import android.support.v4.content.FileProvider
import com.greet.special.BuildConfig
import com.greet.special.Greeting
import java.io.*
import java.io.File.separator
import java.nio.file.Files.isDirectory




class NormalMessageActivity : AppCompatActivity() {

    val Context.myApp: Greeting
        get() = applicationContext as Greeting

    lateinit var contentList: MutableList<ContentListModel>
    lateinit var imageList: MutableList<ImageListModel>
    lateinit var menuList: MutableList<String>
    lateinit var normalViewModel: NormalViewModel
    lateinit var shareAblefile:File


    lateinit var setectedMenu: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.normal_message)

        normalViewModel = ViewModelProviders.of(this).get(NormalViewModel::class.java)

        var id = intent.extras.getInt("SUBCATEGORY_ID")



        normalViewModel.menuNameSelected.observe(this, Observer<String> { s ->
            println("Observer called")
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
            if (s != null) {
                menuSelected(s)
            }

        })


        addMenu()

        leaderImage.setOnClickListener {
            // chooseColor()
            var imageDialog: ImageDialog = ImageDialog.newInstance()
            var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            imageDialog.setList(imageList)
            imageDialog.show(fragmentTransaction, "ImageDialog")
        }

        quotationText.setOnClickListener {
            var menuDialog: MenuDialog = MenuDialog.newInstance()
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            menuDialog.setList(menuList)
            menuDialog.show(fragmentTransaction, "MenuDialog")

        }
        getImageAndContent(id)

        preview.setOnClickListener {
            //changeViewToBitmap()
            callPreviewActivity()
        }

        share.setOnClickListener {
            shareImageViaIntent()
        }
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
                println("SIze: " + contentList.size)
            }
        })

    }

    fun addMenu() {
        menuList = mutableListOf()
        menuList.add(Constant.CHANGE_QUOTE)
        menuList.add(Constant.WRITE_YOUR_QUOTE)
        menuList.add(Constant.CHANGE_FONT)
        menuList.add(Constant.CHANGE_FONT_COLOR)
        menuList.add(Constant.CHANGE_FONT_SIZE)
        // menuList.add(Constant.CHANGE_IMAGE)
        menuList.add(Constant.CHANGE_BACKGROUND_COLOR)
    }


    fun chooseColor() {

        val dialogs: AmbilWarnaDialog = AmbilWarnaDialog(this, R.color.white, object : AmbilWarnaDialog.OnAmbilWarnaListener {
            override fun onCancel(dialog: AmbilWarnaDialog?) {
                Toast.makeText(this@NormalMessageActivity, "Color not choosen", Toast.LENGTH_SHORT).show()

            }

            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                Toast.makeText(this@NormalMessageActivity, "Color not choosen $color", Toast.LENGTH_SHORT).show()
                changeColor(color)
            }
        })


        dialogs.show()
    }

    private fun menuSelected(menuName: String) {
        setectedMenu = menuName
        if (menuName.equals(Constant.CHANGE_QUOTE)) {
            var content: ArrayList<ContentListModel> = ArrayList(contentList)
            var intent = Intent(this@NormalMessageActivity, TextActivity::class.java)
            intent.putParcelableArrayListExtra(Constant.PASS_QUOTE_CONTENT, content)
            startActivityForResult(intent, Constant.SELECTED_TEXT_CODE)
        } else if (menuName.equals(Constant.CHANGE_FONT)) {
            var intent = Intent(this@NormalMessageActivity, FontActivity::class.java)
            startActivityForResult(intent, Constant.SELECTED_FONT_CODE)
        } else if (menuName.equals(Constant.WRITE_YOUR_QUOTE)) {
            var intent = Intent(this@NormalMessageActivity, WriteQuote::class.java)
            startActivityForResult(intent, Constant.WRITE_CODE)
        } else if (menuName.equals(Constant.CHANGE_FONT_COLOR)) {
            chooseColor()
        } else if (menuName.equals(Constant.CHANGE_BACKGROUND_COLOR)) {
            chooseColor()
        } else if (menuName.equals(Constant.CHANGE_FONT_SIZE)) {
            var intent = Intent(this@NormalMessageActivity, FontSizeActivity::class.java)
            startActivityForResult(intent, Constant.FONT_SIZE_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (requestCode == Constant.SELECTED_TEXT_CODE) {
                var quote: String = data!!.getStringExtra(Constant.SELECTED_TEXT)
                quotationText.text = Html.fromHtml(quote)
            } else if (requestCode == Constant.SELECTED_FONT_CODE) {
                var font: String = data!!.getStringExtra(Constant.SELECTED_FONT)
                val type = Typeface.createFromAsset(getAssets(), font)
                quotationText.typeface = type
            } else if (requestCode == Constant.WRITE_CODE) {
                var quote: String = data!!.getStringExtra(Constant.WRITE_QUOTE)
                quotationText.text = Html.fromHtml(quote)
            } else if (requestCode == Constant.FONT_SIZE_CODE) {
                var fontSize: Int = data.getIntExtra(Constant.FONT_SIZE, 15)
                quotationText.textSize = fontSize.toFloat()
            }
        }
    }


    fun changeColor(colorCode: Int) {
        if (setectedMenu.equals(Constant.CHANGE_BACKGROUND_COLOR)) {
            card_view.setCardBackgroundColor(colorCode)
        } else {
            quotationText.setTextColor(colorCode)
        }
    }

    fun changeViewToBitmap() {
        /* card_view.setDrawingCacheEnabled(true)
         card_view.buildDrawingCache()*/

        var bitmap = Bitmap.createBitmap(card_view.getWidth(), card_view.getHeight(), Bitmap.Config.ARGB_8888)
        var canvas: Canvas = Canvas(bitmap)
        var drawable = card_view.background
        if (drawable != null) {
            drawable.draw(canvas)
        }

        card_view.draw(canvas)
        // var bm = card_view.drawingCache
        println("Change view to image called")
        storeBitmap(bitmap)
    }

    fun storeBitmap(bm: Bitmap) {
       /* var iconsStoragePath: String = Environment.getExternalStorageDirectory().toString() + "/myAppDir/myImages/"
        var file = File(iconsStoragePath)
*/

        val sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val imageFolder = File(sd.absolutePath + File.separator +
                "FolderName" + File.separator + "myImages")

        if (!imageFolder.isDirectory) {
            imageFolder.mkdirs()
        }

        val mediaFile = File(imageFolder.toString() + File.separator + "temp.jpg")

        shareAblefile = mediaFile
        println(mediaFile)
       /* file.mkdirs()
        var file2 = File(file, "temp.jpg")
        shareAblefile = file
        if (file2.exists()) {
            file2.delete()
        }*/

        try {
            var fileOutputStream = FileOutputStream(mediaFile)
            bm.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()

        } catch (e: Exception) {
            println(e.toString())
        }


    }

    fun callPreviewActivity() {
        changeViewToBitmap()

        var intent = Intent(this@NormalMessageActivity, PreviewActivity::class.java)

        startActivity(intent)
    }

    fun shareImageViaIntent() {
        changeViewToBitmap()
        var intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/jpeg"
        intent.putExtra(Intent.EXTRA_STREAM, getURI())
        startActivity(Intent.createChooser(intent, "SHARE"))
    }

    fun getURI() : Uri{
        return FileProvider.getUriForFile(this,
                BuildConfig.APPLICATION_ID,
                shareAblefile)
    }

}