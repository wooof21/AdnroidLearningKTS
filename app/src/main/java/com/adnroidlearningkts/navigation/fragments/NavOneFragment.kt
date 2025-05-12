package com.adnroidlearningkts.navigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.FragmentNavOneBinding


class NavOneFragment : Fragment() {

    private lateinit var binding: FragmentNavOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nav_one, container, false)

        //get bundle data
        //!! - not null operator
        val input = requireArguments().getString("name") as String
        binding.navOneFragTv.text = input

        return binding.root
//        return inflater.inflate(R.layout.fragment_nav_one, container, false)
    }

}