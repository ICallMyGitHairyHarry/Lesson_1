package com.example.lesson_1

import android.os.Bundle
import android.os.Parcelable
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
import com.example.lesson_1.model.Album
import com.example.lesson_1.repository.Repository
import kotlinx.android.parcel.Parcelize


class AlbumsFragment : Fragment() {

    private lateinit var viewModel: AlbumsViewModel
    private val adapter = networkAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userList = view.findViewById<RecyclerView>(R.id.albums_recycler_view)

        userList.adapter = adapter
        userList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        val repository = Repository()
        val viewModelFactory = AlbumViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AlbumsViewModel::class.java)
        viewModel.getAlbums()
        viewModel.albumResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { adapter.setAlbumsData(it) }
            } else {
                Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show()
            }
        })

        val backBut = view.findViewById<Button>(R.id.back)
        backBut.setOnClickListener() {
            val menuFragment = MenuFragment()
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.mm_frame, menuFragment)
                commit()
            }
        }


    }
}

