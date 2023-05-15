package com.menesdurak.appcentcasestudycleanarchitecture.presentation.tracks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.TrackData
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.ItemTrackBinding

class TrackAdapter(private val onItemClicked: (Int) -> Unit) :
    RecyclerView.Adapter<TrackAdapter.TrackHolder>() {

    private val itemList = mutableListOf<TrackData>()

    inner class TrackHolder(private val binding: ItemTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(trackData: TrackData) {
            binding.tvTrackName.text = trackData.title
//            Glide
//                .with(binding.root.context)
//                .load(itemList[adapterPosition].picture_medium)
//                .centerCrop()
//                .placeholder(R.drawable.loading)
//                .into(binding.ivGenre)
            binding.root.setOnClickListener {
                onItemClicked.invoke(trackData.id)
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

    fun updateList(newList: List<TrackData>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }
}