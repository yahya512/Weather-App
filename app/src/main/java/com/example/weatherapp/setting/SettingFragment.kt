package com.example.weatherapp.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.weatherapp.MainActivity
import com.example.weatherapp.core.ARABIC
import com.example.weatherapp.core.AppSharedPreferences
import com.example.weatherapp.core.ENGLISH
import com.example.weatherapp.core.changeLang
import com.example.weatherapp.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var sharedPreferences: AppSharedPreferences
    private var selectedLang = ENGLISH
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpSpinner()
        spinnerListener()
        applyChangeLanguage()
    }

    private fun setUpSpinner() {
        val savedLang = sharedPreferences.getLanguage()
        val position = when (savedLang) {
            ENGLISH -> 0
            ARABIC -> 1
            else -> {
                0
            }
        }
        binding.languageSpinner.setSelection(position)
        selectedLang = savedLang ?: "en"
    }

    private fun spinnerListener() {
        binding.languageSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedLang = when (position) {
                        0 -> ENGLISH
                        1 -> ARABIC
                        else -> ENGLISH
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }


    private fun applyChangeLanguage() {
        binding.applyLanguageButton.setOnClickListener {
            sharedPreferences.saveLanguage(selectedLang)
            changeLang(selectedLang)
            val intent = Intent(requireActivity(), MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}