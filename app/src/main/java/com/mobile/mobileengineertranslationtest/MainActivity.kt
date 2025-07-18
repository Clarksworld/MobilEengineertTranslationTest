package com.mobile.mobileengineertranslationtest

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout
import com.mobile.mobileengineertranslationtest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Sample tab data: Pair(Title, Count)
    private val tabs = listOf(
        "All" to 12,
        "Completed" to 5,
        "In progress" to 3,
        "Pending" to 2,
        "Cancelled" to 4
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        // Use ViewBinding for cleaner code
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)


        supportActionBar?.hide()

        makeFullscreen()
        setupCustomTabs()




    }

    private fun setupCustomTabs() {
        val tabLayout = binding.tabLayout

        for ((index, tabData) in tabs.withIndex()) {
            val tab = tabLayout.newTab()
            tab.customView = createTabView(tabData.first, tabData.second, selected = index == 0)
            tabLayout.addTab(tab)
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                updateTabView(tab, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                updateTabView(tab, false)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun createTabView(title: String, count: Int, selected: Boolean): View {
        val view = LayoutInflater.from(this).inflate(R.layout.tab_item, null)
        val titleView = view.findViewById<TextView>(R.id.tabTitle)
        val badgeView = view.findViewById<TextView>(R.id.tabBadge)

        titleView.text = title
        badgeView.text = count.toString()

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

            val textColor = if (selected) R.color.white else R.color.tab_text_default
            val badgeBg = if (selected) R.drawable.tab_badge_selected else R.drawable.tab_badge_bg

            titleView.setTextColor(ContextCompat.getColor(this, textColor))
            badgeView.setBackgroundResource(badgeBg)
        }
    }

    private fun makeFullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.hide(android.view.WindowInsets.Type.statusBars() or android.view.WindowInsets.Type.navigationBars())
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }
    }
}
