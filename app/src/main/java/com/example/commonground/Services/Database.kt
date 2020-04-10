package com.example.commonground.Services

import androidx.lifecycle.MutableLiveData
import com.example.commonground.DTO.CalendarEvent
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.ArrayList

class Database {
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var _events: MutableLiveData<ArrayList<CalendarEvent>> = MutableLiveData()

    fun getEvents(): MutableLiveData<ArrayList<CalendarEvent>>{
        var tempEvents: ArrayList<CalendarEvent> = ArrayList()
        db.collection("events")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val event = CalendarEvent(
                        document.get("eventName") as String,
                        document.get("eventDate") as Timestamp
                    )
                    tempEvents.add(event)
                }
                _events.value = tempEvents;
            }
            .addOnFailureListener { exception ->

            }
        return _events
    }

    fun addEvent(eventName: String, eventDate: Timestamp){
        val calendarEvent = hashMapOf("eventName" to eventName,"eventDate" to eventDate)
        db.collection("events").add(calendarEvent).addOnSuccessListener { getEvents() }
    }

    fun getEventByDay(){

    }
}