/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.screen.search

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.FragmentSearchRepositoryBinding
import jp.co.yumemi.android.code_check.model.Repository
import jp.co.yumemi.android.code_check.utility.extension.closeKeyboard
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 * GitHubのリポジトリ検索結果を一覧表示する Fragment
 */
class SearchRepositoryFragment : Fragment(R.layout.fragment_search_repository) {

    private val viewModel: SearchRepositoryViewModel by inject()
    private lateinit var adapter: SearchRepositoryItemAdapter
    private lateinit var binding: FragmentSearchRepositoryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchRepositoryBinding.bind(view)

        val layoutManager= LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)

        adapter = SearchRepositoryItemAdapter(object: SearchRepositoryItemAdapter.OnItemClickListener {
            override fun itemClick(repository: Repository) {
                gotoRepositoryDetailFragment(repository)
            }
        })

        binding.searchInputText.setOnEditorActionListener { editText, action, event ->
            if (action == EditorInfo.IME_ACTION_SEARCH || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                editText.text.toString().let {
                    viewModel.searchRepositories(inputText = it)
                }
                this.closeKeyboard()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        binding.recyclerView.also {
            it.layoutManager = layoutManager
            it.addItemDecoration(dividerItemDecoration)
            it.adapter = adapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                if (!uiState.isLoading) {
                    adapter.submitList(uiState.items)
                }
            }
        }
    }

    private fun gotoRepositoryDetailFragment(repository: Repository) {
        val action = SearchRepositoryFragmentDirections.actionRepositoriesFragmentToRepositoryFragment(repository = repository)
        findNavController().navigate(action)
    }
}
