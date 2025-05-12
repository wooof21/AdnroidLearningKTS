package com.adnroidlearningkts.coroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityCoroutinesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutinesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoroutinesBinding
    private var count: Int = 0

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_coroutines)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coroutines)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /**
         * Problem without Coroutines:
         *
         * Start Download first, Main Thread is blocked by the large file download operation,
         * when click `start Counting`, UI TextView wont be updated until file download operation
         * is done
         */
        binding.coroutinesCounting.setOnClickListener {
            binding.coroutinesCounter.text = count++.toString()
        }

        binding.coroutinesDownload.setOnClickListener {

            /**
             * Using coroutines:
             *      https://vtsen.hashnode.dev/globalscope-vs-viewmodelscope-vs-lifecyclescope-vs-remembercoroutinescope
             *
             * CoroutineScope: Creating a custom CoroutineScope and explicitly specifying the Dispatchers
             *      CoroutineScope(Dispatchers.IO).launch {
             *                 downloadLargeFileFromNet()
             *             }
             *
             *      * GlobalScope: use default CoroutineContext -> Dispatchers.Default
             *          GlobalScope.launch {
             *                 downloadLargeFileFromNet()
             *             }
             *
             *       * LifecycleScope: tied to lifecycle of owner(Activity/Fragment), no need to
             *          manually check and cancel CoroutineScope when lifecycle owner is about to be destroyed
             *              * https://developer.android.com/topic/libraries/architecture/coroutines
             *              * without specifying the `Dispatchers`, lifecycleScope use Default dispatcher
             *                  Dispatchers.Default
             *
             *       * ViewModelScope: same as LifeycycleScope, it will live as long the ViewModel is alive
             *          * ViewModel: the class that manages and stores UI-related data
             *          * https://developer.android.com/topic/libraries/architecture/coroutines
             *
             *  Dispatchers:
             *      * Dispatchers.Main: For UI interactions only.
             *          * Use Cases:
             *              - Updating UI elements (TextViews, Images, Buttons, etc.).
             *              - Handling user input events.
             *              - Performing any operation that must be executed on the main thread.
             *      * Dispatchers.IO: For I/O-bound tasks (networking, file access, database).
             *          * Use Cases:
             *              - Making network requests (fetching data from an API).
             *              - Reading from or writing to files.
             *              - Accessing databases (Room, SQLite, etc.).
             *              - Any task that primarily involves waiting for external resources.
             *      * Dispatchers.Default: For CPU-bound tasks (heavy computations).
             *          * Use Cases:
             *              - Sorting large lists.
             *              - Processing large datasets in memory.
             *              - Performing complex calculations or algorithms.
             *              - Any task that requires significant CPU power.
             *  By selecting the appropriate dispatcher for the coroutines, to ensure that
             *  the application remains responsive, performs efficiently, and avoids blocking
             *  the main thread. In Android development, a common pattern is to switch between
             *  Dispatchers.IO or Dispatchers.Default for background work and then switch back
             *  to Dispatchers.Main to update the UI using withContext().
             *
             *  when use `lifecycleScope.launch(Dispatchers.Default)` or `lifecycleScope.launch {}`
             *  UI freezes even the UI update is happening in Main Thread with `withContext(Dispatchers.Main)`,
             *  while using `lifecycleScope.launch(Dispatchers.IO)` runs smoothly,
             *
             *  this is because:
             *      * Rapid Switching and Main Thread Overload:
             *          - Dispatchers.Default is designed for CPU-bound tasks and uses a thread pool roughly the size of the CPU cores.
             *          -  the rapid succession of 100,000 calls to withContext(Dispatchers.Main)
             *              from a Dispatchers.Default thread can still overwhelm the main thread's message queue.
             *      * Dispatchers.IO is More Suitable for "Waiting":
             *          - Dispatchers.IO has a larger thread pool and is designed for tasks that
             *              involve waiting (like I/O operations, even simulated ones).
             *          - the repeated calls to withContext and the context switching might behave
             *              more like "waiting" from the perspective of the dispatcher.
             *          - With Dispatchers.IO, the larger thread pool might handle the rapid switching
             *              and queuing of main thread updates slightly better, potentially distributing
             *              the load or having more available threads to handle the background execution part of the loop
             */
//            val job = CoroutineScope(Dispatchers.IO).launch {
            lifecycleScope.launch(Dispatchers.IO) {
                downloadLargeFileFromNet()
            }

            /**
             * When create a custom CoroutineScope
             * it's crucial to manage its lifecycle and cancel it when the Activity is no longer needed.
             * otherwise, the coroutines launched within that scope will continue to run in
             * the background even after the Activity is destroyed, leading to memory leaks and wasted resources.
             *
             * need to Cancel the Scope in onDestroy()
             * job.cancel()
             *
             * This is one of the main reasons why LifecycleScope and ViewModelScope are
             * generally preferred in Android development, as they automatically handle this cancellation
             */
        }

        binding.coroutinesAnother.setOnClickListener {
            startActivity(Intent(this, CoroutineAntoherActivity::class.java))
        }

        binding.coroutinesSeq.setOnClickListener {
            startActivity(Intent(this, SequentialAndParallelCoroutinesActivity::class.java))
        }
    }

    private suspend fun downloadLargeFileFromNet() {
        for(i in 1..100000) {
            Log.i("File Downloading", "Downloading $i in ${Thread.currentThread().name} ")
            /**
             * Error: android.view.ViewRootImpl$CalledFromWrongThreadException:
             *      Only the original thread that created a view hierarchy can touch its views.
             *      Expected: main Calling: DefaultDispatcher-worker-1
             *
             * Crash:
             *      * Synchronous: The for loop is synchronous.
             *          The code within the loop executes sequentially, one iteration after the other, on the same thread.
             *      * Rapid UI Updates: You are calling binding.coroutinesDownloadProgress.text = ... 100,000 times in
             *          rapid succession, and all those calls are happening on a background thread.
             *      * Even though TextView might eventually dispatch those updates to the main thread,
             *           there's no mechanism to ensure that they are batched or that the background thread
             *             won't make subsequent calls before the previous ones get handled.
             *      * The sheer volume of immediate calls from a background thread overwhelms the UI system and triggers the crash.
             *
             * use withContext() to switch coroutine Dispatchers
             *
             */
            withContext(Dispatchers.Main) {
                binding.coroutinesDownloadProgress.text = "$i - Thread: ${Thread.currentThread().name}"
            }
        }
    }
}