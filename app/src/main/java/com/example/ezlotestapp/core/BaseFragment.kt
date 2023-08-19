package com.example.ezlotestapp.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.ezlotestapp.R

abstract class BaseFragment<B : ViewBinding> : Fragment() {

  private var _binding: B? = null
  val binding get() = _binding!!

  abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> B

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = bindingInflater.invoke(inflater, container, false)
    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  protected fun getImageDependingOnPlatform(platform: String): Int {
    return when (platform) {
      "Sercomm G450" -> R.drawable.vera_plus_big
      "Sercomm G550" -> R.drawable.vera_secure_big
      "MiCasaVerde VeraLite" -> R.drawable.vera_edge_big
      "Sercomm NA900" -> R.drawable.vera_edge_big
      "Sercomm NA301" -> R.drawable.vera_edge_big
      "Sercomm NA930" -> R.drawable.vera_edge_big
      else -> R.drawable.vera_edge_big
    }
  }
}