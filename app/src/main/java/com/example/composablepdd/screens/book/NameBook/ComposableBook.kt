package com.example.composablepdd.screens.book.NameBook

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composablepdd.R
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.screens.book.NameBook.elements.TopMenuNameBook
import kotlin.math.max

@Composable
fun ScreenBook(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val titleText = stringResource(id = mainViewModel.getBook.getTitle())
    val appTheme = mainViewModel.getAppTheme
    val getNameBook = mainViewModel.getBook
    val list = getNameBook.getList()

    val outerListState = rememberLazyListState()
    val screenHeight = with(LocalDensity.current) { 200.dp.toPx() }
    val headerHeight = screenHeight / 2
    val decreasingNum = remember { Animatable(1f) }

    LaunchedEffect(outerListState) {
        snapshotFlow { outerListState.firstVisibleItemScrollOffset }
            .collect { offset ->
                if (outerListState.firstVisibleItemIndex == 0) {
                    val newAlpha = max(1f - (offset / headerHeight * 2), 0f)
                    decreasingNum.snapTo(newAlpha)
                } else {
                    decreasingNum.snapTo(0f)
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(appTheme.backgroundApp()),
    ) {
        TopMenuNameBook(
            title = titleText,
            appTheme = appTheme,
            decreasingNum = decreasingNum,
            navController = navController
        )
        LazyColumn(
            Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(top = 10.dp, bottom = 100.dp),
            state = outerListState
        ) {
            item {
                if (getNameBook.buttonId.intValue == 3) {
                    Is4ItemScreenBook(mainViewModel)
                }
            }
            itemsIndexed(list) { id, item ->
                ItemScreenBook(id, item, navController, mainViewModel)
            }
        }
    }
}

@Composable
fun ItemScreenBook(
    index: Int,
    bookName: String,
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val appTheme = mainViewModel.getAppTheme

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                mainViewModel.getBook.buttonBookId.intValue = index
                navController.navigate("LIST_BOOK_SCREEN")
            }
    ) {
        if (index != 0) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.5f.dp)
                    .padding(start = 20.dp)
                    .background(appTheme.foregroundApp())
            )
        }
        Text(
            text = bookName,
            color = appTheme.colorTextApp(),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.font_main)),
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}

@Composable
fun Is4ItemScreenBook(
    mainViewModel: MainViewModel
) {
    val appTheme = mainViewModel.getAppTheme
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = "Настоящий Перечень устанавливает неисправности автомобилей, автобусов, автопоездов, прицепов,\n" +
                    "мотоциклов, мопедов, тракторов, других самоходных машин и условия, при которых запрещается их эксплуатация. Методы\n" +
                    "проверки приведенных параметров регламентированы ГОСТом Р 51709-2001 \"Автотранспортные средства. Требования безопасности к техническому состоянию и методы проверки\"",
            color = appTheme.colorTextApp(),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.font_main)),
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.5f.dp)
                .padding(start = 20.dp)
                .background(appTheme.backgroundApp())
        )
    }
}

