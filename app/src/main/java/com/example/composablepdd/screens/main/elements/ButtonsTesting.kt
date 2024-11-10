package com.example.composablepdd.screens.main.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.R
import com.example.composablepdd.ui.myTheme.AppTheme
import com.example.composablepdd.ui.theme.ColorLightRed
import com.example.composablepdd.ui.theme.ColorLightBlue

@Composable
fun ButtonsTicket(
    appTheme: AppTheme,
    navController: NavController,
    isVisibleMarathonPreview: MutableState<Boolean>,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .height(300.dp)
    ) {
        Text(
            text = "Билеты",
            color = appTheme.colorTextApp(),
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.font_main_bold)),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, bottom = 5.dp, top = 15.dp)
        )

        val buttonData = listOf(
            ButtonData("Экзамен", R.drawable.ic_lum, 1, "PAGER_SCREEN", ColorLightRed),
            ButtonData("Билеты", R.drawable.ic_tickets, null, "TICKET_SCREEN", ColorLightBlue),
            ButtonData("Темы", R.drawable.ic_school, null, "THEMES_SCREEN", ColorLightBlue),
            ButtonData("Марафон", R.drawable.ic_inclusive, 4, "", ColorLightBlue),
            ButtonData("Мои ошибки", R.drawable.ic_mistake, 5, "PAGER_SCREEN", ColorLightBlue),
            ButtonData("Избранные", R.drawable.ic_all_bookmarks, 6, "PAGER_SCREEN", ColorLightBlue)
        )

        buttonData.chunked(2).forEach { pair ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                pair.forEach { data ->
                    TicketCard(data, navController, mainViewModel, Modifier.weight(1f), isVisibleMarathonPreview)
                }
            }
        }
    }
}

@Composable
private fun TicketCard(
    data: ButtonData,
    navController: NavController,
    mainViewModel: MainViewModel,
    modifier: Modifier,
    isVisibleMarathonPreview: MutableState<Boolean>
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp)
            .clickable {
                data.variant?.let { mainViewModel.getDataBaseForTest.variantList.value = it }
                if (data.variant != 4) navController.navigate(data.screen) else {
                    isVisibleMarathonPreview.value = true
                }
            },
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = data.color),
    ) {
        Column(
            Modifier.padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = data.title,
                color = Color.White,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                ),
            )
            Icon(
                painter = painterResource(id = data.icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .size(30.dp)
            )
        }
    }
}

data class ButtonData(
    val title: String,
    val icon: Int,
    val variant: Int?,
    val screen: String,
    val color: Color
)