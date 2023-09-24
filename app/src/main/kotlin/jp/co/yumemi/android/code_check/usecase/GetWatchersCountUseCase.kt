package jp.co.yumemi.android.code_check.usecase

import jp.co.yumemi.android.code_check.repository.GitHubGetRepository

class GetWatchersCountUseCase(
    private val gitHubGetRepository: GitHubGetRepository = GitHubGetRepository()
) {
    suspend operator fun invoke(ownerName: String, reposName: String): Int? {
        val response = gitHubGetRepository.getRepository(ownerName, reposName)
        return if (response.isSuccessful) {
            response.body()?.subscribersCount
        } else {
            null
        }
    }
}