package com.example.composablepdd.screens.settings.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composablepdd.R
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.database.preferences.SaveReminder
import com.example.composablepdd.ui.myTheme.AppTheme

@Composable
fun Notification(
    appTheme: AppTheme,
    isVisible: MutableState<Boolean>,
    mainViewModel: MainViewModel
) {
    val index = mainViewModel.getSaveReminder.prefs.getInt("reminder_replay", 0)
    val icon =
        if (index == 0) R.drawable.ic_notifications_off else R.drawable.ic_notifications_active

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding()
            .animateContentSize()
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Напоминания",
                color = appTheme.colorTextApp(),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                ),
                modifier = Modifier
                    .padding(end = 8.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_circle_24),
                contentDescription = null,
                tint = appTheme.colorIconApp(),
                modifier = Modifier
                    .size(14.dp)
                    .padding(end = 8.dp)
            )
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = appTheme.colorIconApp(),
                modifier = Modifier
                    .size(18.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                )
                .border(
                    .3.dp,
                    appTheme
                        .colorTextApp()
                        .copy(.3f),
                    RoundedCornerShape(15.dp)
                )
                .clickable(
                    onClick = { isVisible.value = true },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Напомнить",
                color = appTheme.colorTextApp(),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                ),
                modifier = Modifier
                    .padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
            )
            if (!isVisible.value) {
                NotificationDescription(appTheme, mainViewModel)
            }
        }
    }
}

@Composable
fun NotificationDescription(
    appTheme: AppTheme,
    mainViewModel: MainViewModel
) {
    val getSaveReminder = mainViewModel.getSaveReminder
    val index = getSaveReminder.prefs.getInt("reminder_replay", 0)
    val text = getSaveReminder.listReplayItemText[index]
    Column(
        modifier = Modifier.padding(end = 10.dp, top = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = text,
            color = appTheme.colorTextApp().copy(0.5f),
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.font_main_bold)),
            ),
            modifier = Modifier
                .padding(end = 8.dp)
        )
        if (index != 0) {
            val hour = getSaveReminder.prefs.getInt("reminder_hour", 99)
            val hourZero = if (hour <= 9) "0" else ""
            val minute = getSaveReminder.prefs.getInt("reminder_minute", 99)
            val minuteZero = if (minute <= 9) "0" else ""
            Text(
                text = "$hourZero$hour:$minuteZero$minute",
                color = appTheme.colorTextApp().copy(0.5f),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                ),
                modifier = Modifier
                    .padding(end = 8.dp)
            )
        }
    }
}