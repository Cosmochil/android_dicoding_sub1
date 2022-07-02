package com.example.dicodinggitprofile.ui.main

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.dicodinggitprofile.R
import com.example.dicodinggitprofile.databinding.ActivityMainBinding
import com.example.dicodinggitprofile.databinding.GitUserRowBinding
import com.example.dicodinggitprofile.model.Users
import kotlinx.android.synthetic.main.git_user_row.view.*

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private val list = ArrayList<Users>()

    fun setList(users: ArrayList<Users>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: GitUserRowBinding ): RecyclerView.ViewHolder(binding.root) {
        fun bind(users: Users) {
            binding.apply{
                Glide.with(itemView)
                    .load(users.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(avatar)
                tvItemName.text = users.login
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =  GitUserRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}