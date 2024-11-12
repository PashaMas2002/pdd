package com.example.composablepdd.screens.settings.elements

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.os.Build
import android.util.Log
import android.widget.NumberPicker
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Index
import com.example.composablepdd.R
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.database.MainDataBase
import com.example.composablepdd.database.preferences.SaveReminder
import com.example.composablepdd.ui.myTheme.AppTheme
import com.example.composablepdd.ui.theme.ColorLightGreen
import com.example.composablepdd.ui.theme.ColorLightRed
import com.example.composablepdd.ui.theme.ColorTabAndProgressLine
import com.example.composablepdd.ui.theme.Primary
import com.example.composablepdd.ui.theme.TextLinkColor
import java.util.Calendar

@Composable
fun PopUpReminder(
    isVisible: MutableState<Boolean>,
    mainViewModel: MainViewModel
) {
    val appTheme = mainViewModel.getAppTheme
    val replayItemCheck = remember {
        mutableIntStateOf(mainViewModel.getSaveReminder.prefs.getInt("reminder_replay", 0))
    }
    val supports = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val background =
        if (supports) Color.DarkGray.copy(.4f) else appTheme.colorPopUpWindow().copy(.98f)
    val elevation = if (supports) 0.dp else 10.dp
    val listReplayItemText = mainViewModel.getSaveReminder.listReplayItemText

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.95f)
            .border(
                .3.dp,
                appTheme
                    .colorTextApp()
                    .copy(.3f),
                RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
            ),
        colors = CardDefaults.cardColors(background),
        elevation = CardDefaults.elevatedCardElevation(elevation),
        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
    ) {
        PopUpReminderTop(
            isVisible = isVisible,
            saveReminder = mainViewModel.getSaveReminder,
            replayItemCheck = replayItemCheck
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
                .height(1.dp)
                .background(appTheme.backgroundApp())
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                start = 10.dp,
                end = 10.dp,
                top = 10.dp,
                bottom = 100.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ReminderTime(
                    background = background,
                    appTheme = appTheme,
                    saveReminder = mainViewModel.getSaveReminder
                )
            }
            item { ReminderReplay(appTheme) }
            itemsIndexed(listReplayItemText) { index, it ->
                ReminderReplayItem(
                    index = index,
                    appTheme = appTheme,
                    saveReminder = mainViewModel.getSaveReminder,
                    replayItemCheck = replayItemCheck,
                    textName = it
                )
            }
        }
    }
}

@Composable
fun PopUpReminderTop(
    isVisible: MutableState<Boolean>,
    saveReminder: SaveReminder,
    replayItemCheck: MutableState<Int>
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Отмена",
            color = TextLinkColor,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.font_main))
            ),
            modifier = Modifier
                .clickable(
                    onClick = { isVisible.value = false },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        )
        Text(
            text = "Готово",
            color = TextLinkColor,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.font_main))
            ),
            modifier = Modifier
                .clickable(
                    onClick = {
                        saveReminder.onClickSaveReminder(replayItemCheck.value)
                        isVisible.value = false
                    },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        )
    }
}

@Composable
fun ReminderReplay(
    appTheme: AppTheme
) {
    Text(
        text = "Повтор",
        color = appTheme.colorTextApp(),
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.font_main_bold)),
        ),
        modifier = Modifier
            .padding(20.dp)
    )
}

@Composable
fun ReminderReplayItem(
    index: Int,
    appTheme: AppTheme,
    saveReminder: SaveReminder,
    replayItemCheck: MutableState<Int>,
    textName: String
) {
    val font = if (index == replayItemCheck.value) {
        Font(R.font.font_main_bold)
    } else Font(R.font.font_main_light)

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 30.dp,
                end = 20.dp,
                bottom = 10.dp
            )
            .clickable(
                onClick = {
                    saveReminder.onClickReplayItem(index)
                    replayItemCheck.value = index
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_circle_24),
                contentDescription = null,
                tint = appTheme.colorIconApp(),
                modifier = Modifier
                    .size(14.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = textName,
                color = appTheme.colorTextApp(),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(font),
                ),
            )
        }
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .border(
                    2.dp,
                    appTheme
                        .colorTextApp()
                        .copy(.6f),
                    RoundedCornerShape(100)
                ),
            contentAlignment = Alignment.Center
        ) {
            if (index == replayItemCheck.value) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_circle_24),
                    contentDescription = null,
                    tint = Primary,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(1.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderTime(
    background: Color,
    appTheme: AppTheme,
    saveReminder: SaveReminder
) {
    val textColor = appTheme.colorTextApp()

    val currentTime = Calendar.getInstance()
    val initialHour =
        if (saveReminder.initialHour == 99) currentTime.get(Calendar.HOUR_OF_DAY) else saveReminder.initialHour
    val initialMinute =
        if (saveReminder.initialMinute == 99) currentTime.get(Calendar.MINUTE) else saveReminder.initialMinute
    val timePickerState = rememberTimePickerState(
        initialHour = initialHour,
        initialMinute = initialMinute,
        is24Hour = true,
    )
    LaunchedEffect(timePickerState.hour, timePickerState.minute) {
        saveReminder.initialHour = timePickerState.hour
        saveReminder.initialMinute = timePickerState.minute
    }

    Column {
        TimePicker(
            state = timePickerState,
            colors = TimePickerDefaults.colors().copy(
                clockDialColor = background.copy(.1f),
                selectorColor = Primary,
                clockDialSelectedContentColor = appTheme.backgroundApp(),
                clockDialUnselectedContentColor = textColor,
                timeSelectorSelectedContainerColor = Primary,
                timeSelectorUnselectedContainerColor = Color.Transparent,
                timeSelectorSelectedContentColor = Color.White,
                timeSelectorUnselectedContentColor = Primary
            )

        )
    }
}


