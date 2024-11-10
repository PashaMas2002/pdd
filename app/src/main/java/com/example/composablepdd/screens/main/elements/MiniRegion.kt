package com.example.composablepdd.screens.main.elements

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.R
import com.example.composablepdd.ui.myTheme.AppTheme
import com.example.composablepdd.ui.theme.ColorRegionCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.InputStream

@Composable
fun ButtonRegion(
    appTheme: AppTheme,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 10.dp,
                bottom = 10.dp
            )
    ) {
        Column {
            Text(
                text = "Регионы",
                color = appTheme.colorTextApp(),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 30.dp,
                        end = 30.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("REGION_SCREEN")
                    },
            ) {
                InfiniteSmoothScrollingLazyRow()
            }
        }
    }
}

@Composable
fun InfiniteSmoothScrollingLazyRow(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            for (i in 0 until mainViewModel.resourceRegion.listNameRegionMini.size) {

                coroutineScope.launch {
                    lazyListState.animateScrollToItem(i)
                }
                delay(2000)
            }
        }
    }

    LazyRow(state = lazyListState) {
        itemsIndexed(mainViewModel.resourceRegion.listNameRegionMini) { index, _ ->
            ItemInfiniteSmoothScrollingLazyRow(index)
        }
    }
}


@Composable
fun ItemInfiniteSmoothScrollingLazyRow(
    index: Int,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val listRandom = (0..<mainViewModel.resourceRegion.listNameRegionMini.size).toList().shuffled()

    val text = mainViewModel.resourceRegion.listNameRegionMini[listRandom[index]]

    val appContext = mainViewModel.applicationContext
    val assetManager = appContext.assets
    fun assetsOpen(resImageName: String): InputStream {
        return assetManager.open("path_to_image/$resImageName.png")
    }

    val resourceName = mainViewModel.resourceRegion.listIc1[listRandom[index]]
    val inputStream = assetsOpen(resourceName)
    val bitmap = BitmapFactory.decodeStream(inputStream)

    Card(
        modifier = Modifier
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = ColorRegionCard),
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            bitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(55.dp)
                        .padding(8.dp)
                        .shadow(elevation = 3.dp, shape = RoundedCornerShape(8.dp))
                )
            }
            Text(
                text = text,
                color = Color.White,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.font_main)),
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(bottom = 5.dp, start = 5.dp, end = 10.dp)
            )
        }
    }
}





