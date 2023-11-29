package com.example.contactapp02

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.contactapp02.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var fragmentManager : FragmentManager
    private lateinit var binding : ActivityMainBinding

    private lateinit var contactView : ConstraintLayout
    private lateinit var backBtn : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Constants.init()

        contactView = findViewById(R.id.contactView)

        backBtn = findViewById(R.id.back_btn)
        backBtn.setOnClickListener {
            gotoFragment(Fragment1())
            backBtn.visibility = View.GONE
            binding.addContact.visibility = View.VISIBLE
        }
        binding.addContact.setOnClickListener {
            gotoFragment(Fragment3())
            backBtn.visibility = View.VISIBLE
            binding.addContact.visibility = View.GONE
        }
    }

    private fun gotoFragment(fragment : Fragment) {
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }

    override fun passDetailData(
        idText: String,
        emailText: String,
        image: Int,
        name: String,
        number: String,
        id: String,
        email: String
    ) {
        val bundle = Bundle()
        bundle.putString("idText", idText)
        bundle.putString("emailText", emailText)
        bundle.putInt("image", image)
        bundle.putString("name", name)
        bundle.putString("number", number)
        bundle.putString("id", id)
        bundle.putString("email", email)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragment2 = Fragment2()
        fragment2.arguments = bundle

        transaction.replace(R.id.fragmentContainer, fragment2).commit()

        backBtn.visibility = View.VISIBLE
        binding.addContact.visibility = View.GONE
    }

    override fun passContactData() {
        gotoFragment(Fragment1())
        backBtn.visibility = View.GONE
        binding.addContact.visibility = View.VISIBLE
    }

    override fun turnOnContact(name : String) {
        contactView.visibility = View.VISIBLE

        contactView.visibility = View.VISIBLE

        val closeBtn : ImageButton = findViewById(R.id.close_btn)
        closeBtn.setOnClickListener {
            contactView.visibility = View.GONE
        }

        val nameView: TextView = findViewById(R.id.contact_name)
        nameView.text = name
    }
}
