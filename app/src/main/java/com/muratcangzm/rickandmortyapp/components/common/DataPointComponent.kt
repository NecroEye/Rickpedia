package com.muratcangzm.rickandmortyapp.components.common

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.muratcangzm.rickandmortyapp.ui.theme.RickAction
import com.muratcangzm.rickandmortyapp.ui.theme.RickPrimary
import com.muratcangzm.rickandmortyapp.ui.theme.RickTextPrimary

data class DataPoint(
    val title:String,
    val description: String,
)

@Composable
fun DataPointComponent(dataPoint: DataPoint){

    Column {

        Text(
            text = dataPoint.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = RickAction
        )

        Text(text = dataPoint.description,
            fontSize = 24.sp,
            color = RickTextPrimary
        )
    }
}


@Preview
@Composable
private fun DataPointComponentPreview(){
    val data = DataPoint("Hayatta mı?", "Bilinmiyor")
    DataPointComponent(data)
}
