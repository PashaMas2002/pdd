package com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.element

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composablepdd.R
import com.example.composablepdd.ui.theme.ColorBottomButtons

@Composable
fun BottomButtonsTabContent(
    color: Color,
    isVisible: MutableState<Boolean>,
    isVisibleIcon: MutableState<Boolean>,
    onClickIcon: () -> Unit,
    onClickCard: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 70.dp),
        horizontalAlignment = Alignment.End
    ) {
        if (!isVisibleIcon.value) {
            Icon(
                painter = painterResource(id = R.drawable.ic_question),
                tint = color.copy(0.8f),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 20.dp)
                    .clickable(
                        onClick = { onClickIcon() }
                    )
            )
        }
        AnimatedVisibility(
            visible = isVisible.value,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                elevation = CardDefaults.elevatedCardElevation(10.dp),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(ColorBottomButtons),
                onClick = {
                    onClickCard()
                },
            ) {
                Text(
                    text = "Далее ->",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.font_main)),
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 15.dp, top = 15.dp)
                )
            }
        }
    }
}