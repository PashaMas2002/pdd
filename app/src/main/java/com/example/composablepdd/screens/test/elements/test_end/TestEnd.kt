package com.example.composablepdd.screens.test.elements.test_end

import android.os.Build
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.database.entities.ItemQuestions
import com.example.composablepdd.screens.test.elements.test_end.elements.CustomProgressBarTheEnd
import com.example.composablepdd.screens.test.elements.test_end.elements.TheEndButtonUpdateResult
import com.example.composablepdd.screens.test.elements.test_end.elements.TheEndProgressText
import com.example.composablepdd.screens.test.elements.test_end.elements.TheEndTestButtons
import com.example.composablepdd.screens.test.elements.test_end.elements.TheEndTestDescription
import com.example.composablepdd.screens.test.elements.test_end.elements.TheEndTestTitle
import com.google.accompanist.pager.PagerState

@Composable
fun TestEnd(
    listClickVariant: SnapshotStateList<MutableState<Int>>,
    listTabPager: MutableList<MutableState<ItemQuestions>>,
    isSizeDropExamList: MutableState<Int>,
    timeUs: MutableState<Boolean>,
    mainViewModel: MainViewModel,
    pagerState: PagerState,
    onClickMistakes: () -> Unit,
    onClickExit: () -> Unit
) {
    val variantTest = mainViewModel.getDataBaseForTest.variantList.value
    val drop = remember(isSizeDropExamList.value) {
        if (variantTest == 1) {
            when (isSizeDropExamList.value) {
                1 -> 5
                2 -> 0
                else -> 10
            }
        } else 0
    }
    val progress = listTabPager.filterIndexed { index, item ->
        listClickVariant[index].value == item.value.clickTrue
    }.size
    val progressMax = listTabPager.drop(drop).size
    val progressMistakes = progressMax - progress
    val appTheme = mainViewModel.getAppTheme
    val supports = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val background = if (supports) Color.DarkGray.copy(.4f) else appTheme.colorPopUpWindow().copy(.98f)
    val elevation = if (supports) 0.dp else 10.dp
    val textColor = if (supports) Color.White else appTheme.colorTextApp()
    val progressColor = Color.White



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.95f)
            .border(.3.dp, appTheme.colorTextApp().copy(.3f), RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)),
        colors = CardDefaults.cardColors(background),
        elevation = CardDefaults.elevatedCardElevation(elevation),
        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 150.dp, top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TheEndTestTitle(
                textColor = textColor
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TheEndProgressText(
                    progress = progress,
                    progressMistakes = progressMistakes,
                    progressMax = progressMax,
                    color = textColor
                )
                CustomProgressBarTheEnd(
                    progress / progressMax.toFloat(),
                    1f,
                    color = progressColor
                )
                TheEndTestDescription(
                    text = getDescText(
                        variantTest = variantTest,
                        progressPercentage = progress.toFloat() / progressMax,
                        progressMistakes = progressMistakes,
                        timeUs = timeUs
                    ),
                    color = textColor
                )
                if (variantTest in listOf(2, 3) && progressMistakes == 1) {
                    TheEndButtonUpdateResult {
                        updateDatabaseValue(variantTest, mainViewModel, listTabPager.size)
                    }
                }
            }
            TheEndTestButtons(
                isVisible = progressMistakes != 0,
                pagerState = pagerState,
                color = textColor,
                onClickMistakes = { onClickMistakes() },
                onClickExit = { onClickExit() },
            )
        }
    }
}

private fun updateDatabaseValue(variantTest: Int?, mainViewModel: MainViewModel, value: Int) {
    val id = mainViewModel.getDataBaseForTest.numList
    if (variantTest == 2) {
        mainViewModel.getDataBaseForTest.updateValueByIdItemTicket(
            id = id,
            value = value,
            mistakes = 0
        )
    } else {
        mainViewModel.getDataBaseForTest.updateValueByIdItemThemes(
            id = id,
            value = value
        )
    }
}

private fun getDescText(
    variantTest: Int?,
    progressPercentage: Float,
    progressMistakes: Int,
    timeUs: MutableState<Boolean>
): String {
    return when (variantTest) {
        1 -> {
            if (timeUs.value) {
                "Время вышло"
            } else {
                when {
                    progressMistakes <= 2 -> "Экзамен сдан"
                    progressMistakes < 8 -> "Экзамен не сдан"
                    else -> "Полный провал"
                }
            }
        }

        2 -> {
            when {
                progressMistakes == 0 -> "Отличный результат"
                progressMistakes <= 2 -> "Хороший результат"
                else -> ""
            }
        }

        3 -> {
            when {
                progressPercentage >= 0.95f -> "Отлично"
                progressPercentage >= 0.9f -> "Хороший результат"
                progressPercentage >= 0.85f -> "Достаточно неплохо"
                else -> ""
            }
        }

        4 -> {
            when {
                progressPercentage >= 0.92f -> "Отлично"
                progressPercentage >= 0.88f -> "Хороший результат"
                progressPercentage >= 0.78f -> "Достаточно неплохо"
                else -> "Плохой результат - тоже результат"
            }
        }

        5 -> {
            if (progressMistakes == 0) "Отлично,\nошибок больше нет" else ""
        }

        else -> ""
    }
}


