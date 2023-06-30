package jp.co.yumemi.android.code_check.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResponse(
    @Json(name = "total_count")
    val totalCount: Int,

    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,

    @Json(name = "items")
    val items: List<Repository>
) : Parcelable
@Parcelize
data class Repository(
    @Json(name = "full_name")
    val fullName: String,

    @Json(name = "owner")
    val owner: Owner,

    @Json(name = "language")
    val language: String,

    @Json(name = "stargazers_count")
    val stargazersCount: Long,

    @Json(name = "watchers_count")
    val watchersCount: Long,

    @Json(name = "forks_count")
    val forksCount: Long,

    @Json(name = "open_issues_count")
    val openIssuesCount: Long,
) : Parcelable
@Parcelize
data class Owner(
    @Json(name = "avatar_url")
    val avatarUrl: String
) : Parcelable