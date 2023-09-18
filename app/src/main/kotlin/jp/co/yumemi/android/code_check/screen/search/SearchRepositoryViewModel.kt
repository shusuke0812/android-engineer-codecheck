/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.utils.io.errors.IOException
import jp.co.yumemi.android.code_check.model.Repository
import jp.co.yumemi.android.code_check.repository.GitHubSearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * GitHubのリポジトリ検索結果を保持する ViewModel
 */
class SearchRepositoryViewModel(
    private val repository: GitHubSearchRepository = GitHubSearchRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchRepositoryListUiState())
    val uiState: StateFlow<SearchRepositoryListUiState> = _uiState
    fun searchRepositories(inputText: String) {
        viewModelScope.launch {
            try {
                val response = repository.getRepositories(inputText = inputText)
                if (response.isSuccessful) {
                    _uiState.update { currentUiState ->
                        currentUiState.copy(
                            items = response.body()?.items ?: emptyList(),
                            isLoading = false
                        )
                    }
                    return@launch
                } else {
                    _uiState.update { currentUiState ->
                        currentUiState.copy(
                            errorMessage = "Not found repositories",
                            isLoading = false
                        )
                    }
                    return@launch
                }
            } catch (e: IOException) {
                // do nothing
            } catch (e: HttpException) {
                // do nothing
            }
        }
    }
}

data class SearchRepositoryListUiState(
    val isLoading: Boolean = false,
    val items: List<Repository> = emptyList(),
    val errorMessage: String? = null
)
data class SearchRepositoryUiState(
    val repository: Repository,
    val onClickItem: () -> Unit
)
