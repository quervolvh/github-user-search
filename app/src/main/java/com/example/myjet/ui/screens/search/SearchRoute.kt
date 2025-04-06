package com.example.myjet.ui.screens.search

import androidx.compose.runtime.Composable
import com.example.myjet.src.navigation.MyJetNavigation

@Composable
fun SearchRoute (
    searchViewModel : SearchViewModel,
    navActions : MyJetNavigation,
){

    SearchScreen(searchViewModel = searchViewModel, navActions = navActions)

}
