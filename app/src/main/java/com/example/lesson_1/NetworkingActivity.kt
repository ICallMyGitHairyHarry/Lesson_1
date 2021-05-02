package com.example.lesson_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NetworkingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networking)

        val menuFragment = MenuFragment()

        supportFragmentManager.beginTransaction().apply {
            add(R.id.mm_frame, menuFragment)
            commit()
        }
    }
}