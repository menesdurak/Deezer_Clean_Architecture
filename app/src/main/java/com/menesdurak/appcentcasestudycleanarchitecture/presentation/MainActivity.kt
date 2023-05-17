package com.menesdurak.appcentcasestudycleanarchitecture.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.menesdurak.appcentcasestudycleanarchitecture.R
import com.menesdurak.appcentcasestudycleanarchitecture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.genresFragment || nd.id == R.id.favoritesFragment
            ) {
                binding.bottomNav.visibility = View.VISIBLE
            } else {
                binding.bottomNav.visibility = View.GONE
            }
        }

        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.genres -> navController.navigate(R.id.genresFragment)
                R.id.favorites -> navController.navigate(R.id.favoritesFragment)
            }
            true
        }
    }
}