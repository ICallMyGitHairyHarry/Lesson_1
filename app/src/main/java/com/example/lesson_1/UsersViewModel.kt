package com.example.lesson_1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson_1.model.User
import com.example.lesson_1.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class UsersViewModel (private val repository: Repository): ViewModel() {

    val userResponse: MutableLiveData<Response<List<User>>> = MutableLiveData()

    fun getUsers() {
        viewModelScope.launch {
            val actualResponse = repository.getUsers()
            userResponse.value = actualResponse
        }
    }
}