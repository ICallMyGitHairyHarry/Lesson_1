package com.example.lesson_1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_1.model.Post
import com.example.lesson_1.repository.Repository


class CreatPostFragment : Fragment() {

    private lateinit var viewModel: CreatPostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creat_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val repository = Repository()
        val viewModelFactory = CreatPostViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CreatPostViewModel::class.java)

//        val testPost = Post(1,1, "hey", "not hey")
//        viewModel.pushPost(testPost)
//        viewModel.pushPost2(2, 2, "hey", "not hey")

        viewModel.myResponse.observe(this, Observer {response ->
            if (response.isSuccessful) {
                Toast.makeText(activity, response.message().toString(), Toast.LENGTH_SHORT).show()
                Log.d("RESPONSE", response.code().toString())
                Log.d("RESPONSE", response.body().toString())
            } else {
                Toast.makeText(activity, response.code().toString(), Toast.LENGTH_SHORT).show()
            }
        })

        val inputTitle = view.findViewById<EditText>(R.id.title_edit_text)
        val inputBody = view.findViewById<EditText>(R.id.body_edit_text)
        val sendBut = view.findViewById<Button>(R.id.sendbtn)
        sendBut.setOnClickListener() {
            viewModel.pushPost(Post(1, 1, inputTitle.text.toString(), inputBody.text.toString()))
            inputBody.text.clear()
            inputTitle.text.clear()
        }

        val backBut = view.findViewById<Button>(R.id.back3)
        backBut.setOnClickListener() {
            val menuFragment = MenuFragment()
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.mm_frame, menuFragment)
                commit()
            }
        }


    }
}