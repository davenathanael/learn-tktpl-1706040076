package id.ac.ui.cs.davenathanael.newspaper

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RequestbinService {
    @Headers("Content-Type:application/json")
    @POST("sample/post/request/")
    suspend fun postWifis(@Body request: Request): Request

    companion object {
        const val BASE_URL = "https://eneg5adxudk3.x.pipedream.net/"

        fun create(): RequestbinService {
            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RequestbinService::class.java)
        }
    }
}