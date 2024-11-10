package com.example.composablepdd.screens.book.ListBook

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composablepdd.R
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.model.DataTestBook
import com.example.composablepdd.screens.item_book.ImageBitmapTestBook
import com.example.composablepdd.screens.item_book.TestBook
import com.example.composablepdd.screens.item_book.elements.TextLinkTestBook
import com.example.composablepdd.ui.myTheme.AppTheme
import kotlin.math.max

@Composable
fun ScreenListBook(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val appTheme = mainViewModel.getAppTheme
    val book =  mainViewModel.getBook.getListBook()
    val title = book.title
    val listItems = book.items
    val isList5 = mainViewModel.getBook.buttonBookId

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

    val isVisibleNestedTestBook = remember { mutableStateOf(false) }
    val keyNestedTestBook = remember { mutableStateOf("") }
    val titleSize = remember { Animatable(24f) }
    LaunchedEffect(isVisibleNestedTestBook.value) {
        titleSize.animateTo(if (isVisibleNestedTestBook.value) 18f else 24f)
    }
    val blur = if (isVisibleNestedTestBook.value) 50.dp else 0.dp

    Log.d("MyLog", "${isList5.intValue}")
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(appTheme.backgroundApp())
                .blur(blur),
        ) {
            TopMenuListBook(
                title = title,
                appTheme = appTheme,
                decreasingNum = decreasingNum,
                navController = navController,
            )

                LazyColumn(
                    Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(
                        top = 20.dp,
                        bottom = 120.dp,
                        start = 15.dp,
                        end = 15.dp
                    ),
                    state = outerListState
                ) {
                    itemsIndexed(listItems) { id, item ->
                        ItemScreenListBook(
                            item = item,
                            key = keyNestedTestBook,
                            isVisibleNestedBook = isVisibleNestedTestBook,
                            mainViewModel = mainViewModel
                        )
                    }
                }

        }
        AnimatedVisibility(
            visible = isVisibleNestedTestBook.value,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            TestBook(
                key = keyNestedTestBook.value,
                mainViewModel = mainViewModel,
                cancel = {isVisibleNestedTestBook.value = false}
            )
        }
    }
}
@Composable
fun TopMenuListBook(
    title: String,
    appTheme: AppTheme,
    decreasingNum: Animatable<Float, AnimationVector1D>,
    navController: NavController,
) {
    val textSize = 16

    Card(
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        shape = RoundedCornerShape(0)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height((80 + (20 * decreasingNum.value)).dp)
                .background(appTheme.foregroundApp()),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 10.dp)
                    .size(textSize.dp)
                    .clip(CircleShape)
                    .background(
                        appTheme
                            .colorIconApp()
                            .copy(.75f)
                    )
                    .clickable {
                        if (title == "Основные положения по допуску"){
                            navController.navigate("MAIN_SCREEN"){
                                popUpTo("MAIN_SCREEN") { inclusive = true }
                            }
                        } else {
                            navController.navigate("SCREEN_BOOK"){
                                popUpTo("SCREEN_BOOK") { inclusive = true }
                            }
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
                text = title,
                color = appTheme.colorIconApp(),
                style = TextStyle(
                    fontSize = textSize.sp,
                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
            )
        }
    }
}
@Composable
fun ItemScreenListBook(
    item: DataTestBook,
    key: MutableState<String>,
    isVisibleNestedBook: MutableState<Boolean>,
    mainViewModel: MainViewModel
){
    val appTheme = mainViewModel.getAppTheme
    Column(
        modifier = Modifier.padding(bottom = 30.dp)
    ) {
        if (item.title.isNotEmpty()) {
            Text(
                text = item.title,
                style = TextStyle(
                    color = appTheme.colorTextApp(),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                ),
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
        ImageBitmapTestBook(item)
        TextLinkTestBook(
            itemTestBook = item,
            textColor = appTheme.colorTextApp(),
            key = key,
            isVisibleNestedBook = isVisibleNestedBook
        )
    }
}