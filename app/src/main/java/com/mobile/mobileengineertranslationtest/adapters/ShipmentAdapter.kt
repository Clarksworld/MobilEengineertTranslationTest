package com.mobile.mobileengineertranslationtest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.mobile.mobileengineertranslationtest.databinding.ItemShipmentBinding
import com.mobile.mobileengineertranslationtest.model.Shipment
import android.widget.Filter

class ShipmentAdapter(private val originalList: List<Shipment>) :
    RecyclerView.Adapter<ShipmentAdapter.ShipmentViewHolder>(), Filterable {

    private var filteredList = originalList.toMutableList()

    inner class ShipmentViewHolder(val binding: ItemShipmentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipmentViewHolder {
        val binding = ItemShipmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShipmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShipmentViewHolder, position: Int) {
        val shipment = filteredList[position]
        holder.binding.apply {
            itemName.text = shipment.name
            itemTrackingId.text = "#${shipment.trackingId} • "
            itemOriginDestination.text = "${shipment.origin} → ${shipment.destination}"
        }
    }

    override fun getItemCount(): Int = filteredList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(query: CharSequence?): FilterResults {
                val result = if (query.isNullOrEmpty()) {
                    originalList
                } else {
                    val filterPattern = query.toString().trim().lowercase()
                    originalList.filter {
                        it.name.lowercase().contains(filterPattern) ||
                                it.trackingId.lowercase().contains(filterPattern)
                    }
                }

                return FilterResults().apply { values = result }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(query: CharSequence?, results: FilterResults?) {
                filteredList = (results?.values as? List<Shipment>)?.toMutableList() ?: mutableListOf()
                notifyDataSetChanged()
            }
        }
    }
}
