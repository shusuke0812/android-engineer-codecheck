package jp.co.yumemi.android.code_check.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * API Doc: https://docs.github.com/ja/rest/search/search?apiVersion=2022-11-28#search-repositories
 * 各プロパティの型（null or not）は API Doc の応答スキーマを参照
 * */
@Parcelize
data class Repository(
    @Json(name = "name")
    val name: String,

    @Json(name = "full_name")
    val fullName: String,

    @Json(name = "owner")
    val owner: Owner?,

    @Json(name = "language")
    val language: String?,

    @Json(name = "stargazers_count")
    val stargazersCount: Long,

    @Json(name = "watchers_count")
    val watchersCount: Long,

    @Json(name = "forks_count")
    val forksCount: Long,

    @Json(name = "open_issues_count")
    val openIssuesCount: Long,

    @Json(name = "description")
    val description: String
) : Parcelable