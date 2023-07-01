package jp.co.yumemi.android.code_check.repository

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import jp.co.yumemi.android.code_check.constant.Constant
import jp.co.yumemi.android.code_check.model.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * API Doc: https://docs.github.com/ja/rest/search/search?apiVersion=2022-11-28#search-repositories
 * */
interface GitHubRepositoryInterface {
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") q: String
    ) : Response<SearchResponse>
}

class GitHubSearchRepository {
    private val api: GitHubRepositoryInterface by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.GITHUB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        retrofit.create(GitHubRepositoryInterface::class.java)
    }

    suspend fun getRepositories(inputText: String): Response<SearchResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext api.getRepositories(q = inputText)
        }
    }
}