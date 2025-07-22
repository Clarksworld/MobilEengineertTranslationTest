package com.mobile.mobileengineertranslationtest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.mobileengineertranslationtest.R
import com.mobile.mobileengineertranslationtest.adapters.DeliveryAdapter
import com.mobile.mobileengineertranslationtest.databinding.FragmentAllShipmentsBinding
import com.mobile.mobileengineertranslationtest.databinding.FragmentInTransitBinding
import com.mobile.mobileengineertranslationtest.model.DeliveryItem



class InTransitFragment : Fragment() {



    private lateinit var binding: FragmentInTransitBinding
    private lateinit var adapter: DeliveryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInTransitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DeliveryAdapter(getSampleDeliveries())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter


        runRecyclerAnimation()




    }

    fun runRecyclerAnimation() {
        binding.recyclerView.layoutAnimation =
            AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_animation_fade_slide_up)
        binding.recyclerView.scheduleLayoutAnimation()
    }

    private fun getSampleDeliveries(): List<DeliveryItem> {
        return listOf(
            DeliveryItem("in-progress", "Laptop from Atlanta", "$1400 USD", "Sep 20, 2023", R.drawable.img),
            DeliveryItem("in-progress", "Sneakers from London", "$220 USD", "Sep 22, 2023", R.drawable.img),
            DeliveryItem("in-progress", "Headphones from New York", "$80 USD", "Sep 19, 2023", R.drawable.img),
            DeliveryItem("in-progress", "Laptop from Atlanta", "$1400 USD", "Sep 20, 2023", R.drawable.img),

        )


    }

    override fun onResume() {
        super.onResume()
        runRecyclerAnimation()
    }

}