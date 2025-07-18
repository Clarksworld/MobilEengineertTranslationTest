package com.mobile.mobileengineertranslationtest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.mobileengineertranslationtest.R
import com.mobile.mobileengineertranslationtest.adapters.DeliveryAdapter
import com.mobile.mobileengineertranslationtest.databinding.FragmentAllShipmentsBinding
import com.mobile.mobileengineertranslationtest.model.DeliveryItem


class AllShipmentsFragment : Fragment() {


    private lateinit var binding: FragmentAllShipmentsBinding
    private lateinit var adapter: DeliveryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllShipmentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DeliveryAdapter(getSampleDeliveries())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    // function for data populated in the recyclerview
    private fun getSampleDeliveries(): List<DeliveryItem> {
        return listOf(
            DeliveryItem("in-progress", "Laptop from Atlanta", "$1400 USD", "Sep 20, 2023", R.drawable.img),
            DeliveryItem("loading", "Sneakers from London", "$220 USD", "Sep 22, 2023", R.drawable.img),
            DeliveryItem("pending", "Headphones from New York", "$80 USD", "Sep 19, 2023", R.drawable.img)
        )
    }


}