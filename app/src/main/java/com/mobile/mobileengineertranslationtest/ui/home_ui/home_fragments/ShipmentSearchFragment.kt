package com.mobile.mobileengineertranslationtest.ui.home_ui.home_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.mobileengineertranslationtest.R
import com.mobile.mobileengineertranslationtest.adapters.ShipmentAdapter
import com.mobile.mobileengineertranslationtest.databinding.FragmentShipmentSearchBinding
import com.mobile.mobileengineertranslationtest.model.Shipment

class ShipmentSearchFragment : Fragment() {


    private var _binding: FragmentShipmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var shipmentAdapter: ShipmentAdapter
    private lateinit var shipmentList: List<Shipment>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShipmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shipmentList = listOf(
            Shipment("Summer linen jacket", "NEJ20089934122231", "Barcelona", "Paris"),
            Shipment("Tapered-fit jeans AW", "NEJ35870264978659", "Colombia", "Paris"),
            Shipment("Macbook pro M2", "NE43857340857904", "Paris", "Morocco"),
            Shipment("Office setup desk", "NEJ23481570754963", "France", "Germany"),
            Shipment("Tapered-fit jeans AW", "NEJ35870264978659", "Colombia", "Madrid"),
            Shipment("Summer linen jacket", "NEJ20089934122231", "Barcelona", "London"),


            )

        shipmentAdapter = ShipmentAdapter(shipmentList)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = shipmentAdapter
        }

                binding.recyclerView.layoutAnimation =
            AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_animation_slide_up)
        binding.recyclerView.scheduleLayoutAnimation()

        binding.searchView.addTextChangedListener {
            shipmentAdapter.filter.filter(it.toString())
        }

        binding.searchIcon.setOnClickListener {

            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}