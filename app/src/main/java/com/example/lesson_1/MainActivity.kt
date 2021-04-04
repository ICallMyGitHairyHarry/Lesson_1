package com.example.lesson_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable

class MainActivity : AppCompatActivity() {

    val tag = "TEST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ActualSingInFragment = ActualSingInFragment()

        supportFragmentManager.beginTransaction().apply {
            add(R.id.MainFrame, ActualSingInFragment)
            commit()
        }
    }




}
