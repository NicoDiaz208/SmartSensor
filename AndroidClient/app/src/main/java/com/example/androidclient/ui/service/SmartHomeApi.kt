package at.htl.countries.service

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.androidclient.R
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SmartHomeApi {
    val retrofitService: SmartHomeApiService
        by lazy{ retrofit.create(SmartHomeApiService::class.java)}
    // private val BASE_URL = "http://90.146.160.166:9020"
    private val BASE_URL = "http://192.168.41.177:9020/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    fun ImageView.loadImage(uri: String?){
        val options = RequestOptions()
            .error(R.mipmap.ic_launcher_round)
        Glide.with(this.context)
            .setDefaultRequestOptions(options)
            .load(uri)
            .into(this)
    }
}