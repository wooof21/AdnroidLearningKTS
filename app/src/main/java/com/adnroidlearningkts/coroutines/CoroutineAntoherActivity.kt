package com.adnroidlearningkts.coroutines

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityCoroutineAntoherBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoroutineAntoherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoroutineAntoherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_coroutine_antoher)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coroutine_antoher)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        updateTVFromMianThread()
        updateTVFromIOThread()
    }

    private fun updateTVFromMianThread() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.textView16.text = "Update From ${Thread.currentThread().name} Thread"
        }
    }

    /**
     * TextView.setText()'s Internal Handling: when TextView.setText() is called from a non-main thread,
     *      it doesn't update the UI directly. Instead, it posts the update to the main thread's message queue.
     *      This happens once.
     * Appears to Work: Because the TextView defers the actual UI update to the main thread,
     *      the app doesn't immediately crash. But, it's only working because of the specific way
     *      TextView handles it and is not a general solution. The text in the textview will be updated,
     *      but it will be done in the main thread.
     *
     * Not Recommend
     */
    private fun updateTVFromIOThread() {
        CoroutineScope(Dispatchers.IO).launch {
            binding.textView17.text = "Update From ${Thread.currentThread().name} Thread"
        }
    }
}