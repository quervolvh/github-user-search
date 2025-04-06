package com.example.myjet.ui.screens.home

import androidx.compose.runtime.Composable
import com.example.myjet.src.navigation.MyJetNavigation

@Composable
fun HomeRoute (
    homeViewModel : HomeViewModel,
    navActions : MyJetNavigation
){

    HomeScreen(homeViewModel = homeViewModel, navActions = navActions)

}
