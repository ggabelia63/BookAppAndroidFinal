package com.example.androidfinal.fragments

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfinal.R
import com.example.androidfinal.adapters.BookItemAdapter
import com.example.androidfinal.api.BooksApi
import com.example.androidfinal.data.BookViewModel
import com.example.androidfinal.models.Books
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment: Fragment() {
    private lateinit var bBooksViewModel: BookViewModel
    private val ChannelId = ""
    private val notificationId = 101

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        bBooksViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)





        fun getTextFromSearch(): String? {
            val text = view.findViewById<EditText>(R.id.search).text.toString()
            return text.ifEmpty {
                Toast.makeText(view.context, "Fill the Field", Toast.LENGTH_LONG).show()
                null
            }
        }

        fun loadData() {
            val searchText = getTextFromSearch()
            if (searchText != null) {
                val call = BooksApi.requests.getBooks(searchText)
                call.enqueue(object : Callback<Books> {
                    override fun onResponse(call: Call<Books>, response: Response<Books>) {
                        when (response.isSuccessful) {
                            true -> {
                                val books = response.body()!!
                                recyclerView.adapter = BookItemAdapter(books, bBooksViewModel, requireContext())
                            }
                            false -> Toast.makeText(view.context, "No response", Toast.LENGTH_LONG)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<Books>, t: Throwable) {
                        Toast.makeText(view.context, "Request failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

        view.findViewById<Button>(R.id.search_btn).setOnClickListener {
            loadData()
        }

        loadData()
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(){
        if (VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val name = "Success"
            val description_text = "Added to Favorites"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(ChannelId,name,importance).apply {
                description= description_text
            }

            val notificationManager = requireActivity().getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}