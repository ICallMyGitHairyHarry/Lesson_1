package com.example.lesson_1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lesson_1.repository.Repository

class CreatPostViewModelFactory (
    private val repository: Repository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreatPostViewModel(repository) as T
    }
}