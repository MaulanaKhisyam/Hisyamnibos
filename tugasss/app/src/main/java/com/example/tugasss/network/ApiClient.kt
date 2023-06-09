package com.example.tugasss.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
object ApiClient {
    // mendefinisikan base url yang berisi URL dari server yang diakses.
    private val BASE_URL = "https://rickandmortyapi.com/api/"
    // untuk mengkonversi JSON ke objek kotlin
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    // membuat objek
    private val retrofit: Retrofit by lazy {
        // untuk konfigurasi
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    // code untuk membuat panggilan ke API
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
// deklarasi antarmuka yang dapat membuat inisiasi dan
// menggunakannya untuk melakukan permintaan ke HTTP dengan query paramater page
interface ApiService{
    @GET("character")
    fun fetchCharacters(@Query("page") page: String): Call<CharacterResponse>
}
