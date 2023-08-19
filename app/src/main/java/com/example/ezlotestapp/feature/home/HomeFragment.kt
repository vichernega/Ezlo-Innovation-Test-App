package com.example.ezlotestapp.feature.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.domain.model.Device
import com.example.data.state.UiState
import com.example.ezlotestapp.R
import com.example.ezlotestapp.core.BaseFragment
import com.example.ezlotestapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

  private val viewModel by viewModels<HomeViewModel>()

  @Inject
  lateinit var devicesAdapter: DevicesAdapter

  override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
    get() = FragmentHomeBinding::inflate

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupAdapter()
    setupAdapterListener()
    setupClicks()
    observeLiveData()
    viewModel.getDevices()
  }

  private fun setupAdapter() {
    binding.rvDevices.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = devicesAdapter
    }
  }

  private fun setupAdapterListener() {
    devicesAdapter.adapterListener = object: DevicesAdapter.AdapterListener {
      override fun onItemClick(device: Device) {
        navigateToHomeDetails(false, device)
      }

      override fun onItemLongClick(device: Device) {
        showDeleteDialog(device)
      }

      override fun onEditClick(device: Device) {
        navigateToHomeDetails(true, device)
      }

      override fun getImage(device: Device): Int = getImageDependingOnPlatform(device.platform)
    }
  }

  private fun setupClicks() {
    binding.btnReset.setOnClickListener { viewModel.resetDevices() }
  }

  private fun observeLiveData() {
    viewModel.devicesLiveData.observe(viewLifecycleOwner) { state ->
      when (state) {
        is UiState.Success -> {
          devicesAdapter.updateDevices(state.data)
          binding.progress.isGone = true
        }
        is UiState.Error -> {
          binding.progress.isGone = true
          Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
        }
        is UiState.Loading -> {
          binding.progress.isGone = false
        }
      }
    }
  }

  private fun navigateToHomeDetails(isInEditMode: Boolean, device: Device) {
    findNavController().navigate(
      HomeFragmentDirections.actionHomeFragmentToHomeDetailsFragment(isInEditMode, device)
    )
  }

  private fun showDeleteDialog(device: Device) {
    AlertDialog.Builder(requireContext())
      .setMessage(getString(R.string.delete_messsage, device.name))
      .setPositiveButton(R.string.dialog_positive_button_title) { _, _ ->
        viewModel.deleteDevice(device)
      }
      .setNegativeButton(R.string.dialog_negative_button_title) { dialog, _ ->
        dialog.dismiss()
      }
      .create()
      .show()
  }
}