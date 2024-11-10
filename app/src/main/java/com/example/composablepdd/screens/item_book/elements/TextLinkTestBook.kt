package com.example.composablepdd.screens.item_book.elements

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.composablepdd.R
import com.example.composablepdd.model.DataTestBook
import com.example.composablepdd.ui.theme.TextLinkColor

@Composable
fun TextLinkTestBook(
    itemTestBook: DataTestBook,
    textColor: Color,
    key: MutableState<String>,
    isVisibleNestedBook: MutableState<Boolean>
){
    val annotatedString = buildAnnotatedString {
        var startIndex = 0
        val description = itemTestBook.description

        while (startIndex < description.length) {
            val redTextIndex = itemTestBook.listRedColor.map { description.indexOf(it, startIndex) }.filter { it != -1 }.minOrNull()
            val boldTextIndex = itemTestBook.listBold.map { description.indexOf(it, startIndex) }.filter { it != -1 }.minOrNull()
            val linkTextIndex = itemTestBook.listLinks.map { description.indexOf(it.second, startIndex) }.filter { it != -1 }.minOrNull()

            val nextIndex = listOfNotNull(redTextIndex, boldTextIndex, linkTextIndex).filter { it != -1 }.minOrNull()

            if (nextIndex == null) {
                append(description.substring(startIndex))
                break
            }
            append(description.substring(startIndex, nextIndex))

            when {
                nextIndex == redTextIndex -> {
                    val redText = itemTestBook.listRedColor.first { description.indexOf(it, startIndex) == redTextIndex }
                    withStyle(SpanStyle(color = Color.Red, fontFamily = FontFamily(Font(R.font.font_main)))) {
                        append(redText)
                    }
                    startIndex = redTextIndex + redText.length
                }
                nextIndex == boldTextIndex -> {
                    val boldText = itemTestBook.listBold.first { description.indexOf(it, startIndex) == boldTextIndex }
                    withStyle(SpanStyle(fontFamily = FontFamily(Font(R.font.font_main_bold)))) {
                        append(boldText)
                    }
                    startIndex = boldTextIndex + boldText.length
                }
                nextIndex == linkTextIndex -> {
                    val (annotation, linkText) = itemTestBook.listLinks.first { description.indexOf(it.second, startIndex) == linkTextIndex }
                    pushStringAnnotation(tag = "TAG", annotation = annotation)
                    withStyle(SpanStyle(color = TextLinkColor, fontFamily = FontFamily(Font(R.font.font_main)))) {
                        append(linkText)
                    }
                    pop()
                    startIndex = linkTextIndex + linkText.length
                }
            }
        }
    }


    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "TAG", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    key.value = annotation.item
                    isVisibleNestedBook.value = true
                }
        },
        style = TextStyle(
            color = textColor,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.font_main)),
        ),
    )
}