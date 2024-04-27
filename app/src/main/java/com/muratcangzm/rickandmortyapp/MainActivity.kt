package com.muratcangzm.rickandmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muratcangzm.network.KtorClient
import com.muratcangzm.network.models.domain.Character
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private val ktorClient = KtorClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var character by remember {
                mutableStateOf<Character?>(null)
            }

            LaunchedEffect(key1 = Unit, block = {
                delay(2000)
                character = ktorClient.getCharacter(1)

            })

            Surface(modifier = Modifier.fillMaxSize()) {

                Column(modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(text = character?.name ?: "Empty",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black)


                }

            }

        }

    }

}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}