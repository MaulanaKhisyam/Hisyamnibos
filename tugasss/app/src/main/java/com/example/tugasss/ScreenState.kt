package com.example.tugasss

sealed class ScreenState <T> (val data: T? = null,val massage: String? = null) {
    class Success<T>(data: T) : ScreenState<T>(data)

    class Loading<T>(data: T? = null) : ScreenState<T>(data)

    class Error<T>(massage: String?, data: T? = null) : ScreenState<T>(data, massage)
}