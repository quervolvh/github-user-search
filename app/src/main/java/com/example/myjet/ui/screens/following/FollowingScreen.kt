package com.example.myjet.ui.screens.following

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.myjet.R
import com.example.myjet.src.navigation.MyJetNavigation
import androidx.compose.runtime.collectAsState

@Composable
fun FollowingScreen(
    followingViewModel: FollowingViewModel,
    navActions: MyJetNavigation,
    focusedUser: String,
    fetchFollowers: Boolean
) {

    val followingState: FollowingUiState by followingViewModel.uiState.collectAsState()

    val focusedList = if (fetchFollowers) followingState.followers else followingState.following

    val pageTitle = if (fetchFollowers) "Followers" else "Following"

    LaunchedEffect(Unit) {
        followingViewModel.fetchList(focusedUser, fetchFollowers)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(12, 123, 240, 255)
    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(all = 15.dp)
                .fillMaxWidth()
                .padding(top = 20.dp),
        ) {

            Text(
                "$focusedUser's $pageTitle",
                fontSize = TextUnit(18.0F, type = TextUnitType.Sp),
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(10.dp))

            focusedList[focusedUser]?.let {

                LazyColumn (modifier = Modifier.fillMaxWidth()){
                    items(

                        count = it.count(),

                        itemContent = { item ->

                            Text(
                                it[item].login,
                                fontFamily = FontFamily(
                                    Font(R.font.poppins_regular)
                                ),
                                color = Color.White,
                                modifier = Modifier
                                    .padding(9.dp)
                                    .clickable(onClick = {

                                        navActions.navigateToProfile(it[item])

                                    })

                            )

                        }

                    )


                }

            }

        }

    }

}
