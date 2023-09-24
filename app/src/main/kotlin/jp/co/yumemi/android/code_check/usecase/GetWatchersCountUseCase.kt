package jp.co.yumemi.android.code_check.usecase

import android.util.Log
import jp.co.yumemi.android.code_check.repository.GitHubGetRepository

class WatchersCountUseCase(
    private val gitHubGetRepository: GitHubGetRepository = GitHubGetRepository()
) {
    suspend operator fun invoke(fullName: String): Int? {
        val response = gitHubGetRepository.getRepository(fullName = fullName)
        return if (response.isSuccessful) {
            response.body()?.subscribersCount
        } else {
            null
        }
    }
}