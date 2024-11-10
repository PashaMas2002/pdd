package com.example.composablepdd.screens.test.func

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.database.entities.ItemQuestions
import java.io.IOException


@Composable
fun getBitmap(
    item: ItemQuestions,
    mainViewModel: MainViewModel = hiltViewModel()
): Bitmap? {
    return remember(item.image) {
        if (item.image != null) {
            val appContext = mainViewModel.applicationContext
            val assetManager = appContext.assets

            fun loadBitmapFromAssets(resourceName: String): Bitmap? {
                val extensions = arrayOf("webp", "jpg")
                for (extension in extensions) {
                    try {
                        val inputStream =
                            assetManager.open("path_to_image/$resourceName.$extension")
                        return BitmapFactory.decodeStream(inputStream)
                    } catch (e: IOException) {
                        //
                    }
                }
                return null
            }
            loadBitmapFromAssets(item.image)
        } else null
    }
}
