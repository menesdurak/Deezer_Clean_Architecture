package com.menesdurak.appcentcasestudycleanarchitecture.presentation.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appcentcasestudycleanarchitecture.R
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.AlbumUiData
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.ItemAlbumBinding

class AlbumAdapter(private val onItemClicked: (Int, String, String) -> Unit) :
    RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() {

    private val itemList = mutableListOf<AlbumUiData>()

    inner class AlbumHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(albumUiData: AlbumUiData) {
            binding.tvAlbumName.text = albumUiData.title
            Glide
                .with(binding.root.context)
                .load(itemList[adapterPosition].picture)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(binding.ivAlbum)
            binding.root.setOnClickListener {
                onItemClicked.invoke(albumUiData.id, albumUiData.title, albumUiData.picture)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val bind = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumHolder(bind)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun updateList(newList: List<AlbumUiData>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }
}