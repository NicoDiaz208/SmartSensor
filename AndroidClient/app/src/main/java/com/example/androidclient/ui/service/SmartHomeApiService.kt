package at.htl.countries.service

import at.htl.countries.model.Data
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SmartHomeApiService {
    @GET("getAll")
    fun getAllData():
            Deferred<List<Data>>
    @GET("getTopic")
    fun getByTopic(@Query("topic") topic: String, @Query("last") last: String = "100"):
            Deferred<List<Data>>
}