package com.example.ezlotestapp.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.ezlotestapp.core.BaseFragment
import com.example.ezlotestapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

  private val viewModel by viewModels<HomeViewModel>()

  override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
    get() = FragmentHomeBinding::inflate

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.getDevices()
  }

}