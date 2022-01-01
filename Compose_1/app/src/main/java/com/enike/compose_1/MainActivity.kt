package com.enike.compose_1

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enike.compose_1.ui.theme.Compose_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_1Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    messageCard(Message("enike", "Jetpack Compose"))
                }
            }
        }
    }
}

@Composable
fun messageCard(data: Message) {
    Row(modifier = Modifier.padding(all = 10.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "this is a profile image",
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column() {
            Text(
                text = data.user,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.secondaryVariant
            )

            Spacer(modifier = Modifier.width(10.dp))

            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                Text(
                    text = data.Message,
                    Modifier.padding(all = 5.dp),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Composable
fun Conversation (messages : List<Message>){
    LazyColumn{
        items(messages){ message ->
            messageCard(data = message)
        }
    }
}


@Preview(showBackground = true,
name = "Dark mode")
@Composable
fun PreviewMessageCard() {
    Compose_1Theme {
        messageCard(Message("Enike", "Jetpack Compose"))
    }
}


@Preview(showBackground = true,
    name = "Dark mode")
@Composable
fun PreviewConversation() {
    Compose_1Theme {
       Conversation(messages = SampleData.conversationSample)
    }
}

