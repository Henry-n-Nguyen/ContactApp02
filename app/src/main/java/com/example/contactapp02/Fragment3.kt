package com.example.contactapp02

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Fragment3 : Fragment() {

    private lateinit var communicator: Communicator
    private lateinit var items : ArrayList<Item>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_3, container, false)

        communicator = activity as Communicator

        items = Constants.getData()

        val name : EditText = view.findViewById(R.id.name)
        val number : EditText = view.findViewById(R.id.number)
        val email : EditText = view.findViewById(R.id.email)

        val confirmBtn : Button = view.findViewById(R.id.confirm_btn)
        confirmBtn.setOnClickListener {
            if (name.text.isEmpty() || number.text.isEmpty() || email.text.isEmpty()) {
                Toast.makeText(context, "Not completed input fields", Toast.LENGTH_SHORT).show()
            } else {
                Constants.addData(R.drawable.c, name.text.toString(), number.text.toString(), email.text.toString())
                communicator.passContactData()
            }
        }

        return view
    }
}

