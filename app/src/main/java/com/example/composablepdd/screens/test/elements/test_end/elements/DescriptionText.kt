package com.example.composablepdd.screens.test.elements.test_end.elements

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
fun TheEndTestDescription(
    text: String,
    color: Color
){
    if (text.isNotEmpty()) {
        Text(
            text = text,
            color = color,
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.font_main_bold)),
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )
    }
}