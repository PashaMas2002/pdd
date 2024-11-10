package com.example.composablepdd.screens.item_book

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.model.DataTestBook
import com.example.composablepdd.screens.item_book.elements.TextLinkTestBook
import com.example.composablepdd.screens.item_book.elements.TitleTestBook
import java.io.IOException

@Composable
fun TestBook(
    key: String,
    mainViewModel: MainViewModel,
    cancel: () -> Unit
) {
    val appTheme = mainViewModel.getAppTheme
    val supports = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val elevation = if (supports) 0.dp else 10.dp
    val background = if (supports) appTheme.backgroundBlurApp().copy(.4f) else appTheme.colorPopUpWindow().copy(.98f)
    val elementColor = appTheme.backgroundApp()
    val itemTestBook = mainViewModel.getTestBook.getItemTestBook(key = key) ?: DataTestBook(
        title = "Пусто",
        description = "Похоже разработчики не успели добавить ответ на ссылку\n\nПозже ответ будет готов"
    )
    val isVisibleNestedTestBook = remember { mutableStateOf(false) }
    val keyNestedTestBook = remember { mutableStateOf("") }
    val titleSize = remember { Animatable(24f) }
    LaunchedEffect(isVisibleNestedTestBook.value) {
        titleSize.animateTo(if (isVisibleNestedTestBook.value) 18f else 24f) // Анимация до нового значения
    }
    val listState = rememberLazyListState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.95f)
            .border(.3.dp, appTheme.colorTextApp().copy(.3f), RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)),
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
                                textColor = appTheme.colorTextApp(),
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

@Composable
fun ImageBitmapTestBook(
    itemTestBook: DataTestBook,
) {
    val image = itemTestBook.listImageBitmap

    if (image.isNotEmpty()) {
        LazyRow(
            Modifier.height(itemTestBook.imageHeight.dp)
        ) {
            items(image) { item ->
                val bitmap = getBitmapBook(item)
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight(),
                        contentScale = ContentScale.FillHeight
                    )
                }
            }
        }
    }
}

@Composable
fun getBitmapBook(
    resStr: String,
    mainViewModel: MainViewModel = hiltViewModel()
): Bitmap? {
    return remember(resStr) {
        val appContext = mainViewModel.applicationContext
        val assetManager = appContext.assets

        fun loadBitmapFromAssets(resourceName: String): Bitmap? {
            try {
                val inputStream =
                    assetManager.open("images_book/$resourceName.png")
                return BitmapFactory.decodeStream(inputStream)
            } catch (e: IOException) {
                Log.d("MyLog", "IOException: $e")
                return null
            }
        }
        loadBitmapFromAssets(resStr)
    }
}

