package jp.co.yumemi.android.code_check.screen.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.*
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.model.Repository

class SearchRepositoryItemAdapter(
    private val itemClickListener: OnItemClickListener
) : ListAdapter<Repository, SearchRepositoryItemAdapter.ViewHolder>(diffUtil) {

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
        val repositoryNameTextView = holder.itemView.findViewById<TextView>(R.id.repositoryName)
        repositoryNameTextView.text = item.fullName

        val repositoryDescriptionTextView = holder.itemView.findViewById<TextView>(R.id.repositoryDescription)
        repositoryDescriptionTextView.text = item.description

        holder.itemView.setOnClickListener {
            itemClickListener.itemClick(item)
        }
    }
}

private val diffUtil = object: DiffUtil.ItemCallback<Repository>() {

    override fun areItemsTheSame(oldRepository: Repository, newRepository: Repository): Boolean {
        return oldRepository.fullName == newRepository.fullName
    }

    override fun areContentsTheSame(oldRepository: Repository, newRepository: Repository): Boolean {
        return oldRepository == newRepository
    }
}