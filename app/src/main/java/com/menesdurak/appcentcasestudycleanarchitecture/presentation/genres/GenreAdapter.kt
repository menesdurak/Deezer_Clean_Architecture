package com.menesdurak.appcentcasestudycleanarchitecture.presentation.genres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appcentcasestudycleanarchitecture.R
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.GenreUiData
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.ItemGenreBinding

class GenreAdapter(private val onItemClicked: (Int, String) -> Unit) :
    RecyclerView.Adapter<GenreAdapter.GenreHolder>() {

    private val itemList = mutableListOf<GenreUiData>()

    inner class GenreHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(genreUiData: GenreUiData) {
                binding.tvGenreName.text = genreUiData.name
                Glide
                    .with(binding.root.context)
                    .load(itemList[adapterPosition].picture)
                    .centerCrop()
                    .placeholder(R.drawable.loading)
                    .into(binding.ivGenre)
                binding.root.setOnClickListener {
                    onItemClicked.invoke(genreUiData.id, genreUiData.name)
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

    fun updateList(newList: List<GenreUiData>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }
}