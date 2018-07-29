package com.greet.special.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import com.greet.special.BuildConfig
import com.greet.special.Greeting
import com.greet.special.R
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.preview.*
import java.io.File
import java.io.FileInputStream


class PreviewActivity : AppCompatActivity() {

   /* val Context.myApp: Greeting
        get() = applicationContext as Greeting
*/

    lateinit var shareAblefile:File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preview)

        preview_header.headerName.text = "PREVIEW"

        preview_header.back.setOnClickListener {
            finish()
        }

      //  previewImage.setImageBitmap(myApp.bitmap)
       getImageFromSD()
        share.setOnClickListener {
            shareImageViaIntent()
        }
    }

    fun getImageFromSD() {
       /* var iconsStoragePath: String = Environment.getExternalStorageDirectory().toString() + "/myAppDir/myImages/"
        var file = File(iconsStoragePath)
*/
        val sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val imageFolder = File(sd.absolutePath + File.separator +
                "FolderName" + File.separator + "myImages")
        val mediaFile = File(imageFolder.toString() + File.separator + "temp.jpg")
        shareAblefile = mediaFile
      //  var file2 = File(file, "temp.jpg")
        try {
            var bitmap: Bitmap = BitmapFactory.decodeStream(FileInputStream(mediaFile))
            previewImage.setImageBitmap(bitmap)
        } catch (e: Exception) {
            println(e.printStackTrace())
        }
    }

    fun shareImageViaIntent() {
      //  changeViewToBitmap()
        var intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/jpeg"
        intent.putExtra(Intent.EXTRA_STREAM, getURI())
        startActivity(Intent.createChooser(intent, "SHARE"))
    }

    fun getURI() : Uri {
        return FileProvider.getUriForFile(this,
                BuildConfig.APPLICATION_ID,
                shareAblefile)
    }
}