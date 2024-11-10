package com.example.composablepdd.screens.book.NameBook.elements

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composablepdd.R
import com.example.composablepdd.ui.myTheme.AppTheme

@Composable
fun TopMenuNameBook(
    title: String,
    appTheme: AppTheme,
    decreasingNum: Animatable<Float, AnimationVector1D>,
    navController: NavController
) {
    val textSize = 16

    Card(
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        shape = RoundedCornerShape(0)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height((80 + (20 * decreasingNum.value)).dp)
                .background(appTheme.foregroundApp()),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 10.dp)
                    .size(textSize.dp)
                    .clip(CircleShape)
                    .background(
                        appTheme
                            .colorIconApp()
                            .copy(.75f)
                    )
                    .clickable {
                        navController.navigate("MAIN_SCREEN") {
                            popUpTo("MAIN_SCREEN") { inclusive = true }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_exit),
                    contentDescription = null,
                    tint = appTheme.foregroundApp(),
                    modifier = Modifier.fillMaxSize(.8f)
                )
            }
            Text(
                text = title,
                color = appTheme.colorIconApp(),
                style = TextStyle(
                    fontSize = textSize.sp,
                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
            )
        }
    }
}