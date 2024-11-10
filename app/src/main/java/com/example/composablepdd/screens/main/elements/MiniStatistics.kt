package com.example.composablepdd.screens.main.elements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.R
import com.example.composablepdd.ui.theme.ColorStatisticsCard
import kotlin.math.round

@Composable
fun MiniStatistics(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val (questionsProgress, questionsSize, questionsProgressFloat) =
        mainViewModel.getStatistics.getProgressQuestions(lifecycleOwner)
    val (ticketProgress, ticketSize, ticketProgressFloat) =
        mainViewModel.getStatistics.getProgressTicket(lifecycleOwner)
    val (themeProgress, themeSize, themeProgressFloat) =
        mainViewModel.getStatistics.getProgressThemes(lifecycleOwner)

    val total = questionsProgressFloat.value + ticketProgressFloat.value + themeProgressFloat.value
    val circularProgress = (total / 3).coerceIn(0f, 1f)


    Column(
        modifier = Modifier
            .padding(
                top = 15.dp,
                bottom = 10.dp,
                start = 15.dp,
                end = 15.dp,
            )
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(15.dp))
            .background(ColorStatisticsCard)
    ) {
        Text(
            text = "Готовность к сдаче экзамена",
            color = Color.White,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.font_main)),
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 10.dp,
                    bottom = 5.dp
                )
        )
        Row(
            Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .width(120.dp)
                    .padding(start = 30.dp, bottom = 30.dp),
                contentAlignment = Alignment.Center
            ) {
                CustomCircularProgressIndicator(circularProgress)
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "${round(circularProgress * 100).toInt()}",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.font_main_bold)),
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Text(
                        text = "%",
                        color = Color.White.copy(alpha = 0.7f),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.font_main)),
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(end = 30.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxHeight()
                        .padding(end = 10.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ){
                    Text(
                        text = "Решенные\nвопросы",
                        color = Color.White.copy(alpha = 0.7f),
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontFamily = FontFamily(Font(R.font.font_main)),
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(5.dp)
                    )
                    Text(
                        text = "Сданные\nбилеты",
                        color = Color.White.copy(alpha = 0.7f),
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontFamily = FontFamily(Font(R.font.font_main)),
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(5.dp)
                    )
                    Text(
                        text = "Темы без\nошибок",
                        color = Color.White.copy(alpha = 0.7f),
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontFamily = FontFamily(Font(R.font.font_main)),
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(5.dp)
                    )
                }
                Column(
                    Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.35f),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    CustomProgressBar(progress = questionsProgressFloat.value)
                    CustomProgressBar(progress = ticketProgressFloat.value)
                    CustomProgressBar(progress = themeProgressFloat.value)
                }
                Column(
                    Modifier
                        .fillMaxHeight()
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "${questionsProgress.value}",
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.font_main)),
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = " / ${questionsSize.value}",
                            color = Color.White.copy(alpha = 0.7f),
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontFamily = FontFamily(Font(R.font.font_main)),
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "${ticketProgress.value}",
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.font_main)),
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = " / ${ticketSize.value}",
                            color = Color.White.copy(alpha = 0.7f),
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontFamily = FontFamily(Font(R.font.font_main)),
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "${themeProgress.value}",
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.font_main)),
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = " / ${themeSize.value}",
                            color = Color.White.copy(alpha = 0.7f),
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontFamily = FontFamily(Font(R.font.font_main)),
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CustomProgressBar(progress: Float) {
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
            color = ColorStatisticsCard,
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

@Composable
fun CustomCircularProgressIndicator(progress: Float) {
    val radius = 100f
    var circleCenter by remember { mutableStateOf(Offset.Zero) }

    Canvas(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        val width = size.width
        val height = size.height
        circleCenter = Offset(x = width / 2f, y = height / 2f)

        drawCircle(
            style = Stroke(width = 20f),
            color = Color.White.copy(alpha = 0.3f),
            radius = radius,
            center = circleCenter
        )

        val sweepAngle = progress * 360f
        drawArc(
            color = Color.White,
            startAngle = -90f,
            sweepAngle = sweepAngle,
            style = Stroke(
                width = 20f,
                cap = StrokeCap.Round
            ),
            useCenter = false,
            size = Size(
                width = radius * 2f,
                height = radius * 2f
            ),
            topLeft = Offset(
                circleCenter.x - radius,
                circleCenter.y - radius
            )
        )
    }
}



