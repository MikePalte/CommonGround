package com.example.commonground.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.commonground.DTO.CalendarEvent
import com.example.commonground.Services.Database

class MainViewModel: ViewModel() {
    var events: MutableLiveData<ArrayList<CalendarEvent>> = MutableLiveData()
    var db: Database = Database()

    init{
        fetchEvents()
    }

    fun fetchEvents() {
        events = db.getEvents()
    }
}
