package com.example.roomapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.db.Person

class RecyclerViewAdapter(private val clickListener: (Person) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    private val personList = ArrayList<Person>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item,parent,false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(personList[position],clickListener)
    }

    override fun getItemCount(): Int {
        return  personList.size
    }

    fun setList(person:List<Person>){
        personList.clear()
        personList.addAll(person)
    }

}

class ViewHolder(private val view : View) : RecyclerView.ViewHolder(view){
    fun bind(person : Person , clickListener: (Person) -> Unit){
        val nameTextView = view.findViewById<TextView>(R.id.tvName)
        val emailTextView = view.findViewById<TextView>(R.id.tvEmail)
        nameTextView.text = person.name
        emailTextView.text = person.email

        // Addong click listener to the recycler view
        view.setOnClickListener {
            clickListener(person)
        }
    }
}