package com.adnroidlearningkts.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.adnroidlearningkts.R


class SimpleFragment : Fragment() {

    //called when the fragment is associated with an activity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Toast.makeText(context, "SimpleFragment onAttach", Toast.LENGTH_SHORT).show()
    }

    //called when the fragment is created
    //Initialization of essential components should be done here
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(context, "SimpleFragment onCreate", Toast.LENGTH_SHORT).show()
    }

    //inflate the fragment layout and initialize UI components here
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Toast.makeText(context, "SimpleFragment onCreateView", Toast.LENGTH_SHORT).show()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple, container, false)
    }

    //called when the fragment becomes visible to the user
    //UI updates and resource allocation can be done here
    override fun onStart() {
        super.onStart()
        Toast.makeText(context, "SimpleFragment onStart", Toast.LENGTH_SHORT).show()
    }

    //called when the fragment is visible and active
    //start animations, acquire resources and register listeners here
    override fun onResume() {
        super.onResume()
        Toast.makeText(context, "SimpleFragment onResume", Toast.LENGTH_SHORT).show()
    }

    //called when the fragment is no longer interacting with the user
    //save UI state or stop ongoing operations here
    override fun onPause() {
        super.onPause()
        Toast.makeText(context, "SimpleFragment onPause", Toast.LENGTH_SHORT).show()
    }

    //called when the fragment is no longer visible to the user
    //stop animations, release the resources, save persistent data here
    override fun onStop() {
        super.onStop()
        Toast.makeText(context, "SimpleFragment onStop", Toast.LENGTH_SHORT).show()
    }

    //called when the view associated with the fragment is being removed
    //clean up resources associated with UI here
    override fun onDestroyView() {
        super.onDestroyView()
        Toast.makeText(context, "SimpleFragment onDestroyView", Toast.LENGTH_SHORT).show()
    }

    //called when the fragment is being destroyed
    //perform final clean up here
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(context, "SimpleFragment onDestroy", Toast.LENGTH_SHORT).show()
    }

    //called when the fragment is being disassociated from the activity
    //release reference to the activity and other resources here
    override fun onDetach() {
        super.onDetach()
        Toast.makeText(context, "SimpleFragment onDetach", Toast.LENGTH_SHORT).show()
    }
}