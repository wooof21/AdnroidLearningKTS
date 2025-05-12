package com.adnroidlearningkts.fragments.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(private val fm: FragmentManager,
                       private val lc: Lifecycle,
                        private val fragments: List<Fragment>): FragmentStateAdapter(fm, lc) {

    //return the total number of fragments in ViewPager2
    override fun getItemCount(): Int {
        return fragments.size
    }

    //creating and returning a Fragment at a specific position in ViewPager2
    override fun createFragment(position: Int): Fragment {

        return fragments[position]
    }


}