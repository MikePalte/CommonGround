package com.example.commonground.DTO

import com.google.firebase.Timestamp

data class CalendarEvent(var eventName: String, var eventDate: Timestamp, var eventLocation: String) {

}
