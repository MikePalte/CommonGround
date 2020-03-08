package com.example.commonground.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.commonground.dto.CalendarEvent
import com.example.commonground.services.Database

class MainViewModel: ViewModel() {
    var events: MutableLiveData<ArrayList<CalendarEvent>> = MutableLiveData()
    var db: Database = Database()

    init {
        events = db.getEvents()
    }
}
