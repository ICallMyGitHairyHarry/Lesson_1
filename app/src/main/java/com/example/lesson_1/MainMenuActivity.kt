package com.example.lesson_1

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlin.random.Random


class MainMenuActivity : AppCompatActivity() {

    val reserved_messages = arrayListOf("Даже не знаю, что ответить...", "Я отвечу позже", "Подожди, я думаю", "Наверное, я плохой собеседник", "Прости, я не знаю", "Привет!!!", "Шутки шутками, но в шутке доля шутки, как грится", "Шутка? Нет, не шутка")
    val messages = arrayListOf<Message>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val extra = intent.extras

        extra?.getString("welcome", "Здесь текст").let {
            welcome.text = it
        }

        val user_data = extra?.getParcelable<User>("user_data")
        user_data.let {
            user_data_text.text = it?.email.toString()
            user_data_text.append(" ")
            user_data_text.append(it?.password.toString())
        }


        val chat = findViewById<RecyclerView>(R.id.recycler_view)

        val adapter = chatAdapter()
        adapter.setData(messages)
        chat.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        chat.adapter = adapter

        val message_form = findViewById<EditText>(R.id.mess_form)

        message_form.setOnTouchListener(OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= message_form.getRight() - message_form.getCompoundDrawables().get(DRAWABLE_RIGHT).getBounds().width()) {
                    messages.add(Message(1, message_form.text.toString()))
                    addMessages()
                    message_form.text.clear()
                    adapter.setData(messages)
                    return@OnTouchListener true
                }
            }
            false
        })
    }

    fun addMessages() {
        messages.add(Message(2, reserved_messages[Random.nextInt(0,reserved_messages.size)]))
        messages.add(Message(2, reserved_messages[Random.nextInt(0,reserved_messages.size)]))
    }
}

@Parcelize
data class Message(val person: Int, val text: String): Parcelable {
}