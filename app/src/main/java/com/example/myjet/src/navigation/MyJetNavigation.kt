package com.example.myjet.src.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.myjet.src.models.GithubSearchModel
import com.example.myjet.src.models.GithubUserModel
import com.example.myjet.ui.screens.profile.ProfileViewModel
import com.example.myjet.ui.screens.search.SearchViewModel

object MyJetDestinations {
    const val SPLASH_ROUTE = "splash"
    const val HOME_ROUTE = "home"
    const val SEARCH_RESULTS_ROUTE = "search"
    const val PROFILE_ROUTE = "profile"
    const val FOLLOWING_ROUTE = "following"
}

class MyJetNavigation(
    navController: NavHostController,
    searchViewModel: SearchViewModel,
    profileViewModel: ProfileViewModel,
    ) {

    val navigateToHome: () -> Unit = {
        navController.navigate(MyJetDestinations.HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToSearch: (searchResults: GithubSearchModel) -> Unit = {
        searchViewModel.setSearchedUsers(it)
        navController.navigate(MyJetDestinations.SEARCH_RESULTS_ROUTE) { }
    }
    val navigateToProfile: (profile: GithubUserModel) -> Unit = {
        navController.currentBackStackEntry?.savedStateHandle?.set("profileInFocus", it.login)
        profileViewModel.attachUser(it)
        navController.navigate(MyJetDestinations.PROFILE_ROUTE) { }
    }
    val navigateToFollowers: (profileUserName: String, fetchFollowers: Boolean) -> (Unit) = { a: String, b: Boolean ->
        navController.currentBackStackEntry?.savedStateHandle?.set("profileInFocus", a)
        navController.currentBackStackEntry?.savedStateHandle?.set("fetchFollowers", b)
        navController.navigate(MyJetDestinations.FOLLOWING_ROUTE) { }
    }
}
