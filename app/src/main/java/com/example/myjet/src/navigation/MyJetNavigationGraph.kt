package com.example.myjet.src.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myjet.MyJetContainer
import com.example.myjet.ui.screens.following.FollowingRoute
import com.example.myjet.ui.screens.following.FollowingViewModel
import com.example.myjet.ui.screens.home.HomeRoute
import com.example.myjet.ui.screens.home.HomeViewModel
import com.example.myjet.ui.screens.profile.ProfileRoute
import com.example.myjet.ui.screens.profile.ProfileViewModel
import com.example.myjet.ui.screens.search.SearchRoute
import com.example.myjet.ui.screens.search.SearchViewModel
import com.example.myjet.ui.screens.splash.SplashRoute

@Composable
fun MyJetNavigationGraph(
    appContainer: MyJetContainer,
    navActions: MyJetNavigation,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MyJetDestinations.SPLASH_ROUTE,
    homeViewModel: HomeViewModel,
    searchViewModel: SearchViewModel,
    profileViewModel: ProfileViewModel,
    followingViewModel: FollowingViewModel
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        composable(
            route = MyJetDestinations.SPLASH_ROUTE,
            deepLinks = listOf()
        ) {

            SplashRoute(navActions = navActions)

        }

        composable(MyJetDestinations.HOME_ROUTE) {

            HomeRoute(
                homeViewModel = homeViewModel,
                navActions = navActions,
            )

        }

        composable(MyJetDestinations.SEARCH_RESULTS_ROUTE) {

            SearchRoute(
                searchViewModel = searchViewModel,
                navActions = navActions,
            )

        }

        composable(MyJetDestinations.PROFILE_ROUTE) {
            ProfileRoute(
                profileViewModel = profileViewModel,
                navActions = navActions,
                profileToDisplay = navController.previousBackStackEntry?.savedStateHandle?.get("profileInFocus")
                    ?: "becanee"
            )

        }

        composable(MyJetDestinations.FOLLOWING_ROUTE) {
            FollowingRoute(
                followingViewModel = followingViewModel,
                navActions = navActions,
                focusedUser = navController.previousBackStackEntry?.savedStateHandle?.get("profileInFocus")
                    ?: "----_-------------------------------------------------_----",
                fetchFollowers = navController.previousBackStackEntry?.savedStateHandle?.get("fetchFollowers")
                    ?: false
            )

        }

    }

}