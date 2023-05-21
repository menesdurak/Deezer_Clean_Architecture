package com.menesdurak.appcentcasestudycleanarchitecture.presentation.favorites

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.data.local.entity.FavoriteTrack
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private val favoriteAdapter by lazy { FavoriteAdapter(::onItemClick, ::onFavoriteClick) }
    private val mediaPlayer: MediaPlayer = MediaPlayer()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesViewModel.getAllFavoriteTracks()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = favoriteAdapter

        favoritesViewModel.favoriteTracksList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    favoriteAdapter.updateList(it.data)
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun onItemClick(favoriteTrack: FavoriteTrack) {
        if (!mediaPlayer.isPlaying) {
            playTrack(mediaPlayer, favoriteTrack.preview)
        } else {
            mediaPlayer.stop()
            mediaPlayer.reset()
        }
    }

    private fun onFavoriteClick(position: Int, trackId: Long) {
        favoritesViewModel.deleteTrackFromFavorites(trackId)
        favoriteAdapter.deleteItem(position, trackId)
    }

    private fun playTrack(mediaPlayer: MediaPlayer, trackUrl: String) {
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
        )
        mediaPlayer.setDataSource(trackUrl)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}