package com.example.lesson_1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson_1.model.Album
import com.example.lesson_1.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class AlbumsViewModel(private val repository: Repository): ViewModel() {

    val albumResponse: MutableLiveData<Response<List<Album>>> = MutableLiveData()

    fun getAlbums() {
        viewModelScope.launch {
            val actualResponse = repository.getAlbums()
            albumResponse.value = actualResponse
        }
    }
}