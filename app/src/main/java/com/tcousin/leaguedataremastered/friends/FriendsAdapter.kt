package com.tcousin.leaguedataremastered.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tcousin.leaguedataremastered.R
import kotlinx.android.synthetic.main.friends_element.view.*

class FriendsAdapter(private val friends: ArrayList<Friend>) :
    RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val f_pp: ImageView
        val f_rank: ImageView
        val f_name: TextView

        init {
            f_pp = view.friends_element_pp
            f_name = view.friends_element_name
            f_rank = view.friends_element_rank_pp
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.friends_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.f_name.text = friends[position].name
        Glide.with(holder.itemView)
            .load(friends[position].profile_picture)
            .into(holder.f_pp)

        Glide.with(holder.itemView)
            .load(friends[position].rank)
            .into(holder.f_rank)
    }

    override fun getItemCount(): Int {
        return friends.size
    }
}