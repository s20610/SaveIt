package com.borysante.saveit.util.formatter

import com.google.firebase.Timestamp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateFormatter {
    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    // Extension function to convert LocalDate to Timestamp
    fun LocalDate.toTimestamp(): Timestamp {
        val instant = this.atStartOfDay(ZoneId.systemDefault()).toInstant()
        return Timestamp(instant.epochSecond, instant.nano)
    }

    // Extension function to convert Timestamp to LocalDate
    fun Timestamp.toLocalDate(): LocalDate {
        val instant = Instant.ofEpochSecond(this.seconds.toLong(), this.nanoseconds.toLong())
        return instant.atZone(ZoneId.systemDefault()).toLocalDate()
    }
}