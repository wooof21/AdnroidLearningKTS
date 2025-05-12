package com.adnroidlearningkts.coroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SequentialAndParallelCoroutinesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sequential_coroutines)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//        CoroutineScope(Dispatchers.IO).launch {
//            Log.i("TAG", "Call start - Sequential")
//            val first = doSomethingFirst()
//            val then = doSomethingThen()
//
//            /**
//             * doSomethingFirst() take 5s and call complete
//             *
//             * wait another 8s, then doSomethingThen() call complete
//             *
//             * log the result out -> total time took: 5 + 8 = 13s
//             *
//             * function call in sequential by default
//             */
//            val result = first + then
//            Log.i("TAG", "result: $result ")
//        }

        /**
         * To run both fun in parallel - use `async` & `await`
         */
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("TAG", "Call start - Parallel")
            val first = async {
                doSomethingFirst()
            }
            val then = async {
                doSomethingThen()
            }

            /**
             * doSomethingFirst() take 5s and call complete
             *
             * wait another 3s, then doSomethingThen() call complete
             *
             * log the result out -> total time took: 5 + 3 = 8s
             *
             * function call in parallel
             */
            val result = first.await() + then.await()
            Log.i("TAG", "result: $result ")
        }
    }

    private suspend fun doSomethingFirst(): Int {
        delay(5000)
        Log.i("TAG", "doSomethingFirst done")
        return 8;
    }

    private suspend fun doSomethingThen(): Int {
        delay(8000)
        Log.i("TAG", "doSomethingThen done")
        return 18;
    }
}