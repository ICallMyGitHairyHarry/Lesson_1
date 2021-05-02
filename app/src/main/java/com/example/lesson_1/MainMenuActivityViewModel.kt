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

    private val _reserved_messages = arrayListOf("Даже не знаю, что ответить...", "Я отвечу позже", "Подожди, я думаю", "Наверное, я плохой собеседник", "Прости, я е знаю", "Привет!!!", "Шутки шутками, но в шутке доля шутки, как грится", "Шутка? Нет, не шутка")
    private val _messages = MutableLiveData<MutableList<Message>>()

    init {
        _messages.value = ArrayList()
    }

    fun messages(): LiveData<MutableList<Message>> {
        return _messages
    }

    fun updateMessages(Mess: Message) {
        _messages.value?.add(Mess)
        _messages.value = _messages.value
    }

    fun addMessages() {
        android.os.Handler().postDelayed({
            _messages.value?.add(Message(2, _reserved_messages[Random.nextInt(0, _reserved_messages.size)]))
            _messages.value?.add(Message(2, _reserved_messages[Random.nextInt(0, _reserved_messages.size)]))
            _messages.value = _messages.value
        }, 2000)
    }


}