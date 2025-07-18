package com.mobile.mobileengineertranslationtest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mobile.mobileengineertranslationtest.R
import com.mobile.mobileengineertranslationtest.databinding.DeliveryItemLayoutBinding
import com.mobile.mobileengineertranslationtest.model.DeliveryItem

class DeliveryAdapter(private val items: List<DeliveryItem>) :
    RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder>() {

    inner class DeliveryViewHolder(private val binding: DeliveryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DeliveryItem) {

            val context = binding.root.context


            binding.status.text = item.status

            when (item.status.lowercase()) {
                "in-progress" -> {
                    binding.status.setTextColor(ContextCompat.getColor(context, R.color.green))
                    binding.loadingIcon.setImageResource(R.drawable.in_progress_icon)
                }
                "pending" -> {
                    binding.status.setTextColor(ContextCompat.getColor(context, R.color.tab_indicator_orange))
                    binding.loadingIcon.setImageResource(R.drawable.pending_vector)
                }
                "loading" -> {
                    binding.status.setTextColor(ContextCompat.getColor(context, R.color.loading_blue))
                    binding.loadingIcon.setImageResource(R.drawable.progress_vector)
                }
                else -> {
                    binding.status.setTextColor(ContextCompat.getColor(context, R.color.item_grey))
                    binding.loadingIcon.setImageResource(R.drawable.default_vector)
                }
            }

            binding.title.text = "Arriving today!"
            binding.description.text = "Your delivery,\n${item.item}\nis arriving today!"
            binding.price.text = item.price
            binding.date.text = item.date
            binding.packageIcon.setImageResource(item.imageResId)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val binding = DeliveryItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DeliveryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
