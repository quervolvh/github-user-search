package com.example.myjet.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun SearchScreen(
    searchViewModel: SearchViewModel,
    navActions: MyJetNavigation,
) {

    val searchResults: SearchUiState by searchViewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(12, 123, 240, 255)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(all = 15.dp)
                .padding(top = 20.dp),
        ) {

            Text(
                "Github User(s) Search Result",
                fontSize = TextUnit(18.0F, type = TextUnitType.Sp),
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Color.White,
            )

            Text(
                if (searchResults.foundUsers?.items?.isEmpty() == true) "Unfortunately, your search did not find any users" else "We found 5 user(s)!",
                fontSize = TextUnit(14.0F, type = TextUnitType.Sp),
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Color.White,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Column (

                modifier = Modifier.verticalScroll(scrollState).fillMaxWidth()

            ) {
                searchResults.foundUsers?.items?.forEach {
                    Text(
                        it.login,
                        fontFamily = FontFamily(
                            Font(R.font.poppins_regular)
                        ),
                        color = Color.White,
                        modifier = Modifier.padding(9.dp)
                            .clickable(onClick = {

                                navActions.navigateToProfile(it)

                            })

                    )
                }

            }

        }

    }

}
