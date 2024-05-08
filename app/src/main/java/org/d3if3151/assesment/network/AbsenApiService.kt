package org.d3if3151.assesment.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "indraazimi/mobpro1-compose/static-api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
interface HewanApiService {
    @GET("static-api.json")
    suspend fun getHewan(): String
}

object HewanAPI {
    val service: HewanApiService by lazy {
        retrofit.create(HewanApiService::class.java)
    }
}