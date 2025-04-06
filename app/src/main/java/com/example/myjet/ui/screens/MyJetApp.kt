package com.example.myjet.ui.screens

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.myjet.MyJetContainer
import com.example.myjet.src.navigation.MyJetNavigation
import com.example.myjet.src.navigation.MyJetNavigationGraph
import com.example.myjet.ui.screens.following.FollowingViewModel
import com.example.myjet.ui.screens.home.HomeViewModel
import com.example.myjet.ui.screens.profile.ProfileViewModel
import com.example.myjet.ui.screens.search.SearchViewModel
import com.example.myjet.ui.theme.MyJetTheme

@Composable
fun MyJetApp (appContainer: MyJetContainer, widthSizeClass: WindowWidthSizeClass) {

    val searchScreenViewModel: SearchViewModel = hiltViewModel()
    val homeScreenViewModel: HomeViewModel = hiltViewModel()
    val profileScreenViewModel: ProfileViewModel = hiltViewModel()
    val followingViewModel : FollowingViewModel = hiltViewModel()

    MyJetTheme {

        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            MyJetNavigation(navController, searchScreenViewModel, profileScreenViewModel)
        }

        MyJetNavigationGraph(

            appContainer = appContainer,
            navController = navController,
            navActions = navigationActions,
            searchViewModel = searchScreenViewModel,
            homeViewModel = homeScreenViewModel,
            profileViewModel = profileScreenViewModel,
            followingViewModel = followingViewModel

        )

    }

}