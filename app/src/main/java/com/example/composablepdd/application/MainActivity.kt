package com.example.composablepdd.application

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composablepdd.screens.book.ListBook.ScreenListBook
import com.example.composablepdd.screens.book.NameBook.ScreenBook
import com.example.composablepdd.screens.main.ScreenMain
import com.example.composablepdd.screens.regions.ScreenRegions
import com.example.composablepdd.screens.settings.ScreenSettings
import com.example.composablepdd.screens.test.ComposableTest
import com.example.composablepdd.screens.themes.ScreenThemes
import com.example.composablepdd.screens.ticket.ScreenTicket
import com.example.composablepdd.ui.theme.ComposablePDDTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val mainScreenLazyListState = rememberLazyListState()

            ComposablePDDTheme {
                NavHost(
                    navController = navController,
                    startDestination = "MAIN_SCREEN",
                ) {
                    composable(
                        route = "MAIN_SCREEN"
                    ) {
                        ScreenMain(navController, mainScreenLazyListState)
                    }
                    composable(
                        route = "SCREEN_BOOK"
                    ) {
                        ScreenBook(navController)
                    }
                    composable(
                        route = "SETTINGS_SCREEN"
                    ) {
                        ScreenSettings(navController, this@MainActivity)
                    }
                    composable(
                        route = "REGION_SCREEN"
                    ) {
                        ScreenRegions(navController)
                    }
                    composable(
                        route = "TICKET_SCREEN"
                    ) {
                        ScreenTicket(navController)
                    }
                    composable(
                        route = "THEMES_SCREEN"
                    ) {
                        ScreenThemes(navController)
                    }
                    composable(
                        route = "PAGER_SCREEN"
                    ) {
                        ComposableTest(navController)
                    }
                    composable(
                        route = "LIST_BOOK_SCREEN"
                    ) {
                        ScreenListBook(navController)
                    }
                }
            }
        }
    }
}

