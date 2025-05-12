package com.adnroidlearningkts.fragments.viewpager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.adnroidlearningkts.R
import com.adnroidlearningkts.fragments.navigationdrawer.HomeFragment
import com.adnroidlearningkts.fragments.navigationdrawer.LoginFragment
import com.adnroidlearningkts.fragments.navigationdrawer.MessageFragment
import com.adnroidlearningkts.fragments.navigationdrawer.SettingsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_pager)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewPager2 = findViewById(R.id.activity_view_pager)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val fragments = listOf(HomeFragment(), MessageFragment(), SettingsFragment(), LoginFragment())

        adapter = ViewPagerAdapter(
            supportFragmentManager,
            lifecycle,
            fragments
        )

        viewPager2.adapter = adapter


        val fragmentTitles = mapOf(
            HomeFragment::class.java.name to "Home",
            MessageFragment::class.java.name to "Messages",
            SettingsFragment::class.java.name to "Settings",
            LoginFragment::class.java.name to "Login"
        )
        //add the tabLayout
        tabLayout = findViewById(R.id.activity_view_pager_tablayout)
        TabLayoutMediator(tabLayout, viewPager2) {
            tab, position ->
            // Customize the tab labels based on the Fragment's name
            val fragment = fragments[position]
            tab.text = fragmentTitles[fragment::class.java.name] ?: "Tab ${position + 1}"
        }.attach()
    }
}