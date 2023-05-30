package com.example.bluemooncaffe.data

import android.icu.text.SimpleDateFormat
import com.google.firebase.Timestamp
import java.util.*


val dateFormat = SimpleDateFormat("HH:mm:ss dd-MM-yyyy", Locale.getDefault())

fun getDate(timestamp: Timestamp): String? {
    val formattedDate = dateFormat.format(timestamp.toDate())
    return formattedDate
}

