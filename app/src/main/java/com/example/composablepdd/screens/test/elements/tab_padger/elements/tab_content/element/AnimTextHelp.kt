package com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.element

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composablepdd.R
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.ui.theme.TextLinkColor
import com.example.composablepdd.ui.myTheme.AppTheme
import com.example.composablepdd.ui.theme.TextBlue
import com.example.composablepdd.ui.theme.TextGreen

@Composable
fun AnimTextHelp(
    index: Int,
    appTheme: AppTheme,
    isVisibleTextHelp: MutableState<Boolean>,
    isMistakesScreen: MutableState<Boolean>,
    text: String,
    isVisibleBook: MutableState<Boolean>,
    keyResLinks: MutableState<String>,
    mainViewModel: MainViewModel
){
    val links = mainViewModel.getResLinks.getLinks(19)
    val annotatedString2 = buildAnnotatedString {
        var startIndex = 0

        links.forEach { (str, linkText) ->
            val linkStartIndex = text.indexOf(linkText, startIndex)
            if (linkStartIndex != -1) {
                append(text.substring(startIndex, linkStartIndex))
                pushStringAnnotation(tag = "TAG", annotation = str)
                withStyle(
                    style = SpanStyle(
                        color = TextLinkColor,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(linkText)
                }
                pop()
                startIndex = linkStartIndex + linkText.length
            }
        }
        append(text.substring(startIndex))
    }
    val annotatedString = buildAnnotatedString {
        var startIndex = 0

        val listBold = listOf("Правильный ответ: 1", "Правильный ответ: 2","Правильный ответ: 3","Правильный ответ: 4", "Правильный ответ: 5")
        val listBlue = listOf("«", "»")
        val listGreen = listOf("(", ")")

        val searchItems = listBold + listBlue + listGreen + links.map { it.second }

        while (startIndex < text.length) {
            val nextIndices = searchItems.map { text.indexOf(it, startIndex) }.filter { it != -1 }.toList()
            val nextIndex = nextIndices.minOrNull()

            if (nextIndex == null) {
                append(text.substring(startIndex))
                break
            }
            append(text.substring(startIndex, nextIndex))

            if (nextIndices.contains(nextIndex)) {
                val foundText = searchItems.first { text.indexOf(it, startIndex) == nextIndex }

                when {
                    foundText in listBold && text.contains(foundText) -> {
                        withStyle(SpanStyle(fontFamily = FontFamily(Font(R.font.font_main_bold)), baselineShift = BaselineShift.Superscript)) {
                            append(foundText)
                        }
                    }
                    foundText in listBlue && text.contains(foundText) -> {
                        withStyle(SpanStyle(color = TextBlue, fontFamily = FontFamily(Font(R.font.font_main_bold)))) {
                            append(foundText)
                        }
                    }
                    foundText in listGreen && text.contains(foundText) -> {
                        withStyle(SpanStyle(color = TextGreen, fontFamily = FontFamily(Font(R.font.font_main_bold)))) {
                            append(foundText)
                        }
                    }
                    links.any { it.second == foundText } -> {
                        val (annotation, linkText) = links.first { it.second == foundText }
                        pushStringAnnotation(tag = "TAG", annotation = annotation)
                        withStyle(SpanStyle(color = TextLinkColor, fontFamily = FontFamily(Font(R.font.font_main)))) {
                            append(linkText)
                        }
                        pop()
                    }
                }
                startIndex = nextIndex + foundText.length
            }
        }
    }
    AnimatedVisibility(
        visible = isVisibleTextHelp.value || isMistakesScreen.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        ClickableText(
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "TAG", start = offset, end = offset)
                    .firstOrNull()?.let { annotation ->
                        keyResLinks.value = annotation.item
                        isVisibleBook.value = true
                    }
            },
            style = TextStyle(
                color = appTheme.colorTextApp(),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.font_main)),
            ),
            modifier = Modifier
                .padding(
                    bottom = 100.dp,
                    top = 10.dp,
                    start = 20.dp,
                    end = 20.dp
                )
        )
    }
}