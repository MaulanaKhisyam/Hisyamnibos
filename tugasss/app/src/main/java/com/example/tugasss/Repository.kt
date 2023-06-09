package com.example.tugasss

import com.example.tugasss.network.ApiService

class Repository (private  val  apiService: ApiService){
    fun getCharacters(page:String) = apiService.fetchCharacters(page)
}