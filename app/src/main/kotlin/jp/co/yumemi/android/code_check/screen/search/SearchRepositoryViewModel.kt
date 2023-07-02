/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.screen.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.utils.io.errors.IOException
import jp.co.yumemi.android.code_check.model.Repository
import jp.co.yumemi.android.code_check.repository.GitHubSearchRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.Date

interface SearchRepositoryViewModelDelegate {
    fun didSuccessSearchRepositories(items: List<Repository>?)
    fun didFailSearchRepositories()
}

/**
 * GitHubのリポジトリ検索結果を保持する ViewModel
 */
class SearchRepositoryViewModel(
    var delegate: SearchRepositoryViewModelDelegate? = null,
    private val repository: GitHubSearchRepository = GitHubSearchRepository()
) : ViewModel() {
    companion object {
        val lastSearchDate: Date get() { return _lastSearchDate }
        private var _lastSearchDate: Date = Date()
    }

    fun searchRepositories(inputText: String) {
        viewModelScope.launch {
            try {
                val response = repository.getRepositories(inputText = inputText)
                if (response.isSuccessful) {
                    val items = response.body()?.items
                    _lastSearchDate = Date()
                    delegate?.didSuccessSearchRepositories(items = items)
                    Log.d("debug", "items=$items")
                } else {
                    delegate?.didFailSearchRepositories()
                }
            } catch (e: IOException) {
                delegate?.didFailSearchRepositories()
            } catch (e: HttpException) {
                delegate?.didFailSearchRepositories()
            }
        }
    }
}

