package com.enike.playground.fav_animation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enike.playground.ui.theme.PlaygroundTheme


enum class ButtonState {
    IDLE, PRESSED
}


@Preview
@Composable
fun AnimatedFavButton() {

    var buttonState by remember { mutableStateOf(ButtonState.IDLE) }
    val transaction = updateTransition(targetState = buttonState, label = "button state")


    val buttonWidth = transaction.animateDp(
        transitionSpec = {
            when {
                ButtonState.PRESSED isTransitioningTo ButtonState.IDLE ->
                   tween(
                       durationMillis = 3000,
                       easing = FastOutLinearInEasing
                   )
                else ->
                    tween(durationMillis = 1500)
            }
        }, label = "button width"
    ) { state ->
        when (state) {
            ButtonState.IDLE -> 300.dp
            ButtonState.PRESSED -> 60.dp
        }
    }

    val buttonRadiusCorners = transaction.animateInt(
        transitionSpec = {
            when {
                ButtonState.PRESSED isTransitioningTo ButtonState.IDLE ->
                    tween(
                        durationMillis = 3000,
                        easing = FastOutLinearInEasing
                    )
                else ->
                    tween(durationMillis = 1500)
            }
        },
        label = "button corner radius"
    ) { state ->
        when (state) {
            ButtonState.IDLE -> 6
            ButtonState.PRESSED -> 50
        }
    }

    FavButton(
        state = buttonWidth,
        clicked = {
            buttonState = when (buttonState) {
                ButtonState.PRESSED -> ButtonState.IDLE
                ButtonState.IDLE -> ButtonState.PRESSED
            }
        },
        cornerRadius = buttonRadiusCorners
    )

}


@Composable
@Preview
fun MainContent() {
    PlaygroundTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedFavButton()
            }
        }
    }
}