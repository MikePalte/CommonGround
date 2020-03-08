package com.example.commonground.DTO

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.commonground.R

class MyAdapter(var mCtx: Context, var resources: Int, var items: List<CalendarEvent>) :
    ArrayAdapter<CalendarEvent>(mCtx, resources, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(mCtx)


        val view = layoutInflater.inflate(resources, null)
        val titleTextView = view.findViewById<TextView>(R.id.mainText)
        val descriptionTextView = view.findViewById<TextView>(R.id.secondText)


        val mItem = items[position]
        titleTextView.text = mItem.eventName
        descriptionTextView.text = mItem.eventDate.toDate().toString()

        return view
    }

}