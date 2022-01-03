package com.adashi.compose_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adashi.compose_2.ui.theme.Compose_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_2Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App()
                }
            }
        }
    }
}

@Composable
fun ShowOnBoardingScreen() {

    //var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Welcome to the Basics Codelab!")
            Button(
                onClick = {  },
                modifier = Modifier.padding(all = 24.dp)
            ) {
                Text(text = "Contunue")

            }

        }
    }

}

@Composable
fun App() {
    Greet()
}

@Composable
fun Greet(names: List<String> = listOf("World", "Compose")) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    val expanded = remember { mutableStateOf(false) }

    val extrapadding = if (expanded.value) 50.dp else 0.dp

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extrapadding)
            ) {
                Text(text = "Hello,")
                Text(text = name)
            }
            OutlinedButton(onClick = { expanded.value = !expanded.value }) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun DefaultPreview() {
    Compose_2Theme {
        App()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnBoarding() {
    Compose_2Theme {
        ShowOnBoardingScreen()
    }
}