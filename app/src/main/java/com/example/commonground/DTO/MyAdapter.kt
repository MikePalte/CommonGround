package com.example.commonground.DTO

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.commonground.R

class MyAdapter(var myAdapterContext:Context, var resources: Int, var items:List<CalendarEvent>):ArrayAdapter<CalendarEvent>(myAdapterContext, resources, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(myAdapterContext)

        val view:View = layoutInflater.inflate(resources, null)
        val titleTextView:TextView = view.findViewById(R.id.mainText)
        val descriptionTextView:TextView = view.findViewById(R.id.secondText)


        val myAdapterItem = this.items[position]
        titleTextView.text = myAdapterItem.eventName
        descriptionTextView.text = myAdapterItem.eventDate.toDate().toString()

        return view
    }

}