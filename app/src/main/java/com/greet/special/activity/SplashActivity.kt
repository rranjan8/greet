package com.greet.special.activity

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.greet.special.R
import com.greet.special.network.ApiCall
import com.greet.special.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.app.ProgressDialog
import com.greet.special.Greeting
import com.greet.special.model.CategoryResponse
import com.greet.special.model.Data
import android.content.pm.PackageManager
import android.Manifest.permission
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.support.v4.content.ContextCompat
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import com.greet.special.BuildConfig


class SplashActivity : AppCompatActivity() {
    lateinit var myApplication: Greeting
    val STORAGE_PERMISSION_CODE : Int = 23
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        myApplication = applicationContext as Greeting


        if(isReadStorageAllowed()){
            callApi()
        }else{
            requestStoragePermission()
        }
    }

    private fun moveToAnotherActivity() {
        intent = Intent(this@SplashActivity, MainCategoryActivity::class.java)
        startActivity(intent)
    }

    fun getMainCategory() {
        val progress = ProgressDialog(this)
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progress.show()
        var apiInterface: ApiInterface = ApiCall.create()
        var call: Call<Data> = apiInterface.getMainCategory()

        call.enqueue(object : Callback<Data> {
            override fun onFailure(call: Call<Data>?, t: Throwable?) {
                println(t.toString())
                progress.dismiss()
            }

            override fun onResponse(call: Call<Data>?, response: Response<Data>?) {
                println(response.toString())
                var data = response?.body()
                var dataList: MutableList<CategoryResponse> = data?.getData()!!
                myApplication.mainCategoryList = dataList
                for (ca in dataList) {
                    println("Id: " + ca.getCategoryId() + " name: " + ca.getCategoryName())
                }
                moveToAnotherActivity()
                progress.dismiss()
            }

        })
    }

    //We are calling this method to check the permission status
    private fun isReadStorageAllowed(): Boolean {
        //Getting the permission status
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false
    }

    //Requesting permission
    private fun requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == STORAGE_PERMISSION_CODE){

            //If permission is granted
            if(grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                callApi()
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }

    fun callApi(){
        var handler = Handler()
        handler.postDelayed({
            getMainCategory()
        }, 1)
    }
}

