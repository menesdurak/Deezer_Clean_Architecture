package com.menesdurak.appcentcasestudycleanarchitecture.presentation.tracks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.FragmentTracksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TracksFragment : Fragment() {
    private var _binding: FragmentTracksBinding? = null
    private val binding get() = _binding!!
    private val tracksViewModel: TracksViewModel by viewModels()
    private val trackAdapter by lazy { TrackAdapter(::onItemClick) }

    private var albumId: Int = -1
    private var albumName: String = ""
    private var albumImage: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTracksBinding.inflate(inflater, container, false)
        val view = binding.root

        //Receive navigation arguments
        val args: TracksFragmentArgs by navArgs()
        albumId = args.albumId
        albumName = args.albumName
        albumImage = args.albumImage

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tracksViewModel.getTracks(albumId)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = trackAdapter

        tracksViewModel.tracksList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    trackAdapter.updateList(it.data.data)
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

    private fun onItemClick(trackId: Int) {
        Toast.makeText(context, "Track Id: $trackId", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}