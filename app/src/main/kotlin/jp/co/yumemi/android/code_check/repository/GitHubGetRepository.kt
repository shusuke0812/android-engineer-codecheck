package jp.co.yumemi.android.code_check.repository

import jp.co.yumemi.android.code_check.constant.Constant
import jp.co.yumemi.android.code_check.model.GetRepository
import jp.co.yumemi.android.code_check.network.GitHubAPIService
import jp.co.yumemi.android.code_check.network.GitHubInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API Doc: https://docs.github.com/ja/rest/repos/repos?apiVersion=2022-11-28#get-a-repository
 * */

// NOTE: full_name = /owner_name/repository_name
interface GitHubGetRepositoryInterface : GitHubInterface {
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/repos/{full_name}")
    suspend fun getRepositoryWatchersCount(
        @Path("full_name") fullName: String
    ) : Response<GetRepository>

}

class GitHubGetRepository {
    private val _api = GitHubAPIService.create<GitHubGetRepositoryInterface>(baseUrl = Constant.GITHUB_BASE_URL)

    suspend fun getRepository(fullName: String): Response<GetRepository> {
        return withContext(Dispatchers.IO) {
            return@withContext _api.getRepositoryWatchersCount(fullName = fullName)
        }
    }
}