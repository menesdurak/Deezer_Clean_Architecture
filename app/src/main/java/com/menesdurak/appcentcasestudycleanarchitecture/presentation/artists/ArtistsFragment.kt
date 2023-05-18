package com.menesdurak.appcentcasestudycleanarchitecture.presentation.artists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.data.mapper.ArtistDataMapper
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.FragmentArtistsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistsFragment : Fragment() {
    private var _binding: FragmentArtistsBinding? = null
    private val binding get() = _binding!!
    private val artistsViewModel: ArtistsViewModel by viewModels()
    private val artistAdapter by lazy { ArtistAdapter(::onItemClick) }
    private var genreName: String = ""
    private var genreId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistsBinding.inflate(inflater, container, false)
        val view = binding.root

        //Receive navigation arguments
        val args: ArtistsFragmentArgs by navArgs()
        genreName = args.genreName
        genreId = args.genreId

        binding.tvGenreName.text = genreName

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artistsViewModel.getArtists(genreId)

        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = artistAdapter

        artistsViewModel.artistsList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    artistAdapter.updateList(ArtistDataMapper().map(it.data.data))
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

    private fun onItemClick(artistId: Int, artistName: String, artistImage: String) {
        val goToAlbumsAction =
            ArtistsFragmentDirections.actionArtistsFragmentToAlbumsFragment(artistId, artistName, artistImage)
        findNavController().navigate(goToAlbumsAction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}