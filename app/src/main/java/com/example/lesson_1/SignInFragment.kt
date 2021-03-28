package com.example.lesson_1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class SignInFragment : Fragment(), View.OnClickListener {

    private lateinit var communicator: Communicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        communicator = activity as Communicator

        val sign_in_hint = view.findViewById<TextView>(R.id.sign_in_hint)
        val reg_button = view.findViewById<Button>(R.id.reg_button)
        sign_in_hint.setOnClickListener(this)
        reg_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.sign_in_hint -> {
                val ActualSingInFragment = ActualSingInFragment()
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.MainFrame, ActualSingInFragment)
                    commit()
                }
            }
            R.id.reg_button -> {

                // :/
                var email_layout = view?.findViewById<TextInputLayout>(R.id.email_sign_up_layout)?.hint.toString()
                var pass_layout = view?.findViewById<TextInputLayout>(R.id.pass_sign_up_layout)?.hint.toString()
                var name_layout = view?.findViewById<TextInputLayout>(R.id.name_sign_up_layout)?.hint.toString()
                var sec_name_layout = view?.findViewById<TextInputLayout>(R.id.secname_sign_up_layout)?.hint.toString()
                var pass_conf_layout = view?.findViewById<TextInputLayout>(R.id.passconf_sign_up_layout)?.hint.toString()
                val pass= view?.findViewById<EditText>(R.id.pass_sign_up_form)?.text.toString()
                val email = view?.findViewById<EditText>(R.id.email_sign_up_form)?.text.toString()
                val name = view?.findViewById<EditText>(R.id.name_sign_up_form)?.text.toString()
                val secname = view?.findViewById<EditText>(R.id.secname_sign_up_form)?.text.toString()
                val passconf = view?.findViewById<EditText>(R.id.passconf_sign_up_form)?.text.toString()

                    if (pass== "" || email == "" || name == "" || secname == "" || passconf == "" || pass != passconf || pass.length < 8) {
                        if (pass != "") { pass_layout = "" }
                        if (email != "") { email_layout = "" }
                        if (name != "") { name_layout = "" }
                        if (secname != "") { sec_name_layout = "" }
                        if (passconf != "") { pass_conf_layout = "" }
                        communicator.transferBigData(email_layout, name_layout, sec_name_layout, pass_layout, pass_conf_layout, pass, passconf)
                    } else {
                        val intent = Intent(activity, MainMenuActivity::class.java)
                        intent.putExtra("welcome", "Успешная регистрация")
                        intent.putExtra("user_data", User(email, pass))
                        if (activity?.packageManager?.let { intent.resolveActivity(it) } != null) {
                            startActivity(intent)
                        }
                    }
            }
        }
    }

}