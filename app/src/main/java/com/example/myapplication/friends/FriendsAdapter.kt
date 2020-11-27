package com.example.myapplication.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.friends_element.view.*

class FriendsAdapter (private val friends: ArrayList<Friend>): RecyclerView.Adapter<FriendsAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val f_pp: ImageView;
        val f_rank: ImageView;
        val f_name: TextView;

        init {
            f_pp = view.friends_element_pp
            f_name = view.friends_element_name
            f_rank = view.friends_element_rank_pp;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view  = LayoutInflater.from(parent.context)
           .inflate(R.layout.friends_element, parent, false)

        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.f_name.text = friends[position].name;
    }

    override fun getItemCount(): Int {
        return friends.size;
    }
}