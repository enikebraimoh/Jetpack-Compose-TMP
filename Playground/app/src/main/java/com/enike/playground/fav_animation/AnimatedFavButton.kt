package com.enike.playground.fav_animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints.Companion.Infinity
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
                        durationMillis = 1500,
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
                        durationMillis = 1500,
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

    val contentColor = transaction.animateColor(
        transitionSpec = {
            when {
                ButtonState.PRESSED isTransitioningTo ButtonState.IDLE ->
                    tween(
                        durationMillis = 500
                    )
                else ->
                    tween(durationMillis = 500)
            }
        },
        label = "button corner radius"
    ) { state ->
        when (state) {
            ButtonState.IDLE -> MaterialTheme.colors.primary
            ButtonState.PRESSED -> Color.White
        }
    }

    val backgroundColor = transaction.animateColor(
        transitionSpec = {
            when {
                ButtonState.PRESSED isTransitioningTo ButtonState.IDLE ->
                    tween(
                        durationMillis = 3000,
                        easing = FastOutLinearInEasing
                    )
                else ->
                    tween(durationMillis = 3000)
            }
        },
        label = "button corner radius"
    ) { state ->
        when (state) {
            ButtonState.IDLE -> Color.White
            ButtonState.PRESSED -> MaterialTheme.colors.primary
        }
    }

    val iconOpacity = transaction.animateFloat(
        transitionSpec = {
            when {
                ButtonState.PRESSED isTransitioningTo ButtonState.IDLE ->
                    tween(
                        durationMillis = 3000
                    )
                else ->
                    tween(durationMillis = 1500)
            }
        },
        label = "button corner radius"
    ) { state ->
        when (state) {
            ButtonState.IDLE -> 0f
            ButtonState.PRESSED -> 1f
        }
    }

    val textOpacity = transaction.animateFloat(
        transitionSpec = {
            when {
                ButtonState.PRESSED isTransitioningTo ButtonState.IDLE ->
                    tween(
                        durationMillis = 3000
                    )
                else ->
                    tween(durationMillis = 1500)
            }
        },
        label = "button corner radius"
    ) { state ->
        when (state) {
            ButtonState.IDLE -> 1f
            ButtonState.PRESSED -> 0f
        }
    }

    val heartSize = transaction.animateDp(
        transitionSpec = {
            when {
                ButtonState.PRESSED isTransitioningTo ButtonState.IDLE ->
                    tween(
                        durationMillis = 3000
                    )
                else ->
                    keyframes {
                        durationMillis = 2200
                        24.dp at 1700
                        12.dp at 1900
                    }
            }
        },
        label = "button corner radius"
    ) { state ->
        when (state) {
            ButtonState.IDLE -> 24.dp
            ButtonState.PRESSED -> 24.dp
        }
    }

    val idealHeartSize = transaction.animateDp(
        transitionSpec = {
            when {
                ButtonState.PRESSED isTransitioningTo ButtonState.IDLE ->
                    repeatable(
                        animation = keyframes {
                            durationMillis = 2000
                            24.dp at 1400
                            12.dp at 1500
                            24.dp at 1600
                            12.dp at 1700
                        },
                        iterations = Infinity
                    )
                else ->
                    tween(
                        durationMillis = 3000
                    )
            }
        },
        label = "button corner radius"
    ) { state ->
        when (state) {
            ButtonState.IDLE -> 24.dp
            ButtonState.PRESSED -> 24.dp
        }
    }


    FavButton(
        state = buttonState,
        buttonWidth = buttonWidth,
        clicked = {
            buttonState = when (buttonState) {
                ButtonState.PRESSED -> ButtonState.IDLE
                ButtonState.IDLE -> ButtonState.PRESSED
            }
        },
        cornerRadius = buttonRadiusCorners,
        contentColor = contentColor,
        backgroundColor = backgroundColor,
        iconOpacity = iconOpacity,
        textOpacity = textOpacity,
        heartSize = heartSize,
        idealHeartSize = idealHeartSize
    )

}

@Composable
@Preview
fun MainContent() {
    PlaygroundTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                Modifier.fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedFavButton()
            }
        }
    }
}