package com.example.commonground.DTO

import android.content.Context
import android.graphics.ColorSpace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.commonground.R

class MyAdapter(var mCtx:Context, var resources: Int, var items:List<CalendarEvent>):ArrayAdapter<CalendarEvent>(mCtx, resources, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)

        val view:View = layoutInflater.inflate(resources, null)
        val titleTextView:TextView = view.findViewById(R.id.mainText)
        val descriptionTextView:TextView = view.findViewById(R.id.secondText)


        var mItem:CalendarEvent = items[position]
        titleTextView.text = mItem.eventName
        descriptionTextView.text = mItem.eventDate.toDate().toString()

        return view
    }

}