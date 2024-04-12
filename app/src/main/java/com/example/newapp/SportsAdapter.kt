package com.example.newapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SportsAdapter(private val sportsList: MutableList<Sport>) : RecyclerView.Adapter<SportsAdapter.SportViewHolder>() {

    class SportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSportType: TextView = itemView.findViewById(R.id.tvSportType)
        val tvSportName: TextView = itemView.findViewById(R.id.tvSportName)
        val tvSportInstructions: TextView = itemView.findViewById(R.id.tvSportInstruction)
    }
    fun updateSports(newSports: List<Sport>) {
        sportsList.clear()
        sportsList.addAll(newSports)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sports_item, parent, false)
        return SportViewHolder(view)
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        val sport = sportsList[position]
        with(holder) {
            tvSportType.text = sport.type
            tvSportName.text = sport.name
            tvSportInstructions.text = sport.instructions
        }
    }



    override fun getItemCount() = sportsList.size
}
