package com.example.composablepdd.screens.test.elements.tab_padger.elements

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.R
import com.example.composablepdd.database.entities.ItemQuestions
import com.example.composablepdd.ui.theme.ColorBottomButtonsActiveExam
import com.example.composablepdd.ui.theme.ColorButtonTadContent
import com.example.composablepdd.ui.theme.ColorButtonTadContentCheckFalse
import com.example.composablepdd.ui.theme.ColorButtonTadContentCheckTrue
import com.example.composablepdd.ui.theme.ColorButtonTadContentIsCheck
import com.example.composablepdd.ui.theme.ColorLightRed
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MyTabsRow(
    lineColor: Color,
    variantClickList: SnapshotStateList<MutableState<Int>>,
    pagerState: PagerState,
    tabPagerList: MutableList<MutableState<ItemQuestions>>,
    isSizeDropExamList: MutableState<Int>,
    isMistakesScreen: MutableState<Boolean>,
    mainViewModel: MainViewModel
) {
    val variantTest = mainViewModel.getDataBaseForTest.variantList
    val coroutineScope = rememberCoroutineScope()
    Log.d("MyLog", "${isSizeDropExamList.value}")
    val drop = remember(isSizeDropExamList.value, tabPagerList) {
        if (variantTest.value == 1 && tabPagerList.isNotEmpty() ) {
            when (isSizeDropExamList.value) {
                1 -> 5
                2 -> 0
                else -> 10
            }
        } else 0
    }
    val result = if (variantTest.value == 1) {
        if (variantClickList.count { it.value != 0 } >= 20) { drop } else { 10 } } else { 0 }
    val filteredTabs = tabPagerList.dropLast(result).filterIndexed { id, item ->
        !isMistakesScreen.value || (item.value.clickTrue != variantClickList[id].value)
    }
    if (variantTest.value == 4) {
        TabsLazyRow(
            lineColor = lineColor,
            pagerState = pagerState,
            tabItems = filteredTabs,
            variantTest = variantTest,
            variantClickList = variantClickList,
            isMistakesScreen = isMistakesScreen,
        )
    } else {
        MyScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { },
            divider = {},
            minItemWidth = 0.dp,
            backgroundColor = Color.Transparent,
            contentColor = Color.Transparent,
            edgePadding = 10.dp
        ) {
            filteredTabs
                .forEachIndexed { index, item ->
                    MyTab(
                        index = index,
                        lineColor = lineColor,
                        item = item,
                        variantTest = variantTest.value,
                        variantClickList = variantClickList,
                        pagerState = pagerState,
                        isMistakesScreen = isMistakesScreen,
                        coroutineScope = coroutineScope
                    )
                }
        }
    }
}

@Composable
fun TabsLazyRow(
    lineColor: Color,
    pagerState: PagerState,
    tabItems: List<MutableState<ItemQuestions>>,
    variantTest: MutableState<Int?>,
    variantClickList: SnapshotStateList<MutableState<Int>>,
    isMistakesScreen: MutableState<Boolean>
) {
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()

    LaunchedEffect(pagerState.currentPage) {
        lazyListState.animateScrollToItem(pagerState.currentPage)
    }

    LazyRow(
        contentPadding = PaddingValues(10.dp),
        state = lazyListState
    ) {
        itemsIndexed(tabItems) { index, item ->
            MyTab(
                index = index,
                lineColor = lineColor,
                item = item,
                variantTest = variantTest.value,
                variantClickList = variantClickList,
                pagerState = pagerState,
                isMistakesScreen = isMistakesScreen,
                coroutineScope = coroutineScope,
            )
        }
    }
}

@Composable
fun MyTab(
    index: Int,
    lineColor: Color,
    item: MutableState<ItemQuestions>,
    variantTest: Int?,
    variantClickList: SnapshotStateList<MutableState<Int>>,
    pagerState: PagerState,
    isMistakesScreen: MutableState<Boolean>,
    coroutineScope: CoroutineScope,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val tabColor = mainViewModel.getAppTheme.colorTabExam()
    val isSelected = pagerState.currentPage == index
    val appTheme = mainViewModel.getAppTheme
    val animatedColor by animateColorAsState(
        targetValue = when {
            variantClickList.isEmpty() -> Color.DarkGray
            isMistakesScreen.value -> ColorLightRed
            variantTest == 1 -> tabColorExam(variantClickList[index].value, tabColor)
            else -> tabColor(variantClickList[index].value, item.value.clickTrue)
        },
        animationSpec = tween(500)
    )

    Tab(
        selected = isSelected,
        onClick = {
            coroutineScope.launch {
                pagerState.scrollToPage(index)
            }
        },
        modifier = Modifier
            .size(55.dp)
            .animateContentSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(if (isSelected) 1f else 0.88f),
            contentAlignment = Alignment.TopEnd
        ) {
            Card(
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxSize()
                    .border(
                        if (isSelected) 1.dp else 0.dp,
                        appTheme
                            .colorTextApp()
                            .copy(0.5f),
                        RoundedCornerShape(10.dp)
                    ),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.elevatedCardElevation(3.dp),
                colors = CardDefaults.cardColors(lineColor)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(.3.dp)
                        .background(
                            shape = RoundedCornerShape(10.dp),
                            color = animatedColor.copy(alpha = if (isSelected) 1f else 0.85f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (index + 1).toString(),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.font_main_bold))
                        ),
                        color = Color.White
                    )
                }
            }
            androidx.compose.animation.AnimatedVisibility(visible = item.value.checkFlag) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_mark),
                    contentDescription = null,
                    tint = ColorButtonTadContentIsCheck,
                    modifier = Modifier
                        .size(22.dp * if (isSelected) 1f else 0.88f)
                        .padding(end = 9.dp, bottom = 9.dp)
                )
            }
        }
    }
}

private fun tabColor(
    variantClick: Int,
    variantTrue: Int
): Color {
    return when {
        variantClick != variantTrue && variantClick != 0 -> ColorButtonTadContentCheckFalse
        variantClick == variantTrue && variantClick != 0 -> ColorButtonTadContentCheckTrue
        else -> ColorButtonTadContent
    }
}

private fun tabColorExam(
    variantClick: Int,
    tabColor: Color
): Color {
    return when {
        variantClick != 0 -> ColorBottomButtonsActiveExam
        else -> tabColor
    }
}