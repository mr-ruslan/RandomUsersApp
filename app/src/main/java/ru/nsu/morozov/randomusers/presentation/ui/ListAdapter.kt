package ru.nsu.morozov.randomusers.presentation.ui

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.nsu.morozov.randomusers.R
import ru.nsu.morozov.randomusers.databinding.UserCardBinding
import ru.nsu.morozov.randomusers.domain.entity.User
import kotlin.coroutines.coroutineContext

class UsersListAdapter(
    private var onSelect: (User) -> Unit,
) : ListAdapter<User, UsersListAdapter.ListViewHolder>(UserDiffCallback()) {

    class ListViewHolder(
        private val binding: UserCardBinding,
        private val onSelect: (User) -> Unit,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            with(binding) {
                userName.text =  context.getString(
                    R.string.full_name_template,
                    user.nameTitle,
                    user.firstName,
                    user.lastName
                )
                userAddress.text = context.getString(
                    R.string.full_address_template,
                    user.country,
                    user.state,
                    user.city,
                    user.street,
                    user.house
                )
                userPhone.text = user.phone
                root.setOnClickListener {
                    onSelect(user)
                }
                Glide.with(userImage.context)
                    .load(user.image)
                    //.placeholder(R.drawable.loading_placeholder)
                    //.error(R.drawable.loading_placeholder)
                    .apply(
                        RequestOptions.bitmapTransform(
                            RoundedCorners(
                                TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    12f,
                                    Resources.getSystem().displayMetrics
                                ).toInt()
                            )
                        )
                    )
                    .into(userImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        UserCardBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onSelect,
        parent.context
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
