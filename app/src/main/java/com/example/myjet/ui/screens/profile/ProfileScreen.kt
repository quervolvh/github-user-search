package com.example.myjet.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    navActions: MyJetNavigation,
    profileToDisplay: String
) {

    val profileState: ProfileUiState by profileViewModel.uiState.collectAsState()
    println(profileState.profiles)

    LaunchedEffect(Unit) {
        profileViewModel.getExpandedUser(profileToDisplay)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(12, 123, 240, 255)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 15.dp)
                .padding(top = 20.dp),
        ) {

            Text(
                "Github User Profile",
                fontSize = TextUnit(18.0F, type = TextUnitType.Sp),
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(10.dp))

            profileState.profiles[profileToDisplay]?.let {

                Column {


                    Text(
                        it.login,
                        fontSize = TextUnit(14.0F, type = TextUnitType.Sp),
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 0.dp, top = 5.dp, bottom = 5.dp)
                            .clip(shape = RoundedCornerShape(5.dp))
                            .background(color = Color.Blue)
                            .padding(start = 35.dp, end = 35.dp, top = 5.dp, bottom = 5.dp)


                    )

                    AsyncImage(
                        model = it.avatarUrl,
                        contentDescription = "github-user-avatar",
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .height(200.dp)
                            .width(200.dp)
                            .padding(top = 0.dp)
                            .clip(shape = RoundedCornerShape(5))

                    )

                }

            }

            profileState.expandedProfiles[profileToDisplay]?.let {

                Column {

                    Text(
                        "Name:",
                        fontSize = TextUnit(15.0F, type = TextUnitType.Sp),
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color.White,
                        modifier = Modifier.padding(top = 20.dp, bottom = 2.dp)
                    )
                    Text(
                        it.name ?: "",
                        fontSize = TextUnit(20.0F, type = TextUnitType.Sp),
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color.White,
                        modifier = Modifier.padding(top = 0.dp, bottom = 30.dp)
                    )

                    Text(
                        "Description:",
                        fontSize = TextUnit(15.0F, type = TextUnitType.Sp),
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color.White,
                    )
                    Text(
                        it.bio ?: "No Description",
                        fontSize = TextUnit(18.0F, type = TextUnitType.Sp),
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color.White,
                    )

                    Row(

                        modifier = Modifier
                            .padding(bottom = 10.dp, top = 15.dp)
                            .clickable(onClick = {
                                if (it.followers != null && it.followers > 0) {
                                    navActions.navigateToFollowers(it.login, true)
                                }
                            })
                    ) {

                        Text(
                            "Followers:",
                            fontSize = TextUnit(15.0F, type = TextUnitType.Sp),
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = Color.White,
                        )

                        Text(
                            if (it.followers != null) it.followers.toString() else "0",
                            fontSize = TextUnit(20.0F, type = TextUnitType.Sp),
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = Color.White,
                            modifier = Modifier.padding(start = 10.dp)
                        )

                    }

                    Row(
                        modifier = Modifier
                            .padding(bottom = 10.dp, top = 5.dp)
                            .clickable(onClick = {

                                if (it.following != null && it.following > 0) {

                                    navActions.navigateToFollowers(it.login, false)

                                }

                            })
                    ) {

                        Text(
                            "Following:",
                            fontSize = TextUnit(15.0F, type = TextUnitType.Sp),
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = Color.White,
                        )

                        Text(
                            if (it.followers != null) it.following.toString() else "0",
                            fontSize = TextUnit(20.0F, type = TextUnitType.Sp),
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = Color.White,
                            modifier = Modifier.padding(start = 10.dp)
                        )

                    }

                }

            }

        }

    }

}
