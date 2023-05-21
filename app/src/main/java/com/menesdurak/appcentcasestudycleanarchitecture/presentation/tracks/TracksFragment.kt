package com.menesdurak.appcentcasestudycleanarchitecture.presentation.tracks

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.data.local.entity.FavoriteTrack
import com.menesdurak.appcentcasestudycleanarchitecture.data.mapper.TrackDataMapper
import com.menesdurak.appcentcasestudycleanarchitecture.data.remote.dto.TrackUiData
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.FragmentTracksBinding
import com.menesdurak.appcentcasestudycleanarchitecture.presentation.favorites.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TracksFragment : Fragment() {
    private var _binding: FragmentTracksBinding? = null
    private val binding get() = _binding!!
    private val tracksViewModel: TracksViewModel by viewModels()
    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private val trackAdapter by lazy {
        TrackAdapter(
            ::onPlayClick,
            ::onFavoriteClick,
            ::addOrDeleteFavorite
        )
    }

    private var albumId: Int = -1
    private var albumName: String = ""
    private var albumImage: String = ""
    private val mediaPlayer: MediaPlayer = MediaPlayer()
    private lateinit var favoriteTracksIdList: List<Long>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTracksBinding.inflate(inflater, container, false)
        val view = binding.root

        //Receive navigation arguments
        val args: TracksFragmentArgs by navArgs()
        albumId = args.albumId
        albumName = args.albumName
        albumImage = args.albumImage

        binding.tvAlbumName.text = albumName

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tracksViewModel.getTracks(albumId)
        favoritesViewModel.getAllFavoriteTracksId()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = trackAdapter

        favoritesViewModel.favoriteTracksIdList.observe(viewLifecycleOwner) { rFavoriteTracksIdList ->
            when (rFavoriteTracksIdList) {
                is Resource.Success -> {
                    favoriteTracksIdList = rFavoriteTracksIdList.data
                }

                is Resource.Error -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }

                Resource.Loading -> {
                }
            }
            tracksViewModel.tracksList.observe(viewLifecycleOwner) { rTrackResponse ->
                when (rTrackResponse) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        trackAdapter.updateFavoriteTracksIdList(favoriteTracksIdList)
                        trackAdapter.updateList(TrackDataMapper(albumImage).map(rTrackResponse.data.data))
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
    }

    private fun onPlayClick(trackUiData: TrackUiData) {
        if (!mediaPlayer.isPlaying) {
            playTrack(mediaPlayer, trackUiData.preview)
        } else {
            mediaPlayer.stop()
            mediaPlayer.reset()
        }
    }

    private fun playTrack(mediaPlayer: MediaPlayer, trackUrl: String) {
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
        )
        mediaPlayer.setDataSource(trackUrl)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    private fun onFavoriteClick(trackUiData: TrackUiData, position: Int) {
        trackAdapter.updateFavoriteStatusOfTrack(position, trackUiData.id)
        val favoriteTrack = FavoriteTrack(
            id = trackUiData.id,
            name = trackUiData.title,
            preview = trackUiData.preview,
            length = trackUiData.duration,
            image = trackUiData.image
        )
        favoritesViewModel.addTrackToFavorites(favoriteTrack)
    }

    private fun addOrDeleteFavorite(trackUiData: TrackUiData) {
        if (!trackUiData.isFavorite) {
            favoritesViewModel.deleteTrackFromFavorites(trackUiData.id)
            Log.d("delete", "Deleted ${trackUiData.title}")
        } else {
            val favoriteTrack = FavoriteTrack(
                id = trackUiData.id,
                name = trackUiData.title,
                preview = trackUiData.preview,
                length = trackUiData.duration,
                image = trackUiData.image
            )
            favoritesViewModel.addTrackToFavorites(favoriteTrack)
            Log.d("added", "Added ${favoriteTrack.name}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}