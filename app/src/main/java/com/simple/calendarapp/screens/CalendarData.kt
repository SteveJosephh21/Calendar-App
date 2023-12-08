package com.simple.calendarapp.screens

import com.simple.calendarapp.datatclass.Calendar
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.Stream

class CalendarData {

    val today: LocalDate
        get() {
            return LocalDate.now()
        }

    fun getPreviousMonth(firstDayOfMonth: LocalDate): Calendar {
        val prevDay = firstDayOfMonth.minusDays(1)
        val prevMonth = YearMonth.of(prevDay.year, prevDay.month)
        return Calendar(
            yearMonth = prevMonth,
            visibleDates = getDatesInBetween(
                prevMonth.atDay(1),
                firstDayOfMonth
            )
        )
    }

    fun getNextMonth(lastDayOfMonth: LocalDate): Calendar {
        val nextDay = lastDayOfMonth.plusDays(1)
        val nextMonth = YearMonth.of(nextDay.year, nextDay.month)
        return Calendar(
            yearMonth = nextMonth,
            visibleDates = getDatesBetween(
                nextMonth.atDay(1),
                nextMonth.plusMonths(1).atDay(1)
            )
        )
    }

    fun getTodayCalendar(): Calendar {
        val yearMonth = YearMonth.now()
        val firstOfMonth = yearMonth.atDay(1)
        val firstOfFollowingMonth = yearMonth.plusMonths(1).atDay(1)
        return Calendar(
            yearMonth = yearMonth,
            visibleDates = getDatesBetween(
                firstOfMonth,
                firstOfFollowingMonth
            )
        )
    }

    private fun getDatesInBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
        val numOfDays = ChronoUnit.DAYS.between(startDate, endDate)
        return Stream.iterate(startDate) { date ->
            date.plusDays(1)
        }.limit(numOfDays).collect(Collectors.toList())
    }

    private fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
        val numOfDays = ChronoUnit.DAYS.between(startDate, endDate)
        return Stream.iterate(startDate) { date ->
            date.plusDays(1)
        }
            .limit(numOfDays)
            .collect(Collectors.toList())
    }
}