/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.screen.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.FragmentRepositoryDetailBinding
import jp.co.yumemi.android.code_check.screen.search.SearchRepositoryViewModel

/**
 * GitHubのリポジトリ詳細を表示する Fragment
 */
class RepositoryDetailFragment : Fragment(R.layout.fragment_repository_detail) {

    private val args: RepositoryDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentRepositoryDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("debug", "lastSearchDate=" + SearchRepositoryViewModel.lastSearchDate)

        binding = FragmentRepositoryDetailBinding.bind(view)

        var repository = args.repository
        val languageWithPrefix = context?.getString(R.string.written_language, repository.language)

        binding.ownerIconView.load(repository.owner?.avatarUrl)
        binding.nameView.text = repository.fullName;
        binding.languageView.text = languageWithPrefix;
        binding.starsView.text = "${repository.stargazersCount} stars";
        binding.watchersView.text = "${repository.watchersCount} watchers";
        binding.forksView.text = "${repository.forksCount} forks";
        binding.openIssuesView.text = "${repository.openIssuesCount} open issues";
    }
}
