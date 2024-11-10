package com.example.composablepdd.screens.main.elements

import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.R
import com.example.composablepdd.ui.theme.ColorButtonMarathonPreview

@Composable
fun MarathonPreview(
    isVisible: MutableState<Boolean>,
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val appTheme = mainViewModel.getAppTheme
    val isButtonRestart = mainViewModel.getDataBaseForTest.isMarathonRestarted.collectAsState(true)
    val supports = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val background = if (supports) Color.DarkGray.copy(.4f) else appTheme.colorPopUpWindow().copy(.98f)
    val elevation = if (supports) 0.dp else 10.dp
    val colorButton = if (supports) ColorButtonMarathonPreview else appTheme.colorButtonPopUpWindow()
    val textColor = if (supports) Color.White else appTheme.colorTextApp()
        AnimatedVisibility(
            visible = isVisible.value,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.95f)
                    .border(.3.dp, appTheme.colorTextApp().copy(.3f), RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)),
                colors = CardDefaults.cardColors(background),
                shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                elevation = CardDefaults.cardElevation(elevation)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 150.dp, top = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Марафон",
                        color = textColor,
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontFamily = FontFamily(Font(R.font.font_main_bold)),
                        )
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Все 800 вопросов",
                            color = textColor,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(R.font.font_main_bold)),
                            )
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(contentAlignment = Alignment.BottomCenter) {
                            androidx.compose.animation.AnimatedVisibility(
                                visible = isButtonRestart.value,
                                enter = scaleIn(),
                                exit = scaleOut()
                            ) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth(.75f)
                                        .height(46.dp)
                                        .padding(bottom = 1.dp)
                                        .animateContentSize(),
                                    colors = CardDefaults.cardColors(Color.White.copy(0.2f)),
                                    shape = RoundedCornerShape(15.dp),
                                    onClick = {
                                        navController.navigate("PAGER_SCREEN")
                                    }
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(0.3.dp)
                                            .background(
                                                shape = RoundedCornerShape(15.dp),
                                                color = colorButton
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "Начать",
                                            color = Color.White,
                                            style = TextStyle(
                                                fontSize = 16.sp,
                                                fontFamily = FontFamily(Font(R.font.font_main_bold)),
                                            )
                                        )
                                    }
                                }
                            }
                            androidx.compose.animation.AnimatedVisibility(
                                visible = !isButtonRestart.value,
                                enter = scaleIn(),
                                exit = scaleOut()
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth(.75f)
                                            .height(46.dp)
                                            .padding(bottom = 1.dp)
                                            .animateContentSize(),
                                        colors = CardDefaults.cardColors(Color.White.copy(0.2f)),
                                        shape = RoundedCornerShape(15.dp),
                                        onClick = {
                                            navController.navigate("PAGER_SCREEN")
                                        }
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(0.3.dp)
                                                .background(
                                                    shape = RoundedCornerShape(15.dp),
                                                    color = colorButton
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "Продолжить",
                                                color = Color.White,
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                                                )
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth(.75f)
                                            .height(46.dp)
                                            .padding(bottom = 1.dp)
                                            .animateContentSize(),
                                        colors = CardDefaults.cardColors(Color.White.copy(0.2f)),
                                        shape = RoundedCornerShape(15.dp),
                                        onClick = {
                                            mainViewModel.getSaveTabPosition.saveCurrentPage(0)
                                            mainViewModel.getDataBaseForTest.updateListQuestionsMarathon()
                                        },
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(0.3.dp)
                                                .background(
                                                    shape = RoundedCornerShape(15.dp),
                                                    color = colorButton
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "Сбросить",
                                                color = Color.White,
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    fontFamily = FontFamily(Font(R.font.font_main_bold)),
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Text(
                            text = "Выход",
                            color = textColor,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.font_main_bold)),
                            ),
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .clickable {
                                    isVisible.value = false
                                }
                        )
                    }
                }
            }
        }
}
