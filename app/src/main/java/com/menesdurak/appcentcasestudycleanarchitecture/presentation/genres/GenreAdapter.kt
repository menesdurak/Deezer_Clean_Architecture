package com.menesdurak.appcentcasestudycleanarchitecture.presentation.genres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appcentcasestudycleanarchitecture.R
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.GenreData
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.ItemGenreBinding

class GenreAdapter(private val onItemClicked: (Int, String) -> Unit) :
    RecyclerView.Adapter<GenreAdapter.GenreHolder>() {

    private val itemList = mutableListOf<GenreData>()

    inner class GenreHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(genreData: GenreData) {
                binding.tvGenreName.text = genreData.name
                Glide
                    .with(binding.root.context)
                    .load(itemList[adapterPosition].picture_medium)
                    .centerCrop()
                    .placeholder(R.drawable.loading)
                    .into(binding.ivGenre)
                binding.root.setOnClickListener {
                    onItemClicked.invoke(genreData.id, genreData.name)
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder {
        val bind = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreHolder(bind)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun updateList(newList: List<GenreData>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }
}