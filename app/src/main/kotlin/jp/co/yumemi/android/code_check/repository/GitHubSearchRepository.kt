package jp.co.yumemi.android.code_check.repository

import jp.co.yumemi.android.code_check.constant.Constant
import jp.co.yumemi.android.code_check.model.SearchResponse
import jp.co.yumemi.android.code_check.network.GitHubInterface
import jp.co.yumemi.android.code_check.network.GitHubAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * API Doc: https://docs.github.com/ja/rest/search/search?apiVersion=2022-11-28#search-repositories
 * */
interface GitHubSearchRepositoryInterface : GitHubInterface {
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") q: String
    ) : Response<SearchResponse>
}

class GitHubSearchRepository {
    private val _api = GitHubAPIService.create<GitHubSearchRepositoryInterface>(baseUrl = Constant.GITHUB_BASE_URL)

    suspend fun getRepositories(inputText: String): Response<SearchResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext _api.getRepositories(q = inputText)
        }
    }
}