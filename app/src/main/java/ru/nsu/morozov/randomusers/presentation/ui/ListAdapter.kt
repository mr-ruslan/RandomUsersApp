package ru.nsu.morozov.randomusers.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.nsu.morozov.randomusers.databinding.UserCardBinding
import ru.nsu.morozov.randomusers.domain.entity.User

class UsersListAdapter(
    private var onMore: (User) -> Unit,
) : ListAdapter<User, UsersListAdapter.ListViewHolder>(UserDiffCallback()) {

    class ListViewHolder(
        private val binding: UserCardBinding,
        private val onMore: (User) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            with(binding) {
                userName.text = user.name
                userAge.text = user.age.toString()
                userEmail.text = user.email
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        UserCardBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onMore,
    )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
