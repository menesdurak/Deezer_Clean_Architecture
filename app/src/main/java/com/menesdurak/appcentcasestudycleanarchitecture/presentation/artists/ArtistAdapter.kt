package com.menesdurak.appcentcasestudycleanarchitecture.presentation.artists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appcentcasestudycleanarchitecture.R
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.ArtistData
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.ItemArtistBinding

class ArtistAdapter(private val onItemClicked: (Int, String) -> Unit) :
    RecyclerView.Adapter<ArtistAdapter.ArtistHolder>() {

    private val itemList = mutableListOf<ArtistData>()

    inner class ArtistHolder(private val binding: ItemArtistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(artistData: ArtistData) {
            binding.tvArtistName.text = artistData.name
            Glide
                .with(binding.root.context)
                .load(itemList[adapterPosition].picture_medium)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(binding.ivArtist)
            binding.root.setOnClickListener {
                onItemClicked.invoke(artistData.id, artistData.name)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        val bind = ItemArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistHolder(bind)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun updateList(newList: List<ArtistData>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }
}