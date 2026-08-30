package com.example.weatherapp.home.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.core.LocationLocalDataSource
import com.example.weatherapp.core.dateFormate
import com.example.weatherapp.core.handleImageUrl
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.home.presentation.adapter.ForecastRecyclerView
import com.example.weatherapp.home.presentation.model.ForecastDayUiModel
import com.example.weatherapp.home.presentation.model.GetWeatherDetailsUiResponse
import com.example.weatherapp.home.presentation.model.HomeUiState
import com.example.weatherapp.home.presentation.viewmodel.WeatherDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: WeatherDetailsViewModel by viewModels()
    private val args: HomeFragmentArgs by navArgs()
    private val adapter by lazy {
        ForecastRecyclerView()
    }

    @Inject
    lateinit var sharedPreferences: LocationLocalDataSource

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Send Latitude and Longitude
        if (args.Latitude == 0.0f && args.Longitude == 0.0f) {
            viewModel.setLatitudeAndLongitude(
                sharedPreferences.getLatitude(),
                sharedPreferences.getLongitude()
            )
        } else {
            viewModel.setLatitudeAndLongitude(args.Latitude, args.Longitude)
        }
        //Load Weather Details
        viewModel.loadWeatherDetails()
        //set up listeners
        setUpListener()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateWeatherDetails.collect { state ->
                    when (state) {
                        is HomeUiState.Error -> {
                            binding.apply {
                                screenItemsGroup.isVisible = false
                                errorMessageTextView.isVisible = true
                                progressBar.isVisible = false
                                swipeToRefresh.isRefreshing = false
                                errorMessageTextView.text = state.errorMessage
                            }
                        }

                        is HomeUiState.Loading -> {
                            binding.apply {
                                screenItemsGroup.isVisible = false
                                progressBar.isVisible = true
                                swipeToRefresh.isRefreshing = false
                            }
                        }

                        is HomeUiState.Success -> {
                            handleSuccessState(binding, state.data)
                        }
                    }

                }
            }
        }

    }

    private fun showWeatherDetails(weatherForecast: List<ForecastDayUiModel>) {
        binding.forecastRecyclerView.adapter = adapter
        adapter.submitList(weatherForecast)
    }

    private fun handleSuccessState(
        binding: FragmentHomeBinding,
        state: GetWeatherDetailsUiResponse
    ) {
        val imageUrl = state.current.condition.icon
        binding.apply {
            screenItemsGroup.isVisible = true
            errorMessageTextView.isVisible = false
            progressBar.isVisible = false
            swipeToRefresh.isRefreshing = false
            countryNameTextView.text = state.location.name
            localTimeTextView.text = dateFormate(state.location.localtime)
            lastUpdatedTextView.text = state.current.lastUpdate
            Glide.with(requireContext())
                .load(
                    imageUrl.handleImageUrl()
                )
                .placeholder(R.drawable.sun_icon).into(weatherStatusImageView)
            weatherConditionTextView.text = state.current.condition.text
            tempCTextView.text = state.current.tempC.toInt().toString()
            feelsLikeNumberTextView.text = state.current.feelsLike.toString()
            humidityNumberTextView.text = getString(
                R.string.percentageHumidityUi, state.current.humidity.toString()
            )
            windNumberTextView.text = getString(
                R.string.speedOfWindUi, state.current.windKph.toString()
            )
            showWeatherDetails(state.forecast.forecastDay)
        }

    }

    private fun setUpListener() {
        //swip to refresh content
        binding.apply {
            swipeToRefresh.setOnChildScrollUpCallback { _, _ ->
                binding.nestedScroll.canScrollVertically(-1)
            }
            swipeToRefresh.setOnRefreshListener {
                viewModel.loadWeatherDetails()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}