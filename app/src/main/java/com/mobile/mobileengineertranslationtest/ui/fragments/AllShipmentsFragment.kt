package com.mobile.mobileengineertranslationtest.ui.fragments

import android.content.Intent
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
import com.mobile.mobileengineertranslationtest.model.DeliveryItem
import com.mobile.mobileengineertranslationtest.ui.ShipmentActivity


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

//        binding.recyclerView.layoutAnimation =
//            AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_animation_slide_up)
//        binding.recyclerView.scheduleLayoutAnimation()
//
//        binding.recyclerView.layoutAnimation =
//            AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_animation_fade_slide_up)
//        binding.recyclerView.scheduleLayoutAnimation()

        runRecyclerAnimation()




    }

     fun runRecyclerAnimation() {
        binding.recyclerView.layoutAnimation =
            AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_animation_fade_slide_up)
        binding.recyclerView.scheduleLayoutAnimation()
    }

//    fun runRecyclerAnimation() {
////        if (!isAdded || view == null || !::binding.isInitialized) return
////
////        binding.recyclerView.alpha = 0f
////        binding.recyclerView.translationY = 100f
////        binding.recyclerView.animate()
////            .translationY(0f)
////            .alpha(1f)
////            .setDuration(600)
////            .start()
////    }

    // function for data populated in the recyclerview
    private fun getSampleDeliveries(): List<DeliveryItem> {
        return listOf(
            DeliveryItem("in-progress", "Laptop from Atlanta", "$1400 USD", "Sep 20, 2023", R.drawable.img),
            DeliveryItem("loading", "Sneakers from London", "$220 USD", "Sep 22, 2023", R.drawable.img),
            DeliveryItem("pending", "Headphones from New York", "$80 USD", "Sep 19, 2023", R.drawable.img),
            DeliveryItem("in-progress", "Laptop from Atlanta", "$1400 USD", "Sep 20, 2023", R.drawable.img),
            DeliveryItem("loading", "Sneakers from London", "$220 USD", "Sep 22, 2023", R.drawable.img),
            DeliveryItem("pending", "Headphones from New York", "$80 USD", "Sep 19, 2023", R.drawable.img)
        )


    }

    override fun onResume() {
        super.onResume()
        runRecyclerAnimation()
    }

//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        runRecyclerAnimation()
//    }

}