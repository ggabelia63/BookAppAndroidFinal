package com.example.androidfinal.fragments

import android.annotation.SuppressLint
import android.content.Intent

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.androidfinal.R
import com.example.androidfinal.models.Book
import com.squareup.picasso.Picasso

class DetailInfoFragment : Fragment() {


//    private val args: DetailInfoFragmentDirections by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = inflater.inflate(R.layout.fragment_detail_info, container, false)
//        Picasso.get().load(Uri.parse(args.image)).into(view.findViewById<ImageView>(R.id.book_image))
//        view.findViewById<TextView>(R.id.book_title).text = args.title
//        view.findViewById<TextView>(R.id.book_desc).text = args.description

        return view
    }
}