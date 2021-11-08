package com.gooseok.sample.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gooseok.sample.databinding.ItemGitUserInfoBinding
import com.gooseok.sample.network.GitUserInfo

class GitUserListAdapter(var callback:(user : GitUserInfo) -> Unit) : ListAdapter<GitUserInfo,GitUserListAdapter.ViewHolder>(GitUserInfoListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitUserListAdapter.ViewHolder {
        return ViewHolder(ItemGitUserInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userInfo = getItem(position)
        Log.d("JTest","userInfo : ${userInfo}, position : $position")
        holder.bind(View.OnClickListener { callback(userInfo) }, userInfo)
    }

    class ViewHolder(val binding: ItemGitUserInfoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(mCLickListener : View.OnClickListener, mUserInfo : GitUserInfo){
            binding.apply {
                this.clickListener = mCLickListener
                this.user = mUserInfo
            }
        }
    }
}

private class GitUserInfoListDiffCallback : DiffUtil.ItemCallback<GitUserInfo>(){
    override fun areItemsTheSame(oldItem: GitUserInfo, newItem: GitUserInfo): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: GitUserInfo, newItem: GitUserInfo): Boolean {
        return true
    }
}