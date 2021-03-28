package com.example.lesson_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable

class MainActivity : AppCompatActivity(), Communicator {

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

    override fun transferData(editTextInput: String, editTextInput2: String) {
        val bundle = Bundle()
        bundle.putString("email", editTextInput)
        bundle.putString("pass", editTextInput2)
        val blankFragment = BlankFragment()
        blankFragment.arguments = bundle
        blankFragment.show(this.supportFragmentManager, "blank_fragment")
    }

    override fun transferBigData(email: String, name: String, secname: String, pass: String, pass_conf: String, pass_value: String, pass_conf_value: String) {
        val bundle = Bundle()
        bundle.putString("email", email)
        bundle.putString("name", name)
        bundle.putString("secname", secname)
        bundle.putString("pass", pass)
        bundle.putString("pass_conf", pass_conf)
        bundle.putString("pass_value", pass_value)
        bundle.putString("pass_conf_value", pass_conf_value)
        val blankFragment = BlankFragment()
        blankFragment.arguments = bundle
        blankFragment.show(this.supportFragmentManager, "blank_fragment")
    }
}
