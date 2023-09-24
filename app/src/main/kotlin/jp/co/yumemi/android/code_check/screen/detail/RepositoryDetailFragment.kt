/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.screen.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.FragmentRepositoryDetailBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 * GitHubのリポジトリ詳細を表示する Fragment
 */
class RepositoryDetailFragment : Fragment(R.layout.fragment_repository_detail) {

    private val args: RepositoryDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentRepositoryDetailBinding
    private val viewModel: RepositoryDetailViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRepositoryDetailBinding.bind(view)

        val repository = args.repository
        val languageWithPrefix = context?.getString(R.string.written_language, repository.language)

        binding.ownerIconView.load(repository.owner?.avatarUrl)
        binding.nameView.text = repository.fullName;
        binding.languageView.text = languageWithPrefix;
        binding.starsView.text = "${repository.stargazersCount} stars";
        binding.watchersView.text = "${repository.watchersCount} watchers";
        binding.forksView.text = "${repository.forksCount} forks";
        binding.openIssuesView.text = "${repository.openIssuesCount} open issues";

        viewModel.getWatchersCount(repository.owner?.login ?: "", repository.name)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                if (!uiState.isLoading && uiState.watchersCount != null) {
                    binding.watchersView.text = "${uiState.watchersCount} watchers"
                }
            }
        }
    }
}
