package com.menesdurak.appcentcasestudycleanarchitecture.presentation.tracks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appcentcasestudycleanarchitecture.R
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.TrackUiData
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.ItemTrackBinding

class TrackAdapter(private val onItemClicked: (Long) -> Unit) :
    RecyclerView.Adapter<TrackAdapter.TrackHolder>() {

    private val itemList = mutableListOf<TrackUiData>()

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
            binding.root.setOnClickListener {
                onItemClicked.invoke(trackUiData.id)
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

}