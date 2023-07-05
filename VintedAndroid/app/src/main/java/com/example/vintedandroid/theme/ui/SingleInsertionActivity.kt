package com.example.vintedandroid.theme.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.vintedandroid.client.models.BasicInsertionDto

@Composable
fun SingleInsertion(insertion : BasicInsertionDto) {
    Image(painter = insertion.imagePath, contentDescription = )

}

@Preview(showBackground = true)
@Composable
fun anteprima() {
    SingleInsertion()
}