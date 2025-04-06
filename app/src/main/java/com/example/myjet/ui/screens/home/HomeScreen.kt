package com.example.myjet.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.myjet.R
import com.example.myjet.src.navigation.MyJetNavigation
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navActions: MyJetNavigation
) {

    val interactionSource = remember { MutableInteractionSource() }
    var searchText by remember { mutableStateOf("") }
    val searchResults: HomeUiState by homeViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = searchResults) {
        if ( searchResults.searchComplete && searchResults.foundUsers != null ) {

            if ( searchResults.foundUsers?.items?.count() == 1) {

                navActions.navigateToProfile(searchResults.foundUsers?.items?.get(0)!!)

            } else {

                val search = searchResults.foundUsers?.items?.find {it.login.lowercase() == searchText.lowercase()};

                if ( search != null ) {

                    navActions.navigateToProfile(search)

                } else {

                    navActions.navigateToSearch(searchResults.foundUsers!!)

                }

            }

        }
        if ( searchResults.searchComplete ) {

            homeViewModel.resetSearchProgress()

        }
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
                "Github User Search",
                fontSize = TextUnit(18.0F, type = TextUnitType.Sp),
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Color.White,

                )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 17.dp, bottom = 17.dp)
                    .clip(RoundedCornerShape(size = 15.dp))
                    .background(color = Color.White)
                    .border(1.dp, Color.Transparent)
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.search_interface_symbol),
                    contentDescription = "search",
                    modifier = Modifier.height(Dp(20.0f))
                )

                Spacer(modifier = Modifier.width(10.dp))

                BasicTextField(
                    value = searchText,
                    onValueChange = { newText -> searchText = newText },
                    textStyle = TextStyle(
                        fontSize = TextUnit(14.0f, TextUnitType.Sp),
                        fontFamily = FontFamily(
                            Font(R.font.poppins_regular)
                        ),
                    ),
                    modifier = Modifier.indicatorLine(
                        enabled = false,
                        focusedIndicatorLineThickness = 0.dp,
                        unfocusedIndicatorLineThickness = 0.dp,
                        isError = false,
                        interactionSource = interactionSource,

                        colors = TextFieldDefaults.colors().copy(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                        ),

                        )
                )

            }

            Text(
                "Looking for a GitHub user ? Enter a name and let us do the magic!",
                fontSize = TextUnit(14.0F, type = TextUnitType.Sp),
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Color.White,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Button(

                onClick = {
                    if (searchText.trim().isNotEmpty()) {
                        homeViewModel.searchUser(searchText)
                    }},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)
                    .border(1.dp, Color.Transparent)


            ) {
                Text(
                    if (searchResults.loading) "Searching..." else "Search",
                    modifier = Modifier.padding(9.dp),
                    fontFamily = FontFamily(
                        Font(R.font.poppins_regular)
                    ),
                )
            }

        }

    }

}
