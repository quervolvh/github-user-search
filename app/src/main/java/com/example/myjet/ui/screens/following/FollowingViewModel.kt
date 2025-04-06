package com.example.myjet.ui.screens.following

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjet.src.models.GithubUserModel
import com.example.myjet.src.repository.github.impl.GithubRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FollowingUiState(
    val following: Map<String, List<GithubUserModel>> = mutableMapOf(),
    val followers: Map<String, List<GithubUserModel>> = mutableMapOf(),
)

@HiltViewModel
class FollowingViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(
        FollowingUiState(
            following = mutableMapOf(),
            followers = mutableMapOf()
        )
    )

    val uiState: StateFlow<FollowingUiState> = _uiState.asStateFlow()

    fun fetchList(user: String, followers: Boolean) {

        val focusableObject = if (followers) uiState.value.followers else uiState.value.following

        viewModelScope.launch {

            if (focusableObject[user] == null) {

                val followingList =

                    if (followers) GithubRepositoryImpl().getUserFollowers((user)) else
                        GithubRepositoryImpl().getUserFollowing((user));

                if (followers) {
                    _uiState.update {

                        it.copy(
                            followers = it.followers + mutableMapOf(user to followingList)
                        )
                    }

                } else {

                    _uiState.update {

                        it.copy(
                            following = it.following + mutableMapOf(user to followingList)
                        )
                    }

                }

            }

        }

    }

    companion object {}

}