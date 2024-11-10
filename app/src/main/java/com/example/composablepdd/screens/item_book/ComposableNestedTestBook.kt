package com.example.composablepdd.screens.item_book

import android.os.Build
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.model.DataTestBook
import com.example.composablepdd.screens.item_book.elements.TextLinkTestBook
import com.example.composablepdd.screens.item_book.elements.TitleTestBook

@Composable
fun NestedTestBook(
    key: String,
    mainViewModel: MainViewModel,
    cancel: () -> Unit,
) {

    val appTheme = mainViewModel.getAppTheme
    val supports = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val elevation = if (supports) 0.dp else 10.dp
    val background = if (supports) appTheme.backgroundBlurApp().copy(.4f) else appTheme.colorPopUpWindow().copy(.98f)
    val elementColor = appTheme.backgroundApp()
    val textColor = appTheme.colorTextApp()
    val itemTestBook = mainViewModel.getTestBook.getItemTestBook(key = key) ?: DataTestBook(
        title = "Пусто",
        description = "Похоже разработчики не успели добавить ответ на ссылку\n\nПозже ответ будет готов"
    )
    val isVisibleNestedTestBook = remember { mutableStateOf(false) }
    val keyNestedTestBook = remember { mutableStateOf("") }
    val titleSize = remember { Animatable(24f) }
    LaunchedEffect(isVisibleNestedTestBook.value) {
        titleSize.animateTo(if (isVisibleNestedTestBook.value) 18f else 24f)
    }
    val listState = rememberLazyListState()
    Card(
        modifier = Modifier
            .fillMaxSize()
            .border(.3.dp, textColor.copy(.3f), RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)),
        colors = CardDefaults.cardColors(background),
        elevation = CardDefaults.elevatedCardElevation(elevation),
        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
    ) {
        Column(
            Modifier.fillMaxSize()
        ) {
            TitleTestBook(
                title = itemTestBook.title,
                textSize = titleSize,
                textColor = appTheme.colorTextApp(),
                elementColor = elementColor,
                cancel = { cancel() },
                onClickTitle = { isVisibleNestedTestBook.value = false }
            )
            Box {
                if (!isVisibleNestedTestBook.value) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp)
                            .height(1.dp)
                            .background(elementColor)
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
                        state = listState
                    ) {
                        item {
                            ImageBitmapTestBook(itemTestBook)
                        }
                        item {
                            TextLinkTestBook(
                                itemTestBook = itemTestBook,
                                textColor = textColor,
                                key = keyNestedTestBook,
                                isVisibleNestedBook = isVisibleNestedTestBook
                            )
                        }
                    }
                }
                androidx.compose.animation.AnimatedVisibility(
                    visible = isVisibleNestedTestBook.value,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it })
                ) {
                    NestedTestBook(
                        key = keyNestedTestBook.value,
                        mainViewModel = mainViewModel,
                        cancel = { isVisibleNestedTestBook.value = false },
                    )
                }
            }
        }
    }
}