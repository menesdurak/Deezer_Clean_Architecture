package com.menesdurak.appcentcasestudycleanarchitecture.presentation.tracks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appcentcasestudycleanarchitecture.R
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.TrackUiData
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.ItemTrackBinding

class TrackAdapter(
    private val onPlayClicked: (Long) -> Unit,
    private val onFavoriteClicked: (TrackUiData, Int) -> Unit,
    private val onFavoriteUpdate: (TrackUiData) -> Unit
) : RecyclerView.Adapter<TrackAdapter.TrackHolder>() {

    private val itemList = mutableListOf<TrackUiData>()
    private val favoriteTracksIdList = mutableListOf<Long>()

    inner class TrackHolder(private val binding: ItemTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(trackUiData: TrackUiData) {
            binding.tvTrackName.text = trackUiData.title
            Glide
                .with(binding.root.context)
                .load(trackUiData.image)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(binding.ivTrack)
            if (trackUiData.id in favoriteTracksIdList) {
                trackUiData.isFavorite = true
                binding.ivFavorite.setImageResource(R.drawable.ic_favorite_filled)
            } else {
                trackUiData.isFavorite = false
                binding.ivFavorite.setImageResource(R.drawable.ic_favorite_empty)
            }
            binding.root.setOnClickListener {
                onPlayClicked.invoke(trackUiData.id)
            }
            binding.ivFavorite.setOnClickListener {
                onFavoriteClicked(trackUiData, adapterPosition)
                onFavoriteUpdate(trackUiData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackHolder {
        val bind = ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrackHolder(bind)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: TrackHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun updateList(newList: List<TrackUiData>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    fun updateFavoriteTracksIdList(newFavoriteTracksIdList: List<Long>) {
        favoriteTracksIdList.clear()
        favoriteTracksIdList.addAll(newFavoriteTracksIdList)
        notifyDataSetChanged()
    }

    fun updateFavoriteStatusOfTrack(position: Int, id: Long) {
        if (id !in favoriteTracksIdList) {
            favoriteTracksIdList.add(id)
        } else {
            favoriteTracksIdList.remove(id)
        }
        itemList[position].isFavorite = !itemList[position].isFavorite
        notifyItemChanged(position)
    }
}