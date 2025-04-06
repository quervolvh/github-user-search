package com.example.myjet.ui.screens.profile

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

data class ProfileUiState(
    val searchComplete: Boolean = false,
    val loading: Boolean = false,
    val profiles: Map<String, GithubUserModel> = mutableMapOf(),
    val expandedProfiles: Map<String, GithubUserModel> = mutableMapOf(),
)

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState(loading = false, profiles = mutableMapOf(), expandedProfiles = mutableMapOf()))
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun attachUser(userProfile: GithubUserModel) {

        _uiState.update { it.copy(
            profiles =  it.profiles + mutableMapOf( userProfile.login to userProfile )
        ) }

    }

    fun getExpandedUser(user: String) {

        viewModelScope.launch {

            if (uiState.value.expandedProfiles[user] == null) {

                val expandedUser = GithubRepositoryImpl().getSingleUser((user));

                _uiState.update {
                    it.copy(
                        expandedProfiles = it.expandedProfiles + mutableMapOf(user to expandedUser)
                    )
                }

            }

        }

    }

     fun getFollowers(searchString : String) {
        _uiState.update { it.copy(
            loading = true,
            searchComplete = false
        ) }

        viewModelScope.launch {
            // Trigger repository requests in parallel

            val data = GithubRepositoryImpl().getUser(searchString);
            println(data?.items?.get(0)?.login);

            _uiState.update {
                it.copy(
                    loading = false,
                    searchComplete = true
                )
            }
        }
    }

    fun resetSearchProgress() {
        _uiState.update {
            it.copy(
                loading = false,
                searchComplete = false
            )
        }
    }

    companion object {}

}