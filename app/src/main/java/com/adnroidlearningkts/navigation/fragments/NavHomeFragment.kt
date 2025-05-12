package com.adnroidlearningkts.navigation.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.FragmentNavHomeBinding


class NavHomeFragment : Fragment() {

    private lateinit var binding: FragmentNavHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nav_home, container, false)


        // Handle the click event on button
        binding.navHomeFragmentSubmit.setOnClickListener {

            if(TextUtils.isEmpty(binding.navHomeFragmentEt.text.toString())) {
                Toast.makeText(context, "Enter the Name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //passing data
            val bundle: Bundle = bundleOf("name" to binding.navHomeFragmentEt.text.toString())

            //get the NavController from the View and navigate to destination fragment using
            // the Action ID defined in nav graph file
            it.findNavController().navigate(R.id.action_navHomeFragment_to_navOneFragment, bundle)
        }

        return binding.root
//        return inflater.inflate(R.layout.fragment_nav_home, container, false)
    }

}