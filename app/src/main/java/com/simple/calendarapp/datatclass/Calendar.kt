package com.simple.calendarapp.datatclass

import androidx.compose.runtime.Stable
import java.time.LocalDate
import java.time.YearMonth

@Stable
data class Calendar(
    val yearMonth: YearMonth,
    val visibleDates: List<LocalDate>
) {

    val startDate: LocalDate = visibleDates.first()
    val endDate: LocalDate = visibleDates.last()
}
