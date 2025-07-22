package com.mobile.mobileengineertranslationtest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.mobileengineertranslationtest.databinding.ItemVehicleBinding
import com.mobile.mobileengineertranslationtest.model.Vehicle

class VehicleAdapter(private val vehicles: List<Vehicle>) :
    RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    inner class VehicleViewHolder(val binding: ItemVehicleBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val binding = ItemVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehicleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = vehicles[position]
        holder.binding.apply {
            imgVehicle.setImageResource(vehicle.imageResId)
            tvVehicleTitle.text = vehicle.title
            tvVehicleDesc.text = vehicle.subtitle
        }
    }

    override fun getItemCount(): Int = vehicles.size
}
