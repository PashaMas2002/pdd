package com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.element

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.example.composablepdd.ui.myTheme.AppTheme
import com.example.composablepdd.ui.theme.ColorButtonTadContentIsCheck

@Composable
fun ButtonFavorites(
    appTheme: AppTheme,
    isChecked: Boolean,
    onClick: () -> Unit,
    variantTest: Int?
) {

    val color = if (variantTest != 1) {
        ColorButtonTadContentIsCheck
    } else appTheme.colorTextApp().copy(alpha = 0.7f)
    Row(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
            .clickable(
                onClick = { onClick() },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            AnimatedIcon(
                isChecked,
                color
            )
        }
        AnimatedContent(
            targetState = isChecked,
        ) { checked ->
            Text(
                text = if (checked) "Убрать из избранных" else "Добавить в избранные",
                color = color,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                ),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            )
        }
    }
}


@Composable
fun AnimatedIcon(
    isChecked: Boolean,
    color: Color
) {

    AnimatedVisibility(
        visible = isChecked,
        enter = scaleIn(initialScale = 0.8f) + fadeIn(),
        exit = scaleOut(targetScale = 0.8f) + fadeOut()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_mark),
            contentDescription = null,
            tint = ColorButtonTadContentIsCheck,
        )
    }

    AnimatedVisibility(
        visible = !isChecked,
        enter = scaleIn(initialScale = 0.8f) + fadeIn(),
        exit = scaleOut(targetScale = 0.8f) + fadeOut()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_not_bookmark),
            contentDescription = null,
            tint = color,
        )
    }
}