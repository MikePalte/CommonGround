package com.example.commonground.Services

import androidx.lifecycle.MutableLiveData
import com.example.commonground.DTO.CalendarEvent
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Time
import java.util.*
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

    fun getEventByDay(year: Int, month: Int, day: Int): MutableLiveData<ArrayList<CalendarEvent>>{

        var endDate = Date(Date(year, month, day, 23,59,59).time - Date(1970, 0, 0).time)
        var beginDate = Date(Date(year, month, day, 4,0,0).time - Date(1970, 0, 0).time)
        var tempEvents: ArrayList<CalendarEvent> = ArrayList()

        db.collection("events")
            .orderBy("eventDate")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val date = document.get("eventDate") as Timestamp
                    val convertedDate = date.toDate()
                    val event = CalendarEvent(
                        document.get("eventName") as String,
                        document.get("eventDate") as Timestamp
                    )
                    if (convertedDate.compareTo(beginDate) > 0
                        && convertedDate.compareTo(endDate) < 0
                        || convertedDate.compareTo(beginDate) == 0) {
                        tempEvents.add(event);
                    }
                }
                _events.value = tempEvents;
            }
            .addOnFailureListener { exception ->
                var i = 0
            }
        return _events
    }
}