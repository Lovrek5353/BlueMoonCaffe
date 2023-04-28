package com.example.bluemooncaffe.data

import android.icu.text.SimpleDateFormat
import com.google.firebase.Timestamp
import java.util.*


val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

fun getDate(timestamp: Timestamp): String? {
        val formattedDate=dateFormat.format(timestamp.toDate())
        return formattedDate
}

