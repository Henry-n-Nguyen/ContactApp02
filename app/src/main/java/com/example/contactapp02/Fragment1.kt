package com.example.contactapp02

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class Fragment1 : Fragment() {

    private var CHANGE = 0

    private lateinit var recyclerView : RecyclerView
    private lateinit var items : ArrayList<Item>
    private lateinit var adapter : MyAdapter

    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Implement Fragment
        val view = inflater.inflate(R.layout.fragment_1, container, false)

        communicator = activity as Communicator

        recyclerView = view.findViewById(R.id.recyclerView)

        items = Constants.getData()
        adapter = MyAdapter(view.context, items)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter

        adapter.setOnClickListener( object : MyAdapter.OnClickListener {
            override fun onClick(position: Int, model: Item) {
                communicator.passDetailData("ID", "Email", model.image, model.name, model.number, model.id, model.email)
            }
        })

        adapter.setOnLongClickListener( object : MyAdapter.OnLongClickListener {
            override fun onLongClick(position: Int, model: Item) {
                communicator.turnOnContact(model.name)
            }
        })

        return view
    }
}

class Item() {

    var image: Int = 0
    lateinit var name: String
    lateinit var number: String
    lateinit var id: String
    lateinit var email: String

    constructor(image: Int, name: String, number: String, id: String, email: String) : this() {
        this.image = image
        this.name = name
        this.number = number
        this.id = id
        this.email = email
    }
}

object Constants {

    private var items = ArrayList<Item>()

    fun init() {
        // create an arraylist of type employee class
        items.add(Item(R.drawable.a, "Name 01", "0123456789", "0", "example.example@gmail.com"))
        items.add(Item(R.drawable.b, "Name 02", "0123456789", "1", "example.example@gmail.com"))
        items.add(Item(R.drawable.c, "Name 03", "0123456789", "2", "example.example@gmail.com"))
        items.add(Item(R.drawable.d, "Name 04", "0123456789", "3", "example.example@gmail.com"))
        items.add(Item(R.drawable.e, "Name 05", "0123456789", "4", "example.example@gmail.com"))
        items.add(Item(R.drawable.f, "Name 06", "0123456789", "5", "example.example@gmail.com"))
    }

    fun addData(image: Int, name: String, number: String, email: String) {
        items.add(Item(image, name, number, items.size.toString(), email))
    }

    fun getData():ArrayList<Item>{
        return  items
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imageView: ImageView
    var nameView: TextView

    init {
        imageView = itemView.findViewById(R.id.imageview)
        nameView = itemView.findViewById(R.id.name)
    }
}

class MyAdapter() : RecyclerView.Adapter<MyViewHolder>() {

    private lateinit var items: List<Item>
    private lateinit var context: Context

    private var onClickListener: OnClickListener? = null
    private var onLongClickListener: OnLongClickListener? = null

    constructor(context: Context, items: List<Item>) : this() {
        this.context = context
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.number_intro, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameView.text = items[position].name
        holder.imageView.setImageResource(items[position].image)

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, items[position])
            }
        }

        holder.itemView.setOnLongClickListener {
            if (onLongClickListener != null) {
                onLongClickListener!!.onLongClick(position, items[position])
            }
            true
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    fun setOnLongClickListener(onLongClickListener: OnLongClickListener) {
        this.onLongClickListener = onLongClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: Item)
    }

    interface OnLongClickListener {
        fun onLongClick(position: Int, model: Item)
    }
}