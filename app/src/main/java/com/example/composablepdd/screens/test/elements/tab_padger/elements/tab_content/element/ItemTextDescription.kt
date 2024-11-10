package com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.element

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composablepdd.R

@Composable
fun ItemTextDescription(
    text: String,
    color: Color
    ){
    Text(
        text = text,
        color = color,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.font_main)),
        ),
        modifier = Modifier
            .padding(bottom = 25.dp, start = 20.dp, end = 20.dp)
    )
}