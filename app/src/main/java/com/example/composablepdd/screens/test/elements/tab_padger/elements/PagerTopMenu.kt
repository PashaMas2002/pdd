package com.example.composablepdd.screens.test.elements.tab_padger.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composablepdd.R
import com.example.composablepdd.ui.myTheme.AppTheme

@Composable
fun PagerTopMenu(
    appTheme: AppTheme,
    titleText: String,
    navController: NavController,
    theEndTest: MutableState<Boolean>,
    timeUs: MutableState<Boolean>,
    examTimeMinutes: MutableState<Int>,
    variantTest: Int?
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(start = 20.dp, bottom = 10.dp)
                .size(20.dp)
                .clip(CircleShape)
                .align(Alignment.BottomStart)
                .background(
                    appTheme
                        .colorIconApp()
                        .copy(.75f)
                )
                .clickable {
                    navController.navigate("MAIN_SCREEN") {
                        popUpTo("MAIN_SCREEN") { inclusive = true }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_exit),
                contentDescription = null,
                tint = appTheme.foregroundApp(),
                modifier = Modifier.fillMaxSize(.8f)
            )
        }
        Text(
            text = titleText,
            color = appTheme.colorTextApp(),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.font_main_bold)),
            ),
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomCenter)
        )
        if (variantTest == 1 && examTimeMinutes.value != 0) {
            TextTimer(
                color2 = appTheme.colorTextApp(),
                modifier = Modifier.align(Alignment.BottomEnd),
                timeForTimer = examTimeMinutes.value * 60 * 1000L,
                theEndTest = theEndTest,
                timeUp = timeUs
            )
        }
    }
}



