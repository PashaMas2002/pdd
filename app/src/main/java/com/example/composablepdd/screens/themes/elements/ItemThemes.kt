package com.example.composablepdd.screens.themes.elements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.R
import com.example.composablepdd.database.entities.ItemThemes
import com.example.composablepdd.ui.theme.ColorLightBlue
import com.example.composablepdd.ui.theme.ColorCardItemThemesItemReady

@Composable
fun ItemThemes(
    index: Int,
    item: ItemThemes,
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val (progressCurrent, progressSize, progressFloat) =
        mainViewModel.getTableThemes.getProgressItemThemes(index = index)
    val isReady = progressFloat.value == 1f
    val backgroundColor = if (isReady) ColorCardItemThemesItemReady else ColorLightBlue

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 10.dp,
                top = 5.dp,
            ),
        elevation = CardDefaults.cardElevation(3.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(backgroundColor),
        onClick = {
            mainViewModel.getDataBaseForTest.numList = (index + 1)
            mainViewModel.getDataBaseForTest.variantList.value = 3
            navController.navigate("PAGER_SCREEN")
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 10.dp, end = 15.dp, bottom = 10.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = item.id.toString(),
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontFamily = FontFamily(Font(R.font.font_main_bold)),
                        )
                    )
                    Column(Modifier.padding(bottom = 3.dp)) {
                        Text(
                            text = "№",
                            color = Color.White.copy(0.3f),
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontFamily = FontFamily(Font(R.font.font_main_bold)),
                            )
                        )
                        Text(
                            text = "Тема",
                            color = Color.White.copy(0.3f),
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.font_main_bold)),
                            )
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.35f)
                        .padding(start = 30.dp, end = 10.dp)
                ) {
                    CustomProgressBarItemThemes(progressFloat.value, backgroundColor)
                }
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "${progressCurrent.value}",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.font_main_bold)),
                        )
                    )
                    Text(
                        text = " / ${progressSize.value}",
                        color = Color.White.copy(alpha = 0.7f),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.font_main_bold)),
                        )
                    )
                }
            }
            Text(
                text = item.name,
                color = Color.White,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                )
            )

        }
    }
}

@Composable
fun CustomProgressBarItemThemes(progress: Float, color: Color) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
    ) {
        val strokeWidth = 16f
        val padding = 4f

        drawRoundRect(
            color = Color.White.copy(alpha = 0.3f),
            topLeft = Offset(0f, size.height / 2 - strokeWidth / 2),
            size = size.copy(height = strokeWidth),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(10f, 10f)
        )
        drawRoundRect(
            color = Color.White,
            topLeft = Offset(0f, size.height / 2 - strokeWidth / 2),
            size = size.copy(width = size.width * progress, height = strokeWidth),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(10f, 10f)
        )
        drawCircle(
            color = color,
            radius = strokeWidth / 1.6f + padding,
            center = Offset(size.width * progress, size.height / 2)
        )
        drawCircle(
            color = Color.White,
            radius = strokeWidth / 3 + padding,
            center = Offset(size.width * progress, size.height / 2)
        )
    }
}