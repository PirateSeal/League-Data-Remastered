package com.example.myapplication.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import kotlinx.android.synthetic.main.historic_element.view.*

class HistoricAdapter(private val historics: ArrayList<Historic>) :
    RecyclerView.Adapter<HistoricAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profile_pic: ImageView;
        val kda: TextView;
        val rank_p: ImageView;

        init {
            profile_pic = view.historic_element_pp;
            kda = view.historic_element_kda;
            rank_p = view.historic_element_rank_pp;
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.historic_element, parent, false)
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(historics[position].profile_pic)
            .into(holder.profile_pic)

        Glide.with(holder.itemView)
            .load(historics[position].rank_p)
            .into(holder.rank_p);

        holder.kda.text = historics[position].kda;
    }

    override fun getItemCount(): Int {
        return historics.size
    }

}