package com.simple.calendarapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simple.calendarapp.screens.CalendarScreen
import com.simple.calendarapp.ui.theme.CalendarAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarAppTheme {
                CalendarScreen(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalendarAppTheme {
        CalendarScreen(
            modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(16.dp)
        )
    }
}