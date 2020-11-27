package com.example.myapplication.history

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HistoricAdapter(private val historics: ArrayList<Historic>): RecyclerView.Adapter<HistoricAdapter.HistoricViewHolder>() {

    class HistoricViewHolder(view: RecyclerView): RecyclerView.ViewHolder (view){

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoricViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HistoricViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
      return historics.size
    }

}