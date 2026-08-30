package com.example.weatherapp.search.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.core.LocationLocalDataSource
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.search.presentation.adapter.OnClickItem
import com.example.weatherapp.search.presentation.adapter.SearchRecyclerView
import com.example.weatherapp.search.presentation.model.SearchResultUiModel
import com.example.weatherapp.search.presentation.model.SearchUiState
import com.example.weatherapp.search.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(), OnClickItem {

    private var _binding: FragmentSearchBinding? = null
    private val binding
        get() = _binding!!
    private val adapter by lazy {
        SearchRecyclerView(this)
    }
    private val viewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: LocationLocalDataSource

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpListener()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchState.collect { state ->
                    when (state) {
                        SearchUiState.Idle -> {
                            binding.apply {
                                searchResultRecyclerView.isVisible = false
                                errorMessageTextView.isVisible = false
                                progressBar.isVisible = false
                                searchEditText.isVisible = false
                            }
                        }

                        is SearchUiState.Error -> {
                            binding.apply {
                                searchEditText.isVisible = true
                                searchResultRecyclerView.isVisible = false
                                progressBar.isVisible = false
                                errorMessageTextView.isVisible = true
                                errorMessageTextView.text = state.errorMessage
                            }
                        }

                        SearchUiState.Loading -> {
                            binding.apply {
                                searchEditText.isVisible = false
                                searchResultRecyclerView.isVisible = false
                                progressBar.isVisible = true
                                errorMessageTextView.isVisible = false
                            }
                        }

                        is SearchUiState.Success -> {
                            handleSuccessState(binding, state.data)
                        }

                    }
                }
            }
        }
//         handle open and close search
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.searchEditText.isVisible) {
                        closeSearch()
                    } else {
                        isEnabled = false
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    }
                }
            })
    }

    private fun handleSuccessState(
        binding: FragmentSearchBinding, state: List<SearchResultUiModel>
    ) {
        binding.apply {
            searchEditText.isVisible = true
            searchResultRecyclerView.isVisible = true
            progressBar.isVisible = false
            errorMessageTextView.isVisible = false
            showSearchResult(state)
        }
    }

    private fun setUpListener() {
        binding.searchIconImageView.setOnClickListener {
            openSearch()
        }
        binding.searchEditText.addTextChangedListener {
            viewModel.setCityName(it.toString())
        }
    }

    private fun openSearch() {
        binding.apply {
            LocationTextView.isVisible = false
            searchIconImageView.isVisible = false
            searchEditText.isVisible = true
            searchEditText.requestFocus()
        }
    }

    private fun closeSearch() {
        binding.apply {
            LocationTextView.isVisible = true
            searchIconImageView.isVisible = true
            searchEditText.isVisible = false
            searchEditText.text?.clear()
            searchEditText.clearFocus()
        }
    }

    private fun showSearchResult(items: List<SearchResultUiModel>) {
        binding.searchResultRecyclerView.adapter = adapter
        adapter.submitList(items)
    }


    override fun onClick(item: SearchResultUiModel) {
        // saved location
        sharedPreferences.saveLocation(item.latitude, item.longitude)
        val action = SearchFragmentDirections.actionSearchFragmentToHomeFragment(
            item.latitude, item.longitude
        )

        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}