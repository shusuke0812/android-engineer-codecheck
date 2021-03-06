package com.example.androidengineercodecheck.Entity

import com.squareup.moshi.Json

/**
 *  GitHub Search APIのレスポンス型
 */
data class GitHubRepository (
    val items: List<Item>
)
data class Item (
    // リポジトリ名
    @Json(name = "full_name")
    val name: String,
    // リポジトリ言語
    @Json(name = "language")
    val language: String,
    // スター数
    @Json(name = "stargazers_count")
    val starNumber: Int,
    // ウォッチ数
    @Json(name = "watchers_count")
    val watchNumber: Int,
    // フォーク数
    @Json(name = "forks_count")
    val forkNumber: Int,
    // イシュー数
    @Json(name = "open_issues_count")
    val isueNumber: Int,
    // オーナー情報
    val owner: Owner
)
data class Owner (
    // 名前
    @Json(name = "login")
    val name: String,
    // アバター画像URL
    @Json(name = "avatar_url")
    val avatarImage: String
)