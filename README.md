# Android エンジニアコードチェック課題

## 概要

本リポジトリは [iOSエンジニアコードチェック](https://github.com/shusuke0812/ios-engineer-codecheck) の課題を参考に作ったGitHubのリポジトリを検索するAndroidアプリである。

## アプリ仕様

本アプリは GitHub のリポジトリーを検索するアプリです。

![動作イメージ](README_Images/app.gif)

### 環境

- IDE：基本最新の安定版（本概要作成時点では Androit Studio ）
- Kotlin：基本最新の安定版（本概要作成時点では Swift 5.1）

### 動作

1. 何かしらのキーワードを入力
2. GitHub API（`search/repositories`）でリポジトリーを検索し、結果一覧を概要（リポジトリ名）で表示
3. 特定の結果を選択したら、該当リポジトリの詳細（リポジトリ名、オーナーアイコン、プロジェクト言語、Star 数、Watcher 数、Fork 数、Issue 数）を表示

## 課題取り組み方法

[課題](https://github.com/shusuke0812/ios-engineer-codecheck/projects/1)を参考に、
- 
