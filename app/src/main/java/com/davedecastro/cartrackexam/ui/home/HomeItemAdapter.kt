package com.davedecastro.cartrackexam.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.davedecastro.cartrackexam.R
import com.davedecastro.cartrackexam.data.db.entities.User
import com.davedecastro.cartrackexam.databinding.ItemUserBinding
import java.lang.Exception

class HomeItemAdapter : ListAdapter<User, HomeItemAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem
    }
) {

    private var onItemClickListener: (User) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_user,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val item = currentList[holder.adapterPosition]

            holder.binding.root.setOnClickListener {
                onItemClickListener.invoke(item)
            }

            holder.binding.user = item

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setOnItemClickListener(onItemClickListener: (User) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
}