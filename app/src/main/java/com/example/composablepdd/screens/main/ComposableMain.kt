package com.example.composablepdd.screens.main

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.R
import com.example.composablepdd.ui.myTheme.AppTheme
import com.example.composablepdd.screens.main.elements.ButtonRegion
import com.example.composablepdd.screens.main.elements.ButtonSettings
import com.example.composablepdd.screens.main.elements.ButtonsTicket
import com.example.composablepdd.screens.main.elements.MainTopMenu
import com.example.composablepdd.screens.main.elements.MarathonPreview
import com.example.composablepdd.screens.main.elements.MiniStatistics
import com.example.composablepdd.screens.main.elements.TitleBook
import com.example.composablepdd.screens.main.elements.columnBook
import com.example.composablepdd.ui.theme.TextLinkColor
import kotlin.math.max

@Composable
fun ScreenMain(
    navController: NavController,
    outerListState: LazyListState,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val appTheme = mainViewModel.getAppTheme

    val screenHeight = with(LocalDensity.current) { 200.dp.toPx() }
    val headerHeight = screenHeight / 2
    val decreasingNum = remember { Animatable(1f) }
    val increasingNum = remember { Animatable(1f) }

    LaunchedEffect(outerListState) {
        snapshotFlow { outerListState.firstVisibleItemScrollOffset }
            .collect { offset ->
                if (outerListState.firstVisibleItemIndex == 0) {
                    val newAlpha = max(1f - (offset / headerHeight * 2), 0f)
                    decreasingNum.snapTo(newAlpha)
                    increasingNum.snapTo(1f - newAlpha * 1.5f)
                } else {
                    decreasingNum.snapTo(0f)
                    increasingNum.snapTo(1f)
                }
            }
    }

    val isVisibleMarathonPreview = remember { mutableStateOf(false) }
    val blur = if (isVisibleMarathonPreview.value) 30.dp else 0.dp

    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(appTheme.backgroundApp())
                .blur(blur)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                state = outerListState,
                contentPadding = PaddingValues(top = 15.dp)
            ) {
                item { Spacer(Modifier.height(100.dp)) }
                item { MiniStatistics() }
                item { ButtonsTicket(appTheme, navController, isVisibleMarathonPreview) }
                item { ButtonRegion(appTheme, navController) }
                item { TitleBook() }
                columnBook(appTheme, navController)
                item { MainScreenText(appTheme, mainViewModel) }
                item { MainScreenSpacer() }
            }
        }
        Box(
            Modifier
                .fillMaxSize()
                .blur(blur)
        ) {
            MainTopMenu(
                appTheme = appTheme,
                isVisible = isVisibleMarathonPreview,
                decreasingNum = decreasingNum,
                increasingNum = increasingNum,
            )
            if (!isVisibleMarathonPreview.value) {
                ButtonSettings(appTheme, navController)
            }
        }
        MarathonPreview(
            isVisible = isVisibleMarathonPreview,
            navController = navController,
            mainViewModel = mainViewModel
        )
    }
}

@Composable
fun MainScreenText(
    appTheme: AppTheme,
    mainViewModel: MainViewModel
) {
    val text = buildAnnotatedString {
        append("Правила и экзаменационные билеты получены с ")
        pushStringAnnotation("URL", "https://госавтоинспекция.рф/mens/avtovladeltsam/abm")
        withStyle(
            style = SpanStyle(
                color = TextLinkColor,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("официального сайта ГИБДД МВД России.")
        }
        pop()
        append("\n\nНе являются официальным приложением ГИБДД МВД России.")
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ClickableText(
            text = text,
            onClick = { offset ->
                text.getStringAnnotations("URL", offset, offset)
                    .firstOrNull()?.let { annotation ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item)).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        mainViewModel.applicationContext.startActivity(intent)
                    }
            },
            style = TextStyle(
                color = appTheme.colorTextApp().copy(0.8f),
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.font_main_light))
            ),
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
            )
        )
    }
}

@Composable
fun MainScreenSpacer() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    )
}





