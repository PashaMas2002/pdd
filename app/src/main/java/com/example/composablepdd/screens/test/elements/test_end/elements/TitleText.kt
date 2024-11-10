package com.example.composablepdd.screens.test.elements.test_end.elements

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.composablepdd.R

@Composable
fun TheEndTestTitle(
    textColor: Color
){
    Text(
        text = "Результат",
        color = textColor,
        style = TextStyle(
            fontSize = 26.sp,
            fontFamily = FontFamily(Font(R.font.font_main_bold)),
        )

    )
}