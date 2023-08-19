package com.example.ezlotestapp.feature.details

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.ezlotestapp.R
import com.example.ezlotestapp.core.BaseFragment
import com.example.ezlotestapp.databinding.FragmentHomeDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDetailsFragment : BaseFragment<FragmentHomeDetailsBinding>() {

  private val viewModel: HomeDetailsViewModel by viewModels()
  private val args: HomeDetailsFragmentArgs by navArgs()

  override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeDetailsBinding
    get() = FragmentHomeDetailsBinding::inflate

  @RequiresApi(Build.VERSION_CODES.TIRAMISU)
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupViews()
    setupClicks()
  }

  private fun setupViews() {
    val isInEditMode = args.isInEditMode
    binding.apply {
      tvDeviceName.isGone = isInEditMode
      etDeviceName.isGone = !isInEditMode
      btnChangeTitle.isGone = !isInEditMode

      if (isInEditMode) {
        etDeviceName.requestFocus()
        setKeyboardForView(etDeviceName, true)
      }

      args.device.let {
        ivHomeImage.setImageResource(getImageDependingOnPlatform(it.platform))
        tvDeviceName.text = it.name
        etDeviceName.hint = it.name
        tvSn.text = formatString(R.string.sn_format, it.pkDevice.toString())
        tvMacAddress.text = formatString(R.string.mac_address_format, it.macAddress)
        tvFirmware.text = formatString(R.string.firmware_format, it.firmware)
        tvModel.text = formatString(R.string.model_format, it.platform)
      }
    }
  }

  private fun setupClicks() {
    binding.btnChangeTitle.setOnClickListener {
      val title = binding.etDeviceName.text.trim().toString()
      viewModel.updateTitle(args.device, title)
      binding.etDeviceName.clearFocus()
      setKeyboardForView(binding.etDeviceName)
    }
  }

  private fun formatString(resource: Int, value: String) =
    binding.root.resources.getString(resource, value)

  private fun setKeyboardForView(view: View, needShow: Boolean = false) {
    val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (needShow) {
      inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    } else {
      inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
  }
}