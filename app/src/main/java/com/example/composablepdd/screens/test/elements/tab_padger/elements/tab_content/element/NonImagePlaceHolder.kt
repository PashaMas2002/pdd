package com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composablepdd.R
import com.example.composablepdd.ui.theme.ColorTabAndProgressLine

@Composable
fun NonImagePlaceholder() {
    Row(
        modifier = Modifier
            .padding(bottom = 25.dp, top = 20.dp, start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .size(20.dp, 1.dp)
                .background(ColorTabAndProgressLine.copy(alpha = 0.6f))
        )
        Text(
            text = "Вопрос без изображения",
            color = ColorTabAndProgressLine.copy(alpha = 0.6f),
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.font_main)),
            ),
            modifier = Modifier
                .padding(bottom = 5.dp, start = 5.dp, end = 10.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(ColorTabAndProgressLine.copy(alpha = 0.7f))
        )
    }
}