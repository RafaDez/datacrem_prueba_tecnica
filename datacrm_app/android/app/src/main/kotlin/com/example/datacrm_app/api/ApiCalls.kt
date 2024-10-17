package com.example.datacrm_app.api

import com.example.datacrm_app.models.ContactModel
import com.example.datacrm_app.models.LoginModel
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

class ApiCalls {
    interface ApiService {
        @GET("webservice.php")
        suspend fun getKey(
            @Query("operation") operation : String,
            @Query("username") username : String
        ): LoginModel.ApiResponseGetKey

        @FormUrlEncoded
        @POST("webservice.php")
        suspend fun login(
            @Field("operation") operation: String,
            @Field("username") username: String,
            @Field("accessKey") accessKey: String
        ): LoginModel.ApiResponseLogin

        @GET("webservice.php")
        suspend fun getContacts(
            @Query("operation") operation : String,
            @Query("sessionName") sessionName : String,
            @Query("query") query : String
        ): ContactModel.ApiResponseContact
    }


    object RequestManager {
        private const val base_url = "https://develop1.datacrm.la/jdimate/pruebatecnica/"

        val api: ApiService by lazy {
            Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}