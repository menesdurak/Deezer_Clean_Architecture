package com.menesdurak.appcentcasestudycleanarchitecture.presentation.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appcentcasestudycleanarchitecture.R
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.AlbumData
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.ItemAlbumBinding

class AlbumAdapter (private val onItemClicked: (Int, String) -> Unit) :
    RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() {

    private val itemList = mutableListOf<AlbumData>()

    inner class AlbumHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(albumData: AlbumData) {
            binding.tvAlbumName.text = albumData.title
            Glide
                .with(binding.root.context)
                .load(itemList[adapterPosition].cover_medium)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(binding.ivAlbum)
            binding.root.setOnClickListener {
                onItemClicked.invoke(albumData.id, albumData.title)
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

    fun updateList(newList: List<AlbumData>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }
}