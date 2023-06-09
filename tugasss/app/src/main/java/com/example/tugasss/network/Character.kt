package com.example.tugasss.network

import com.squareup.moshi.Json

//membuat data class character dengan properti 'name' dan 'image'
data class Character (
    //@Json digunakan untuk memberi tahu library Moshi
    @Json(name="name")
    val name: String,
    @Json(name="image")
    val image: String

)
//method yang digunakan untuk merepresentasikan respon API yang mengandung daftar karakter
data class CharacterResponse(@Json(name="results")
//properti result digunakan menyimpan daftar karakter yang diterima dari respon API
    val result : List<Character>) {
}



