package com.example.composablepdd.screens.test.elements.test_end.elements

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composablepdd.R
import com.example.composablepdd.ui.theme.ColorButtonMarathonPreview
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@Composable
fun TheEndTestButtons(
    isVisible: Boolean,
    pagerState: PagerState,
    color: Color,
    onClickMistakes: () -> Unit,
    onClickExit: () -> Unit,
){
    val scope = rememberCoroutineScope()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isVisible) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(.6f)
                    .height(46.dp)
                    .padding(bottom = 1.dp)
                    .animateContentSize(),
                colors = CardDefaults.cardColors(Color.White.copy(0.2f)),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(0)
                    }
                    onClickMistakes()
                },
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.3.dp)
                        .background(
                            shape = RoundedCornerShape(15.dp),
                            color = ColorButtonMarathonPreview
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Ошибки",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.font_main_bold)),
                        )
                    )
                }
            }
        }
        Text(
            text = "Завершить",
            color = color,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.font_main_bold)),
            ),
            modifier = Modifier
                .padding(top = 25.dp)
                .clickable {
                    onClickExit()
                }
        )
    }
}