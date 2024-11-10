package com.example.composablepdd.screens.test.func

import androidx.compose.ui.graphics.Color
import com.example.composablepdd.ui.theme.ColorBottomButtonsActiveExam
import com.example.composablepdd.ui.theme.ColorButtonTadContent
import com.example.composablepdd.ui.theme.ColorButtonTadContent2
import com.example.composablepdd.ui.theme.ColorButtonTadContentCheckFalse
import com.example.composablepdd.ui.theme.ColorButtonTadContentCheckTrue

class ColorElementsTest {

    fun handleButtonClick(
        variantTest: Int?,
        buttonId: Int,
        clickVariant: Int,
        clickTrue: Int,
        tabColor: Color
    ): Color{
        return if (variantTest == 1) {
            buttonColorExam(buttonId, clickVariant, tabColor)
        } else {
            buttonColor(buttonId, clickVariant, clickTrue)
        }
    }
    private fun buttonColor(
        buttonId: Int,
        variantClick: Int,
        variantTrue: Int
    ): Color {
        return when {
            variantClick != variantTrue && variantClick == buttonId -> ColorButtonTadContentCheckFalse
            variantClick == variantTrue && variantClick == buttonId -> ColorButtonTadContentCheckTrue
            buttonId == variantTrue && variantClick != 0 -> ColorButtonTadContentCheckTrue
            variantClick != 0 -> ColorButtonTadContent2
            else -> ColorButtonTadContent
        }
    }

    private fun buttonColorExam(
        buttonId: Int,
        variantClick: Int,
        tabColor: Color
    ): Color {
        return when {
            variantClick == buttonId -> ColorBottomButtonsActiveExam
            else -> tabColor
        }
    }

}
