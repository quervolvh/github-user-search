package com.example.myjet.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjet.src.models.GithubSearchModel
import com.example.myjet.src.repository.github.impl.GithubRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val searchComplete: Boolean = false,
    val loading: Boolean = false,
    val foundUsers: GithubSearchModel?,
)

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(loading = false, foundUsers = null))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

     fun searchUser(searchString : String) {
        _uiState.update { it.copy(
            loading = true,
            searchComplete = false
        ) }

        viewModelScope.launch {

            val data = GithubRepositoryImpl().getUser(searchString);

            _uiState.update {
                it.copy(
                    loading = true,
                    foundUsers = data,
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