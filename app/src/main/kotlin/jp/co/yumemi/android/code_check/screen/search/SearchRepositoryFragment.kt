/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.screen.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.FragmentSearchRepositoryBinding
import jp.co.yumemi.android.code_check.model.Repository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * GitHubのリポジトリ検索結果を一覧表示する Fragment
 */
class SearchRepositoryFragment : Fragment(R.layout.fragment_search_repository) {

    private val viewModel: SearchRepositoryViewModel by viewModels()
    private lateinit var adapter: CustomAdapter
    private lateinit var binding: FragmentSearchRepositoryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchRepositoryBinding.bind(view)

        val layoutManager= LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)

        adapter = CustomAdapter(object: CustomAdapter.OnItemClickListener {
            override fun itemClick(repository: Repository) {
                gotoRepositoryDetailFragment(repository)
            }
        })

        binding.searchInputText.setOnEditorActionListener { editText, action, event ->
            if (action == EditorInfo.IME_ACTION_SEARCH || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                editText.text.toString().let {
                    viewModel.searchRepositories(inputText = it)
                }
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

// TODO: この行以降に記載されたRecyclerViewに関する設定コードは別ファイルに切り出したい( search/adapterディレクトリ配下 )
val diffUtil = object: DiffUtil.ItemCallback<Repository>() {

    override fun areItemsTheSame(oldRepository: Repository, newRepository: Repository): Boolean {
        return oldRepository.fullName == newRepository.fullName
    }

    override fun areContentsTheSame(oldRepository: Repository, newRepository: Repository): Boolean {
        return oldRepository == newRepository
    }
}

class CustomAdapter(private val itemClickListener: OnItemClickListener, ) : ListAdapter<Repository, CustomAdapter.ViewHolder>(diffUtil) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnItemClickListener {
    	fun itemClick(repository: Repository)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
    	val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
    	return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    	val item = getItem(position)
        val textView = holder.itemView.findViewById<TextView>(R.id.repositoryNameView)
        textView.text = item.fullName

    	holder.itemView.setOnClickListener {
     		itemClickListener.itemClick(item)
    	}
    }
}
