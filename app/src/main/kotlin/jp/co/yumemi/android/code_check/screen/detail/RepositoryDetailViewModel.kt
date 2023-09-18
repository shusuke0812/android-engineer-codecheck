package jp.co.yumemi.android.code_check.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.android.code_check.usecase.WatchersCountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepositoryDetailViewModel(
    private val watchersCountUseCase: WatchersCountUseCase,
    private val fullName: String
) : ViewModel() {
    private val _uiState = MutableStateFlow(RepositoryDetailUiState())
    val uiState: StateFlow<RepositoryDetailUiState> = _uiState

    init {
        viewModelScope.launch {
            val watchersCount = watchersCountUseCase(fullName = fullName)
            _uiState.update { currentUiState ->
                currentUiState.copy(watchersCount = watchersCount)
            }
        }
    }
}

data class RepositoryDetailUiState(
    val isLoading: Boolean = false,
    val watchersCount: Int? = null,
    val errorMessage: String? = null
)