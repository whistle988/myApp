package com.example.quwiclient.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quwiclient.R
import com.example.quwiclient.model.Users
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*

class UsersListAdapter (var userList: List<Users>) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setUsersList(userList: List<Users>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Users) {
            view.userName.text = item.name

            Picasso.get()
                .load(item.avatarURL.toString())
                .placeholder(R.drawable.pngegg)
                .error(R.drawable.pngegg)
                .into(view.userImage)

            if(item.isOnline == 0.toLong()) {
                view.userCheckbox.isChecked = false
            } else {
                view.userCheckbox.isChecked = true
            }

        }
    }

}
