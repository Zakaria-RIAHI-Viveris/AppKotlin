package com.viveris.appkotlin.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.viveris.appkotlin.R
import com.viveris.appkotlin.model.User
import com.viveris.appkotlin.ui.listener.IListUserListener
import kotlinx.android.synthetic.main.item_user.view.*

class UsersRecyclerAdapter(private val users: List<User>,
                           private val context: Context,
                           private val listener: IListUserListener) : RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

    override fun getItemCount() = users.size

    private fun getItem(position: Int) = users[position]

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(user: User?, listener: IListUserListener?) {
            itemView.apply {
                if (user != null) {
                    text_view_user_alias.text = user.displayName
                    val badgeCounts = user.badgeCounts
                    text_view_gold_number.text = badgeCounts!!.gold.toString()
                    text_view_silver_number.text = badgeCounts.silver.toString()
                    text_view_bronze_number.text = badgeCounts.bronze.toString()
                    view_item.setOnClickListener { listener?.onUserClicked(user) }
                }
            }

        }
    }
}
