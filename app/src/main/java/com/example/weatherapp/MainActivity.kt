package com.example.weatherapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.home.presentation.ui.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.activityFragmentContainer.id) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    binding.bottomNavigation.isVisible = false
                }

                R.id.homeFragment, R.id.searchFragment -> {
                    binding.bottomNavigation.isVisible = true
                }
            }
        }

        binding.bottomNavigation.setOnClickListener { item ->
            when (item.id) {
                R.id.home_navBar -> {
                    HomeFragment()
                }

                R.id.search_navBar -> {
                    //SearchFragment()
                }

                R.id.setting_navbar -> {
                    // SettingFragment
                }
            }
        }
    }
}