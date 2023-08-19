package com.example.ezlotestapp.feature.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.domain.model.Device
import com.example.ezlotestapp.R
import com.example.ezlotestapp.databinding.ItemDeviceBinding
import javax.inject.Inject

class DevicesAdapter @Inject constructor() : RecyclerView.Adapter<DevicesAdapter.ViewHolder>() {

  private var devices: List<Device> = listOf()
  lateinit var adapterListener: AdapterListener

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      ItemDeviceBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val device = devices[position]
    holder.bind(device)
  }

  override fun getItemCount(): Int = devices.size

  @SuppressLint("NotifyDataSetChanged")
  fun updateDevices(devicesList: List<Device>) {
    devices = devicesList
    notifyDataSetChanged()
  }

  inner class ViewHolder(private val binding: ItemDeviceBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(device: Device) {
      binding.apply {
        container.setOnClickListener {
          adapterListener.onItemClick(device)
        }
        container.setOnLongClickListener {
          adapterListener.onItemLongClick(device)
          true
        }

        btnEdit.setOnClickListener {
          adapterListener.onEditClick(device)
        }

        binding.ivHomeImage.setImageResource(adapterListener.getImage(device))

        tvHomeName.text = device.name
        tvHomeCode.text =
          binding.root.resources.getString(R.string.sn_format, device.pkDevice.toString())
      }
    }
  }

  interface AdapterListener {
    fun onItemClick(device: Device)
    fun onItemLongClick(device: Device)
    fun onEditClick(device: Device)
    fun getImage(device: Device): Int
  }
}