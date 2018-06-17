package com.greet.special.network

import android.provider.SettingsSlicesContract
import com.greet.special.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level

class ApiCall {
    private lateinit var apiInterface: ApiInterface

    /**
     * Companion object to create the GithubApiService
     */
    companion object Factory {
        fun create(): ApiInterface {
            val BASE_URL: String = "http://glumar-001-site1.ftempurl.com/"
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }

}