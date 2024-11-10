package com.example.composablepdd.screens.test.elements.test_end.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.R
import com.example.composablepdd.ui.theme.ColorLightRed

@Composable
fun TheEndProgressText(
    progress: Int,
    progressMistakes: Int,
    progressMax: Int,
    color: Color,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val variantTest = mainViewModel.getDataBaseForTest.variantList.value
    if (variantTest != 5) {
        Row(
            Modifier.padding(start = 20.dp, bottom = 10.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "$progress",
                color = color,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                ),
            )
            if (progressMistakes != 0) {
                Text(
                    text = " / ",
                    color = color.copy(0.6f),
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.font_main_bold)),
                    ),
                )
                Text(
                    text = "$progressMistakes",
                    color = ColorLightRed,
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.font_main_bold)),
                    ),
                )
            }
            Text(
                text = " / $progressMax",
                color = color.copy(alpha = 0.6f),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                ),
            )
        }
    } else {
        Text(
            text = "$progressMistakes",
            color = ColorLightRed,
            style = TextStyle(
                fontSize = 36.sp,
                fontFamily = FontFamily(Font(R.font.font_main_bold)),
            ),
        )
    }
}