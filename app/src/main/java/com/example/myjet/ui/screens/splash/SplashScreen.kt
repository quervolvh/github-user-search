package com.example.myjet.ui.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.lifecycle.Lifecycle
import com.example.myjet.R
import com.example.myjet.src.navigation.MyJetNavigation
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navActions: MyJetNavigation) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(12, 123, 240, 255)
    ) {

        val lifecycleOwner = LocalLifecycleOwner.current
        val stateFlow = lifecycleOwner.lifecycle.currentStateFlow

        LaunchedEffect(key1 = "secretes") {

            stateFlow.collect {

                if (it == Lifecycle.State.STARTED) {

                    delay(2000);

                    navActions.navigateToHome()

                }

            }

        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                "GitHub User Search",
                fontSize = TextUnit(34.0F, type = TextUnitType.Sp),
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Color.White
            )

        }

    }

}