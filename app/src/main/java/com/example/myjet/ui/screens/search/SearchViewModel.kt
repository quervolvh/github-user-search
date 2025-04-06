package com.example.myjet.ui.screens.search

import androidx.lifecycle.ViewModel
import com.example.myjet.src.models.GithubSearchModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class SearchUiState(
    val foundUsers: GithubSearchModel?,
)

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState(foundUsers = null))
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun setSearchedUsers(results : GithubSearchModel) {

        println("copying")
        println(results)

            _uiState.update {
                it.copy(

                    foundUsers = results

                )
            }
    }

    companion object {}

}