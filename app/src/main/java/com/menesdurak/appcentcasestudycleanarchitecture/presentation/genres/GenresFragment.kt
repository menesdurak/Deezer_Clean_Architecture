package com.menesdurak.appcentcasestudycleanarchitecture.presentation.genres

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.menesdurak.appcentcasestudycleanarchitecture.common.Resource
import com.menesdurak.appcentcasestudycleanarchitecture.data.mapper.GenreDataMapper
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.FragmentGenresBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenresFragment : Fragment() {

    private var _binding: FragmentGenresBinding? = null
    private val binding get() = _binding!!
    private val genresViewModel: GenresViewModel by viewModels()
    private val genreAdapter by lazy { GenreAdapter(::onItemClick) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenresBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        genresViewModel.getGenres()

        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = genreAdapter

        genresViewModel.genresList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    genreAdapter.updateList(GenreDataMapper().map(it.data.data))
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

    private fun onItemClick(genreId: Int, genreName: String) {
        val goToArtistsAction =
            GenresFragmentDirections.actionHomeFragmentToArtistsFragment(genreId, genreName)
        findNavController().navigate(goToArtistsAction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}