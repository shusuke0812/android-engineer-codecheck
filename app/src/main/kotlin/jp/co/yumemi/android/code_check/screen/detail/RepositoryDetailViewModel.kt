package jp.co.yumemi.android.code_check.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.android.code_check.usecase.GetWatchersCountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepositoryDetailViewModel(
    private val getWatchersCountUseCase: GetWatchersCountUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(RepositoryDetailUiState())
    val uiState: StateFlow<RepositoryDetailUiState> = _uiState

    fun getWatchersCount(ownerName: String, reposName: String) {
        viewModelScope.launch {
            val watchersCount = getWatchersCountUseCase(ownerName, reposName)
            _uiState.update { currentUiState ->
                currentUiState.copy(watchersCount = watchersCount, isLoading = false)
            }
        }
    }
}

data class RepositoryDetailUiState(
    val isLoading: Boolean = true,
    val watchersCount: Int? = null,
    val errorMessage: String? = null
)