package com.example.lesson_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class MenuFragment : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val albums = view.findViewById<Button>(R.id.albums)
        val users = view.findViewById<Button>(R.id.users)
        val post = view.findViewById<Button>(R.id.post)
        albums.setOnClickListener(this)
        users.setOnClickListener(this)
        post.setOnClickListener(this)



    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.albums -> {
                val albumsFragment = AlbumsFragment()
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.mm_frame, albumsFragment)
                    commit()
                }
            }
            R.id.users -> {
                val usersFragment = UsersFragment()
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.mm_frame, usersFragment)
                    commit()
                }
            }
            R.id.post -> {
                val postFragment = CreatPostFragment()
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.mm_frame, postFragment)
                    commit()
                }
            }
        }
    }
}