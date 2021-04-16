package com.example.lesson_1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.reflect.Array
import java.util.ArrayList
import kotlin.math.log
import kotlin.random.Random

class MainMenuActivityViewModel : ViewModel() {

    private val _reserved_messages = MutableLiveData<List<String>>()
    private val _messages = MutableLiveData<MutableList<Message>>()

    init {
        _reserved_messages.value = arrayListOf("Даже не знаю, что ответить...", "Я отвечу позже", "Подожди, я думаю", "Наверное, я плохой собеседник", "Прости, я не знаю", "Привет!!!", "Шутки шутками, но в шутке доля шутки, как грится", "Шутка? Нет, не шутка")
        _messages.value = ArrayList()
    }

    fun messages(): LiveData<MutableList<Message>> {
        return _messages
    }

    fun updateMessages (Mess: Message) {
        _messages.value?.add(Mess)
        _messages.value = _messages.value
    }

    fun addMessages () {
        _reserved_messages.value?.get(Random.nextInt(0, _reserved_messages.value!!.size))?.let { Message(2, it) }?.let { _messages.value?.add(it) }
        _reserved_messages.value?.get(Random.nextInt(0, _reserved_messages.value!!.size))?.let { Message(2, it) }?.let { _messages.value?.add(it) }
        _messages.value = _messages.value
    }


}