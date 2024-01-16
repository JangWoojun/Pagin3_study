package com.woojun.pagin3_study

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.woojun.pagin3_study.databinding.RowPagingBinding


class UserAdapter : PagingDataAdapter<User, UserViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            RowPagingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User) =
                oldItem == newItem
        }
    }
}

class UserViewHolder(private val binding: RowPagingBinding) : RecyclerView.ViewHolder(binding.root) {
    var user:User? = null

    fun bind(item: User?) {
        item?.let {
            binding.id.text = "id : ${it.id}"
            binding.name.text = "${it.firstName} ${it.lastName}"
            binding.email.text = it.email
            Glide.with(binding.avatar.context)
                .load(it.avatar)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.avatar)
            user = item
        }
    }
}