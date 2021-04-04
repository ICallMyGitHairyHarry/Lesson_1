package com.example.lesson_1

import android.os.Bundle
import android.view.DisplayCutout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class BlankFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_blank, container, false)


        val emailText = view.findViewById<TextView>(R.id.loll)
        val warn = view.findViewById<TextView>(R.id.warn)
        val passes = view.findViewById<TextView>(R.id.passes)
        val pass8 = view.findViewById<TextView>(R.id.pass_8)

        val received_data = arguments?.getParcelable<BigData>("data")

        val Data1: Array<String?> = arrayOf(received_data?.email, received_data?.name, received_data?.secname, received_data?.pass, received_data?.pass_conf)
        val pass_v = received_data?.pass_value
        val pass_c_v = received_data?.pass_conf_value

        for (i in 0..Data1.size-2) {
            if (Data1[i] != null) { emailText.append(Data1[i]) }
            if (emailText.text.toString() != "" && Data1[i+1] != "" && Data1[i+1] != null) {
                emailText.append(", ")
            }
        }
        if (Data1[4] != null) { emailText.append(Data1[4]) }

        if (emailText.text.toString() != "") { warn.text = "Следующие поля должны быть заполнены:" }

        if (pass_v != null && pass_c_v != null) {
            if (pass_c_v != pass_v) {
                passes.text = "Пароль должен совпадать с подтверждением пароля"
            }
            if (pass_v.length < 8) {
                pass8.text = "Пароль должен быть не менее 8 символов"
            }
        }




        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



}