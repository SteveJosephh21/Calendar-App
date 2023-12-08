package com.simple.calendarapp.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.simple.calendarapp.datatclass.Calendar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val calendarData = CalendarData()
    var data by remember { mutableStateOf(calendarData.getTodayCalendar()) }
    Column(modifier = modifier.fillMaxSize()) {
        Header(
            data = data,
            onPrevClickListener = { startDate ->
                data = calendarData.getPreviousMonth(startDate)
            },
            onNextClickListener = { endDate ->
                data = calendarData.getNextMonth(endDate)
            }
        )
        Content(data = data, calendarData) { date ->
            Toast.makeText(
                context,
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@Composable
fun Header(
    data: Calendar,
    onPrevClickListener: (LocalDate) -> Unit,
    onNextClickListener: (LocalDate) -> Unit,
) {
    Row {
        Text(
            text = data.yearMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")),
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            style = TextStyle(color = MaterialTheme.colorScheme.primary)
        )
        IconButton(onClick = {
            onPrevClickListener(data.startDate)
        }) {
            Icon(
                imageVector = Icons.Filled.ChevronLeft,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        IconButton(onClick = {
            onNextClickListener(data.endDate)
        }) {
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = "Next",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun Content(
    data: Calendar,
    calendarData: CalendarData,
    onDateClickListener: (LocalDate) -> Unit,
) {
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 48.dp)) {
        items(data.visibleDates.size) { index ->
            ContentItem(
                date = data.visibleDates[index],
                calendarData,
                onDateClickListener
            )
        }
    }
}

@Composable
fun ContentItem(
    date: LocalDate,
    calendarData: CalendarData,
    onClickListener: (LocalDate) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .clickable {
                onClickListener(date)
            },
        colors =
        if (date == calendarData.today) {
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        } else {
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        },
    ) {
        Column(
            modifier = Modifier
                .width(40.dp)
                .height(48.dp)
                .padding(4.dp)
        ) {
            Text(
                text = date.format(DateTimeFormatter.ofPattern("E")),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = date.dayOfMonth.toString(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}