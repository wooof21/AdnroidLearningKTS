package com.adnroidlearningkts.dependencyinjection.firebase

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R
import com.adnroidlearningkts.dependencyinjection.firebase.repository.DataRepo
import com.adnroidlearningkts.dependencyinjection.firebase.viewmodel.HFViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltFirebaseActivity : AppCompatActivity() {

    /**
     * inject the ViewModel: specifically designed for injecting instances of the ViewModel
     *
     * by viewModels(): Kotlin property delegate is used to lazily initiate and initialize the ViewModel with Hilt
     *  * no need to manually create ViewModelProvider, Hilt does this
     *
     *  *Lazy Initialization: The ViewModel instance is not created immediately when the Activity or Fragment is created.
     *  It is created only when first access the viewModel property (e.g., by calling viewModel.getData())
     *  This can improve the startup performance of the Activity or Fragment,
     *  as it don't incur the cost of creating the ViewModel until it's actually needed.
     */
    private val viewModel: HFViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hilt_firebase)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tv: TextView = findViewById(R.id.activity_hilt_firebase_tv)

        viewModel.getData().observe(this) {
            tv.text = it.joinToString("\n")
        }
    }
}