package jp.co.yumemi.android.code_check.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * API Doc: https://docs.github.com/ja/rest/search/search?apiVersion=2022-11-28#search-repositories
 * 各プロパティの型（null or not）は API Doc の応答スキーマを参照
 * */
@Parcelize
data class Owner(
    @Json(name = "login")
    val login: String,

    @Json(name = "avatar_url")
    val avatarUrl: String
) : Parcelable
