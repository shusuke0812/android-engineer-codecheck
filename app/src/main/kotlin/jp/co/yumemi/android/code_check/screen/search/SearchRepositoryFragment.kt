/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.screen.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.FragmentSearchRepositoryBinding
import jp.co.yumemi.android.code_check.model.Repository

/**
 * GitHubのリポジトリ検索結果を一覧表示する Fragment
 */
class SearchRepositoryFragment : Fragment(R.layout.fragment_search_repository) {

    private var _binding: FragmentSearchRepositoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchRepositoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSearchRepositoryBinding.bind(view)

        val layoutManager= LinearLayoutManager(context!!)
        val dividerItemDecoration = DividerItemDecoration(context!!, layoutManager.orientation)

        val adapter = CustomAdapter(object: CustomAdapter.OnItemClickListener {
            override fun itemClick(repository: Repository) {
                gotoRepositoryFragment(repository)
            }
        })

        binding.searchInputText.setOnEditorActionListener { editText, action, _ ->
            if (action == EditorInfo.IME_ACTION_SEARCH) {
                editText.text.toString().let {
                    viewModel.searchResults(it).apply {
                        adapter.submitList(this)
                    }
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
    }

    fun gotoRepositoryFragment(repository: Repository) {
        val action = SearchRepositoryFragmentDirections.actionRepositoriesFragmentToRepositoryFragment(repository = repository)
        findNavController().navigate(action)
    }
}

val diffUtil = object: DiffUtil.ItemCallback<Repository>() {

    override fun areItemsTheSame(oldRepository: Repository, newRepository: Repository): Boolean {
        return oldRepository.name == newRepository.name
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
        (holder.itemView.findViewById<View>(R.id.repositoryNameView) as TextView).text = item.name

    	holder.itemView.setOnClickListener {
     		itemClickListener.itemClick(item)
    	}
    }
}
