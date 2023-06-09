package com.example.android.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.MarsApi
import com.example.android.marsphotos.network.MarsPhoto
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }

/**
 * class untuk viewmodel melampirkan ke overviewfragment
 */
class OverviewViewModel : ViewModel() {

    // code untuk menyimpan status permintaan baru
    private val _status = MutableLiveData<MarsApiStatus>()

    // code untuk status yang tidak dapat dirubah dari permintaan
    val status: LiveData<MarsApiStatus> = _status

    // code untuk memperbaharui daftar marsPhoto dengan nilai baru
    private val _photos = MutableLiveData<List<MarsPhoto>>()

    // code untuk antarmuka livedata eksternal ke properti tidak dapat dirubah,
    // jadi hanya kelas yang dapat dimodifikasi
    val photos: LiveData<List<MarsPhoto>> = _photos

    /**
     * Memanggil  getMarsPhotos agar dapat menampilkan status
     */
    init {
        getMarsPhotos()
    }

    /**
     * Mendapatkan informasi foto mars dari layanan API retrofit dan diperbaharui
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getMarsPhotos() {

        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}
