package com.menesdurak.appcentcasestudycleanarchitecture.presentation.artists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appcentcasestudycleanarchitecture.R
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.ArtistData

class ArtistAdapter: RecyclerView.Adapter<ArtistAdapter.ArtistHolder>() {

    private val itemList = mutableListOf<ArtistData>()

    inner class ArtistHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        return ArtistHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_artist, parent, false)
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvArtistName).text = itemList[position].name
        Glide
            .with(holder.itemView.context)
            .load(itemList[position].picture_medium)
            .centerCrop()
            .placeholder(R.drawable.loading)
            .into(holder.itemView.findViewById(R.id.ivArtist))
    }

    fun updateList(newList: List<ArtistData>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }
}