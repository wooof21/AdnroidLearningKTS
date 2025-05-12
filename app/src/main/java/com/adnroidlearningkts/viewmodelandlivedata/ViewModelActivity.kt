package com.adnroidlearningkts.viewmodelandlivedata

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityViewModelBinding


/**
 * Problem: when the device configuration change (screen rotation),
 * the counter state will be lost (reset to zero)
 *
 * Solution: use ViewModel and LiveData
 *
 * JetPack Lifecycle ViewModel dependency: https://developer.android.com/jetpack/androidx/releases/lifecycle#kts
 *
 * JetPack Lifecycle ViewModel and ViewModel in MVVM are not exactly the same
 *
 * The MVVM ViewModel: is the concept of what should be done: separating UI-related logic,
 *  data handling, and commands from the View.
 *
 * The Jetpack Lifecycle ViewModel: is the tool that helps you achieve the MVVM ViewModel's responsibilities.
 */
class ViewModelActivity : AppCompatActivity() {

    private lateinit var counterViewModel: CounterViewModel
    private lateinit var binding: ActivityViewModelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_view_model)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_model)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /**
         * Initialize the ViewModel
         *
         * ViewModelProvider: responsible for creating and managing the lifecycle of a ViewModel
         *
         * owner: Activity or Fragment -> this
         *
         * if ViewModel already exist(associate with current owner), it return and reuse
         */
        counterViewModel = ViewModelProvider(this).get(CounterViewModel::class.java)


        /**
         * using LiveData to observe the counter
         *  https://developer.android.com/jetpack/androidx/releases/lifecycle
         *
         * LiveData is lifecycle aware, means it will automatically start and stop
         * observing data based on the lifecycle state of the activity or fragment,
         * preventing potential memory leaks
         *
         * binding.lifecycleOwner = this - set this to enable the observe when deal with LiveData
         * sets the lifecycleOwner property of a data binding object (binding) to the
         * current instance of the Activity or Fragment (this) that contains the associated layout.
         *
         * By setting lifecycleOwner:
         * 1. Automatic UI Updates with LiveData:
         *      * If your layout XML is bound to LiveData objects (which hold data that can change over time),
         *      the Data Binding Library will automatically observe these LiveData objects.
         *      * Crucially, it does so in a lifecycle-aware manner. This means that the UI will
         *      only be updated when the associated Activity or Fragment is in an active state (e.g., started or resumed).
         *      * When the Activity or Fragment is stopped or destroyed, the observer is automatically removed.
         *      This prevents memory leaks and crashes that could occur if you tried to update the UI when it was no longer visible.
         * 2. Lifecycle Awareness for Binding Adapters:
         *      * Binding adapters are custom methods that you can use to perform specific UI updates.
         *      * When you set lifecycleOwner, these binding adapters also become lifecycle-aware.
         *      This allows them to adjust their behavior based on the lifecycle state, just like LiveData observers.
         *
         * Without it: UI is not updated when click the button, will only be updated when there is
         *      an configuration change
         */

        binding.lifecycleOwner = this
        binding.counterviewmodel = counterViewModel

    }
}