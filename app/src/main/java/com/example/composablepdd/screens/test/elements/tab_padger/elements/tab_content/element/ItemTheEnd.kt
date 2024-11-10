package com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.composablepdd.database.entities.ItemQuestions
import com.example.composablepdd.ui.myTheme.AppTheme
import com.example.composablepdd.ui.theme.ColorLightGreen
import com.example.composablepdd.ui.theme.ColorLightRed

@Composable
fun ItemTheEnd(
    appTheme: AppTheme,
    item: ItemQuestions,
    texts: List<String?>
){
    Column(Modifier.padding(bottom = 10.dp)) {
        Text(
            text = "Ваш ответ:",
            color = appTheme.colorTextApp(),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.font_main)),
            ),
            modifier = Modifier
                .padding(
                    20.dp
                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .background(
                    color = ColorLightRed,
                    shape = RoundedCornerShape(10.dp)
                )
        ){
            Text(
                text = if (item.variantClick != null){ texts[item.variantClick!! - 1].toString() } else "",
                color = Color.White,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.font_main)),
                ),
                modifier = Modifier
                    .padding(
                        10.dp
                    )
            )
        }
        Text(
            text = "Верный ответ:",
            color = appTheme.colorTextApp(),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.font_main)),
            ),
            modifier = Modifier
                .padding(
                    20.dp
                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .background(
                    color = ColorLightGreen,
                    shape = RoundedCornerShape(10.dp)
                )
        ){
            Text(
                text = texts[item.clickTrue - 1].toString(),
                color = Color.White,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.font_main)),
                ),
                modifier = Modifier
                    .padding(
                        10.dp
                    )
            )
        }
    }
}