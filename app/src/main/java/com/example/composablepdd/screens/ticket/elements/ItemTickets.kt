package com.example.composablepdd.screens.ticket.elements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.composablepdd.database.entities.ItemTicket
import com.example.composablepdd.ui.theme.ColorLightBlue
import com.example.composablepdd.ui.theme.ColorLightRed

@Composable
fun ItemTickets(
    index: Int,
    item: ItemTicket,
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val (progressCurrent, progressMistake, progressFloat) =
        mainViewModel.getTableTicket.getProgressItemTicket(index = index)

    val progressMistakes = progressMistake.value.toFloat() / 20
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.2f)
            .padding(
                start = 5.dp,
                end = 5.dp,
                top = 5.dp,
                bottom = 10.dp
            ),
        onClick = {
            mainViewModel.getDataBaseForTest.numList = (index + 1)
            mainViewModel.getDataBaseForTest.variantList.value = 2
            navController.navigate("PAGER_SCREEN")
        },
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(ColorLightBlue),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp, start = 10.dp, bottom = 5.dp, end = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = item.numberList.toString(),
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
                        text = "Билет",
                        color = Color.White.copy(0.3f),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.font_main_bold)),
                        )
                    )
                }
            }
            CustomProgressBarItemTicket(progressFloat.value, progressMistakes)
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "${progressCurrent.value}",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.font_main_bold)),
                    )
                )
                if(progressCurrent.value != 20) {
                    Text(
                        text = " / ",
                        color = Color.White.copy(alpha = 0.7f),
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.font_main_bold)),
                        )
                    )
                    Text(
                        text = "${progressMistake.value}",
                        color =
                        if (progressMistake.value == 0)
                            Color.White.copy(alpha = 0.7f)
                        else
                            ColorLightRed,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.font_main_bold)),
                        )
                    )
                }
                Text(
                    text = " / 20",
                    color = Color.White.copy(alpha = 0.7f),
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.font_main_bold)),
                    )
                )
            }
        }
    }
}

@Composable
fun CustomProgressBarItemTicket(progress1: Float, progress2: Float) {
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
            color = ColorLightRed,
            topLeft = Offset(0f, size.height / 2 - strokeWidth / 2),
            size = size.copy(width = size.width * (progress1 + progress2), height = strokeWidth),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(10f, 10f)
        )
        drawRoundRect(
            color = Color.White,
            topLeft = Offset(0f, size.height / 2 - strokeWidth / 2),
            size = size.copy(width = size.width * progress1, height = strokeWidth),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(10f, 10f)
        )
        drawCircle(
            color = ColorLightBlue,
            radius = strokeWidth / 1.6f + padding,
            center = Offset(size.width * progress1, size.height / 2)
        )
        drawCircle(
            color = Color.White,
            radius = strokeWidth / 3 + padding,
            center = Offset(size.width * progress1, size.height / 2)
        )
    }
}