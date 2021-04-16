package com.example.lesson_1

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlin.random.Random


class MainMenuActivity : AppCompatActivity() {

    val reserved_messages = arrayListOf("Даже не знаю, что ответить...", "Я отвечу позже", "Подожди, я думаю", "Наверное, я плохой собеседник", "Прости, я не знаю", "Привет!!!", "Шутки шутками, но в шутке доля шутки, как грится", "Шутка? Нет, не шутка")
    val messages = arrayListOf<Message>()
    val adapter = chatAdapter()




    @SuppressLint("ClickableViewAccessibility", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val viewModel = ViewModelProvider(this).get(MainMenuActivityViewModel::class.java)

        val chat = findViewById<RecyclerView>(R.id.recycler_view)

        viewModel.messages().observe(this, Observer {
            adapter.setData(it)
        })
        chat.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        chat.adapter = adapter

        val messFormLayout = findViewById<TextInputLayout>(R.id.mess_form_layout)
        val message_form = findViewById<EditText>(R.id.mess_form)
        messFormLayout.endIconMode = TextInputLayout.END_ICON_CUSTOM
        messFormLayout.endIconDrawable = resources.getDrawable(R.drawable.union)
        messFormLayout.setEndIconOnClickListener{
            viewModel.updateMessages(Message(1, message_form.text.toString()))
            android.os.Handler().postDelayed({ viewModel.addMessages() }, 2000)
            message_form.text.clear()
        }

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




    }

//    fun addMessages() {
//        messages.add(Message(2, reserved_messages[Random.nextInt(0,reserved_messages.size)]))
//        messages.add(Message(2, reserved_messages[Random.nextInt(0,reserved_messages.size)]))
//    }
}

@Parcelize
data class Message(val person: Int, val text: String): Parcelable {
}