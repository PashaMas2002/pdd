package com.example.composablepdd.screens.ticket

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.screens.ticket.elements.ItemTickets
import com.example.composablepdd.screens.ticket.elements.TicketsTopMenu
import kotlin.math.max

@Composable
fun ScreenTicket(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val appTheme = mainViewModel.getAppTheme
    val (ticketProgress, ticketSize) =
        mainViewModel.getTableTicket.getProgressTicket()

    val outerListState = rememberLazyGridState()
    val screenHeight = with(LocalDensity.current) { 200.dp.toPx() }
    val headerHeight = screenHeight / 2
    val decreasingNum = remember { Animatable(1f) }
    val increasingNum = remember { Animatable(1f) }

    LaunchedEffect(outerListState) {
        snapshotFlow { outerListState.firstVisibleItemScrollOffset }
            .collect { offset ->
                if (outerListState.firstVisibleItemIndex == 0) {
                    val newAlpha = max(1f - (offset / headerHeight * 2), 0f)
                    decreasingNum.snapTo(newAlpha)
                    increasingNum.snapTo(1f - newAlpha * 1.5f)
                } else {
                    decreasingNum.snapTo(0f)
                    increasingNum.snapTo(1f)
                }
            }
    }
    val listTickets = mainViewModel.getTableTicket.getItemTicket()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .background(appTheme.backgroundApp()),
        state = outerListState,
        contentPadding = PaddingValues(
            start = 10.dp,
            end = 10.dp,
            top = 120.dp,
            bottom = 90.dp
        )
    ) {
        itemsIndexed(listTickets) { index, item ->
            if (item != null) {
                ItemTickets(index, item, navController)
            }
        }
    }
    TicketsTopMenu(
        appTheme = appTheme,
        decreasingNum = decreasingNum,
        increasingNum = increasingNum,
        ticketProgress = ticketProgress,
        ticketSize = ticketSize,
        navController = navController
    )
}



