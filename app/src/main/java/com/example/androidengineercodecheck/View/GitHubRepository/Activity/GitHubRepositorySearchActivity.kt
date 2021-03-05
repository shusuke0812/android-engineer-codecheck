package com.example.androidengineercodecheck.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidengineercodecheck.R
import com.example.androidengineercodecheck.ViewModel.GitHubRepositorySearchViewModel

class GitHubRepositorySearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_repository_search)
    }
}