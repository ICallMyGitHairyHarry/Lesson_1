package com.example.lesson_1

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.parcel.Parcelize

class ActualSingInFragment : Fragment(), View.OnClickListener, Communicator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_actual_sing_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val sing_up_hint = view.findViewById<TextView>(R.id.sing_up_hint)
        val login = view.findViewById<Button>(R.id.login)
        sing_up_hint.setOnClickListener(this)
        login.setOnClickListener(this)



    }

    override fun transferBigData(Data: BigData) {
        val bundle = Bundle()
        bundle.putParcelable("data", Data)
        val blankFragment = BlankFragment()
        blankFragment.arguments = bundle
        activity?.supportFragmentManager?.let { blankFragment.show(it, "blank_fragment") }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.sing_up_hint -> {
                val SignInFragment = SignInFragment()
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.MainFrame, SignInFragment)
                    commit()
                }
            }
            R.id.login -> {
                val email_layout = view?.findViewById<TextInputLayout>(R.id.email_layout)
                val pass_layout = view?.findViewById<TextInputLayout>(R.id.Pass_layout)
                val pass= view?.findViewById<EditText>(R.id.pass_form)
                val email = view?.findViewById<EditText>(R.id.email_form)
                if (pass != null && email != null) {
                    if (pass.getText()?.toString() == "" || email.getText()?.toString() == "") {
                        var pass_string = ""
                        var email_string = ""
                        if (pass.getText()?.toString() == "") {
                            pass_string = pass_layout?.hint.toString()
                        }
                        if (email.getText()?.toString() == "") {
                            email_string = email_layout?.hint.toString()
                        }
                        transferBigData(BigData(email_string, null, null,  pass_string, null, null, null))
                    } else {
                        val intent = Intent(activity, MainMenuActivity::class.java)
                        intent.putExtra("welcome", "Привет, рады снова тебя видеть!")
                        intent.putExtra("user_data", User(email.text.toString(), pass.text.toString()))
                        if (activity?.packageManager?.let { intent.resolveActivity(it) } != null) {
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}


@Parcelize
data class User (val email: String?, val password: String?): Parcelable {}