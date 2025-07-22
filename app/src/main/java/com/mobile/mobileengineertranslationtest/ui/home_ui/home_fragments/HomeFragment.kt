package com.mobile.mobileengineertranslationtest.ui.home_ui.home_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.mobileengineertranslationtest.R
import com.mobile.mobileengineertranslationtest.adapters.VehicleAdapter
import com.mobile.mobileengineertranslationtest.databinding.FragmentHomeBinding
import com.mobile.mobileengineertranslationtest.model.Vehicle


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var vehicleAdapter: VehicleAdapter
    private lateinit var vehicleList: List<Vehicle>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchBar.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_shipmentSearchFragment)
        }

        binding.searchIcon.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_shipmentSearchFragment)
        }

        binding.searchInput.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_shipmentSearchFragment)
        }

        setupVehicleList()
        setupRecyclerView()
    }

    private fun setupVehicleList() {
        vehicleList = listOf(
            Vehicle(R.drawable.ship, "Ocean freight", "International"),
            Vehicle(R.drawable.pick_up, "Cargo freight", "Reliable"),
            Vehicle(R.drawable.plane, "Air freight", "International")
        )
    }

    private fun setupRecyclerView() {
        vehicleAdapter = VehicleAdapter(vehicleList)
        binding.rvVehicles.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = vehicleAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
