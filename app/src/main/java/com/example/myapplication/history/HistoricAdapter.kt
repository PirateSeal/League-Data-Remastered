package com.example.myapplication.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.database.DataStorage
import kotlinx.android.synthetic.main.historic_element.view.*

class HistoricAdapter(private val historics: ArrayList<Historic>) :
    RecyclerView.Adapter<HistoricAdapter.ViewHolder>() {

    private lateinit var dataStorage: DataStorage

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profilePic: ImageView;
        val kda: TextView;
        val rankP: ImageView;

        init {
            profilePic = view.historic_element_champion_pic;
            kda = view.historic_element_kda;
            rankP = view.historic_element_rank_pp;
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
        dataStorage = DataStorage(holder.itemView.context)
        val version = dataStorage.getString("patch")



        /*val profilIconUrl =
            "${BuildConfig.CDN_URL}cdn/$version/img/profileicon/${summoner.profileIconId}.png"


        Glide.with(holder.itemView)
            .load(historics[position].champion_pic)
            .into(holder.profile_pic)

        Glide.with(holder.itemView)
            .load(historics[position].rank_p)
            .into(holder.rank_p);

        holder.kda.text = historics[position].kda;*/
    }

    override fun getItemCount(): Int {
        return historics.size
    }

}