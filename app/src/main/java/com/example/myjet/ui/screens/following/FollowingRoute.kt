package com.example.myjet.ui.screens.following

import androidx.compose.runtime.Composable
import com.example.myjet.src.navigation.MyJetNavigation

@Composable
fun FollowingRoute (
    followingViewModel: FollowingViewModel,
    navActions : MyJetNavigation,
    focusedUser : String,
    fetchFollowers: Boolean
){
    FollowingScreen(
        followingViewModel = followingViewModel,
        navActions = navActions,
        focusedUser = focusedUser,
        fetchFollowers = fetchFollowers
    )
}
