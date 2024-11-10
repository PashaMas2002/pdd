package com.example.composablepdd.screens.main.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composablepdd.R
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.ui.myTheme.AppTheme
import com.example.composablepdd.ui.theme.ColorBookCard

@Composable
fun TitleBook() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 40.dp,
            ),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp,),
        colors = CardDefaults.cardColors(containerColor = ColorBookCard)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "ПДД",
                color = Color.White,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.font_main)),
                ),
                modifier = Modifier
                    .padding(
                        start = 30.dp,
                        end = 10.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    )
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_circle_24),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .size(18.dp)
                    .padding(end = 10.dp)
            )
            Text(
                text = "2024",
                color = Color.White,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.font_main)),
                ),
                modifier = Modifier
                    .padding(
                        end = 10.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    )
            )
        }
    }
}

val listName = listOf(
    R.string.name_book_1,
    R.string.name_book_2,
    R.string.name_book_3,
    R.string.name_book_4,
    R.string.name_book_5,
    R.string.name_book_6,
)

fun LazyListScope.columnBook(
    appTheme: AppTheme,
    navController: NavController
) {
    itemsIndexed(listName) { index, item ->
        ItemColumnBook(index, item, appTheme, navController)
    }
}

@Composable
fun ItemColumnBook(
    index: Int,
    nameRes: Int,
    appTheme: AppTheme,
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val bookName = stringResource(id = nameRes)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                mainViewModel.getBook.buttonId.intValue = index
                if (index != 4) navController.navigate("SCREEN_BOOK") else  navController.navigate("LIST_BOOK_SCREEN")
            }
    ) {
        Text(
            text = "${index + 1}. $bookName",
            color = appTheme.colorTextApp(),
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.font_main)),
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.5f.dp)
                .padding(start = 20.dp)
                .background(appTheme.foregroundApp())
        )
    }
}
