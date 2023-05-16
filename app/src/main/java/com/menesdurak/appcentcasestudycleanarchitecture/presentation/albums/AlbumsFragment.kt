package com.menesdurak.appcentcasestudycleanarchitecture.presentation.albums

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.menesdurak.appcentcasestudycleanarchitecture.R
import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.FragmentAlbumsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment : Fragment() {
    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding!!
    private val albumsViewModel: AlbumsViewModel by viewModels()
    private val albumAdapter by lazy { AlbumAdapter(::onItemClick) }
    private var artistName: String = ""
    private var artistId: Int = -1
    private var artistImage: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        val view = binding.root

        //Receive navigation arguments
        val args: AlbumsFragmentArgs by navArgs()
        artistName = args.artistName
        artistId = args.artistId
        artistImage = args.artistImage

        binding.tvArtistName.text = artistName

        Glide
            .with(binding.root.context)
            .load(artistImage)
            .centerCrop()
            .placeholder(R.drawable.loading)
            .into(binding.ivArtist)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumsViewModel.getAlbums(artistId)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = albumAdapter

        albumsViewModel.albumsList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    albumAdapter.updateList(it.data.data)
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

    private fun onItemClick(albumId: Int, albumName: String, albumImage: String) {
        val goToTracksAction = AlbumsFragmentDirections.actionAlbumsFragmentToTracksFragment(
            albumId = albumId,
            albumName = albumName,
            albumImage = albumImage
        )
        findNavController().navigate(goToTracksAction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}