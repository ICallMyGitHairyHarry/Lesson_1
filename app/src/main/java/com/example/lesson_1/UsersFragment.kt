package com.example.lesson_1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_1.repository.Repository


class UsersFragment : Fragment() {

    private lateinit var viewModel: UsersViewModel
    private val adapter = networkAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userList = view.findViewById<RecyclerView>(R.id.users_recycler_view)

        userList.adapter = adapter
        userList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        val repository = Repository()
        val viewModelFactory = UsersViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(UsersViewModel::class.java)
        viewModel.getUsers()
        viewModel.userResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { adapter.setUsersData(it) }
            } else {
                Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show()
            }
        })

        val backBut = view.findViewById<Button>(R.id.back2)
        backBut.setOnClickListener() {
            val menuFragment = MenuFragment()
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.mm_frame, menuFragment)
                commit()
            }
        }


    }
}