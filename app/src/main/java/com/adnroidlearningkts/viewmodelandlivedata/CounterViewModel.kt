package com.adnroidlearningkts.viewmodelandlivedata

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * ViewModel class: used to store and manage UI related data in a lifecycle aware manner
 *  - manage the data (counter) in a way that survives configuration changes - screen rotation,
 *      and is scoped to the lifecycle of the associated activity or fragment
 */
class CounterViewModel: ViewModel() {

//    private var counter: Int = 0
    /**
     * Use of Live Data
     *
     * 1. Use _counter as a backing property for internal mutation
     *
     * Encapsulation: It's crucial to encapsulate the mutable version of LiveData (i.e., MutableLiveData)
     * within the ViewModel. This prevents accidental or unauthorized modifications from outside the ViewModel.
     *
     * Read-Only Access: The public counter is now a read-only LiveData<Int>, meaning
     * components like Activities or Fragments can observe changes but cannot directly modify the value.
     * This is a core principle of data flow management in MVVM (Model-View-ViewModel).
     *
     * Best Practice: This pattern (backing property + read-only exposure) is considered
     * the standard way to manage LiveData within ViewModels in Android development.
     */
    private val _counter = MutableLiveData<Int>()
    // 2. Expose an immutable LiveData for observers
    val counter: LiveData<Int> = _counter


    init {
        /**
         * setValue(): used for update the value of livedata,
         * if the current thread is the UI thread it updates the value inmediately,
         * otherwise it throws an Exception.
         *
         * postValue(): this method updates the value of livedata in the background thread,
         * and then it will be notified to the UI thread.
         *
         * If it is needed to update the value from a background thread _counter.postValue() should be used.
         *
         * When the UI is created, it will immediately receive the initial value if it is
         * on the UI thread or later if it is in the background thread.
         */
        _counter.value = 0
    }

    fun increaseCounter(view: View) {
//        counter++
        // 4. Use a more idiomatic way to update the LiveData value
        _counter.value = (_counter.value ?: 0) + 1
        //Or using post value if you need to update the value from a background thread
        //_counter.postValue((_counter.value ?: 0) + 1)
    }

}