package com.example.contactapp02

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import kotlin.properties.Delegates

class Fragment2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_2, container, false)

        val image : ImageView = view.findViewById(R.id.detail_imageview)
        val name : TextView = view.findViewById(R.id.detail_name)
        val number : TextView = view.findViewById(R.id.detail_number)
        val idText : TextView = view.findViewById(R.id.detail_id_text)
        val id : TextView = view.findViewById(R.id.detail_id_content)
        val emailText : TextView = view.findViewById(R.id.detail_email_text)
        val email : TextView = view.findViewById(R.id.detail_email_content)

        idText.text = arguments?.getString("idText")
        emailText.text = arguments?.getString("emailText")
        arguments?.let { image.setImageResource(it.getInt("image")) }
        name.text = arguments?.getString("name")
        number.text = arguments?.getString("number")
        id.text = arguments?.getString("id")
        email.text = arguments?.getString("email")

        return view
    }
}