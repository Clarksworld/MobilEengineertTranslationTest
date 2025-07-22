package com.mobile.mobileengineertranslationtest.ui.home_ui.home_fragments

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mobile.mobileengineertranslationtest.R
import com.mobile.mobileengineertranslationtest.adapters.ShipmentPagerAdapter
import com.mobile.mobileengineertranslationtest.databinding.ActivityShipmentBinding
import com.mobile.mobileengineertranslationtest.databinding.FragmentShipmentBinding
import com.mobile.mobileengineertranslationtest.ui.ShipmentActivity
import com.mobile.mobileengineertranslationtest.ui.fragments.AllShipmentsFragment
import com.mobile.mobileengineertranslationtest.ui.fragments.CanceledFragment
import com.mobile.mobileengineertranslationtest.ui.fragments.FinishedFragment
import com.mobile.mobileengineertranslationtest.ui.fragments.InTransitFragment
import com.mobile.mobileengineertranslationtest.ui.fragments.PendingFragment


class ShipmentFragment : Fragment() {

    private var _binding: FragmentShipmentBinding? = null
    private val binding get() = _binding!!

    private val titles = listOf("All", "Completed", "In progress", "Pending", "Cancelled")
    private val counts = listOf(12, 5, 3, 2, 4)

    private var shipmentFragments: List<Fragment> = emptyList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShipmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.statusBarColor = Color.TRANSPARENT
        makeFullscreen()
        setupViewPagerWithTabs()

        // Animate TabLayout
        val slideIn = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_from_right_side)
        binding.tabLayout.startAnimation(slideIn)


        binding.backButton.setOnClickListener {

            findNavController().navigateUp()
        }

        val intent = Intent(requireContext(), ShipmentActivity::class.java)
        startActivity(intent)
        activity?.finish()


    }

    private fun setupViewPagerWithTabs() {

        shipmentFragments = listOf(
            AllShipmentsFragment(),
            FinishedFragment(),
            InTransitFragment(),
            PendingFragment(),
            CanceledFragment()
        )

        val adapter = ShipmentPagerAdapter(requireActivity(), shipmentFragments)
        binding.viewPager.adapter = adapter

        //   attach mediator
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.customView = createTabView(titles[position], counts[position], position == 0)
        }.attach()

        // Only after everything, set the page change callback
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val fragment = shipmentFragments[position]
                if (fragment is AllShipmentsFragment && position == 0) {
                    fragment.runRecyclerAnimation()
                }
            }
        })

        // update tab styles
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) = updateTabView(tab, true)
            override fun onTabUnselected(tab: TabLayout.Tab?) = updateTabView(tab, false)
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }

    private fun createTabView(title: String, count: Int, selected: Boolean): View {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.tab_item, null)
        val titleView = view.findViewById<TextView>(R.id.tabTitle)
        val badgeView = view.findViewById<TextView>(R.id.tabBadge)

        titleView.text = title
        badgeView.text = count.toString()

        val textColor = if (selected) R.color.white else R.color.tab_text_default
        val badgeBg = if (selected) R.drawable.tab_badge_selected else R.drawable.tab_badge_bg

        titleView.setTextColor(ContextCompat.getColor(requireContext(), textColor))
        badgeView.setBackgroundResource(badgeBg)

        return view
    }

    private fun updateTabView(tab: TabLayout.Tab?, selected: Boolean) {
        tab?.customView?.let { view ->
            val titleView = view.findViewById<TextView>(R.id.tabTitle)
            val badgeView = view.findViewById<TextView>(R.id.tabBadge)

            val textColor = if (selected) R.color.white else R.color.tab_text_default
            val badgeBg = if (selected) R.drawable.tab_badge_selected else R.drawable.tab_badge_bg

            titleView.setTextColor(ContextCompat.getColor(requireContext(), textColor))
            badgeView.setBackgroundResource(badgeBg)
        }
    }

    private fun makeFullscreen() {
        val window = requireActivity().window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.hide(
                WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars()
            )
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
