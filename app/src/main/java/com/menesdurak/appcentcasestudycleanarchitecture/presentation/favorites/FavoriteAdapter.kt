package com.menesdurak.appcentcasestudycleanarchitecture.presentation.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appcentcasestudycleanarchitecture.R
import com.menesdurak.appcentcasestudycleanarchitecture.data.local.entity.FavoriteTrack
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.ItemTrackBinding

class FavoriteAdapter(private val onItemClicked: (FavoriteTrack) -> Unit) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>() {

    private val itemList = mutableListOf<FavoriteTrack>()

    inner class FavoriteHolder(private val binding: ItemTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favoriteTrack: FavoriteTrack) {
            binding.tvTrackName.text = favoriteTrack.name
            Glide
                .with(binding.root.context)
                .load(itemList[adapterPosition].image)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(binding.ivTrack)
            binding.root.setOnClickListener {
                onItemClicked.invoke(favoriteTrack)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val bind = ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteHolder(bind)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun updateList(newList: List<FavoriteTrack>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }
}