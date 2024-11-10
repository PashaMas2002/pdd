package com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.element

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun ItemImage(bitmap: Bitmap?){
    if (bitmap != null) {
        Card(
            modifier = Modifier
                .padding(start = 3.dp, end = 3.dp, bottom = 15.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }
    } else {
        NonImagePlaceholder()
    }
}