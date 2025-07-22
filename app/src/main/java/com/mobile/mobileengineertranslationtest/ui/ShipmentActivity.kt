package com.mobile.mobileengineertranslationtest.ui

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mobile.mobileengineertranslationtest.R
import com.mobile.mobileengineertranslationtest.adapters.ShipmentPagerAdapter
import com.mobile.mobileengineertranslationtest.databinding.ActivityShipmentBinding
import com.mobile.mobileengineertranslationtest.ui.fragments.AllShipmentsFragment
import com.mobile.mobileengineertranslationtest.ui.fragments.CanceledFragment
import com.mobile.mobileengineertranslationtest.ui.fragments.FinishedFragment
import com.mobile.mobileengineertranslationtest.ui.fragments.InTransitFragment
import com.mobile.mobileengineertranslationtest.ui.fragments.PendingFragment
import com.mobile.mobileengineertranslationtest.ui.home_ui.MainActivity


//class MainActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityMainBinding
//
//    // Sample tab data: Pair(Title, Count)
//    private val tabs = listOf(
//        "All" to 12,
//        "Completed" to 5,
//        "In progress" to 3,
//        "Pending" to 2,
//        "Cancelled" to 4
//    )
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//
//
//        // Use ViewBinding for cleaner code
//        binding = ActivityMainBinding.inflate(layoutInflater)
//
//
//        setContentView(binding.root)
//
//
//        supportActionBar?.hide()
//
//        makeFullscreen()
//        setupCustomTabs()
//
//
//
//
//    }
//
//    private fun setupCustomTabs() {
//        val tabLayout = binding.tabLayout
//
//        for ((index, tabData) in tabs.withIndex()) {
//            val tab = tabLayout.newTab()
//            tab.customView = createTabView(tabData.first, tabData.second, selected = index == 0)
//            tabLayout.addTab(tab)
//        }
//
//        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                updateTabView(tab, true)
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                updateTabView(tab, false)
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {}
//        })
//    }
//
//    private fun createTabView(title: String, count: Int, selected: Boolean): View {
//        val view = LayoutInflater.from(this).inflate(R.layout.tab_item, null)
//        val titleView = view.findViewById<TextView>(R.id.tabTitle)
//        val badgeView = view.findViewById<TextView>(R.id.tabBadge)
//
//        titleView.text = title
//        badgeView.text = count.toString()
//
//        // Apply initial styling
//        val textColor = if (selected) R.color.white else R.color.tab_text_default
//        val badgeBg = if (selected) R.drawable.tab_badge_selected else R.drawable.tab_badge_bg
//
//        titleView.setTextColor(ContextCompat.getColor(this, textColor))
//        badgeView.setBackgroundResource(badgeBg)
//
//        return view
//    }
//
//    private fun updateTabView(tab: TabLayout.Tab?, selected: Boolean) {
//        tab?.customView?.let { view ->
//            val titleView = view.findViewById<TextView>(R.id.tabTitle)
//            val badgeView = view.findViewById<TextView>(R.id.tabBadge)
//
//            val textColor = if (selected) R.color.white else R.color.tab_text_default
//            val badgeBg = if (selected) R.drawable.tab_badge_selected else R.drawable.tab_badge_bg
//
//            titleView.setTextColor(ContextCompat.getColor(this, textColor))
//            badgeView.setBackgroundResource(badgeBg)
//        }
//    }
//
//    private fun makeFullscreen() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            window.setDecorFitsSystemWindows(false)
//            window.insetsController?.hide(android.view.WindowInsets.Type.statusBars() or android.view.WindowInsets.Type.navigationBars())
//        } else {
//            @Suppress("DEPRECATION")
//            window.decorView.systemUiVisibility = (
//                    View.SYSTEM_UI_FLAG_FULLSCREEN
//                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                    )
//        }
//    }
//}


class ShipmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShipmentBinding

    private val titles = listOf("All", "Completed", "In progress", "Pending", "Cancelled")
    private val counts = listOf(12, 5, 3, 2, 4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityShipmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        makeFullscreen()
        setupViewPagerWithTabs()

        binding.backButton.setOnClickListener {
//            onBackPressedDispatcher.onBackPressed()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }




        // Animate TabLayout
        val slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_from_right_side)
        binding.tabLayout.startAnimation(slideIn)
    }

    private fun setupViewPagerWithTabs() {
        val fragments = listOf(
            AllShipmentsFragment(),
            FinishedFragment(),
            InTransitFragment(),
            PendingFragment(),
            CanceledFragment()
        )

        val adapter = ShipmentPagerAdapter(this, fragments)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.customView = createTabView(titles[position], counts[position], position == 0)
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) = updateTabView(tab, true)
            override fun onTabUnselected(tab: TabLayout.Tab?) = updateTabView(tab, false)
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun createTabView(title: String, count: Int, selected: Boolean): View {
        val view = LayoutInflater.from(this).inflate(R.layout.tab_item, null)
        val titleView = view.findViewById<TextView>(R.id.tabTitle)
        val badgeView = view.findViewById<TextView>(R.id.tabBadge)

        titleView.text = title
        badgeView.text = count.toString()

//        titleView.setTextColor(ContextCompat.getColorStateList(this, R.color.tab_text_selected))
//        badgeView.setBackgroundResource(
//            if (selected) R.drawable.tab_badge_selected else R.drawable.tab_badge_bg
//        )

        // Apply initial styling
        val textColor = if (selected) R.color.white else R.color.tab_text_default
        val badgeBg = if (selected) R.drawable.tab_badge_selected else R.drawable.tab_badge_bg

        titleView.setTextColor(ContextCompat.getColor(this, textColor))
        badgeView.setBackgroundResource(badgeBg)

        return view
    }

    private fun updateTabView(tab: TabLayout.Tab?, selected: Boolean) {
        tab?.customView?.let { view ->
            val titleView = view.findViewById<TextView>(R.id.tabTitle)
            val badgeView = view.findViewById<TextView>(R.id.tabBadge)

//            titleView.setTextColor(ContextCompat.getColorStateList(this, R.color.tab_text_selected))
//            badgeView.setBackgroundResource(
//                if (selected) R.drawable.tab_badge_selected else R.drawable.tab_badge_bg
//            )

            val textColor = if (selected) R.color.white else R.color.tab_text_default
            val badgeBg = if (selected) R.drawable.tab_badge_selected else R.drawable.tab_badge_bg

            titleView.setTextColor(ContextCompat.getColor(this, textColor))
            badgeView.setBackgroundResource(badgeBg)
        }
    }

    private fun makeFullscreen() {
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
        window.statusBarColor = Color.TRANSPARENT
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }
}
