package com.example.myjet.ui.screens.profile

import androidx.compose.runtime.Composable
import com.example.myjet.src.navigation.MyJetNavigation

@Composable
fun ProfileRoute (
    profileViewModel : ProfileViewModel,
    navActions : MyJetNavigation,
    profileToDisplay : String
){
    ProfileScreen(
        profileViewModel = profileViewModel,
        navActions = navActions,
        profileToDisplay = profileToDisplay
    )
}
