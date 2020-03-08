package com.example.commonground.services

import androidx.lifecycle.MutableLiveData
import com.example.commonground.dto.CalendarEvent
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.ArrayList

class Database {
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var _events: MutableLiveData<ArrayList<CalendarEvent>> = MutableLiveData()

    /**
     * Function to get events from the database
     * @return ArrayList of Calendar Events.
     */
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
                println("Exception encountered: $exception")
            }
        return _events
    }

    /**
     * Function to add events to the database.
     * @param eventName String: Name of the event.
     * @param eventDate Timestamp presumably used as unique identifier.
     */
    fun addEvent(eventName: String, eventDate: Timestamp){
        val calendarEvent = hashMapOf("eventName" to eventName,"eventDate" to eventDate)
        db.collection("events").add(calendarEvent).addOnSuccessListener { getEvents() }
    }
}