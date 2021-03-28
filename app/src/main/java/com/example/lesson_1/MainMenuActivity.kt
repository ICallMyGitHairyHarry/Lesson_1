package com.example.lesson_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity() {
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
            user_data_text.append(", ")
            user_data_text.append(it?.password.toString())
        }
    }
}