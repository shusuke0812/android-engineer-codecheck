package com.example.androidengineercodecheck.Entity

import com.squareup.moshi.Json

data class GitHubRepository (
    var items: List<Item>
)
data class Item (
    // リポジトリ名
    @Json(name = "full_name")
    var name: String,
    // リポジトリ言語
    @Json(name = "language")
    var language: String,
    // スター数
    @Json(name = "stargazers_count")
    var starNumber: Int,
    // ウォッチ数
    @Json(name = "watchers_count")
    var watchNumber: Int,
    // フォーク数
    @Json(name = "forks_count")
    var forkNumber: Int,
    // イシュー数
    @Json(name = "open_issues_ccount")
    var isueNumber: Int,
    // オーナー情報
    var owner: Owner
)
data class Owner (
    // アバター画像URL
    @Json(name = "avatar_url")
    var avatarImage: String
)