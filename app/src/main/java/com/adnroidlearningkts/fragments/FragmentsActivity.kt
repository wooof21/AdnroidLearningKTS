package com.adnroidlearningkts.fragments

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.adnroidlearningkts.R

/**
 * Fragment: is a modular and reusable component that represents a portion of a user interface
 * or behavior within an activity
 *
 * FrameLayout: a simple container for holding a single Fragment
 */
class FragmentsActivity : AppCompatActivity() {

    private lateinit var simple: Button
    private lateinit var another: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragments)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        simple = findViewById(R.id.fragment_activity_simple)
        another = findViewById(R.id.fragment_activity_another)


        simple.setOnClickListener {
            val simpleFragment: Fragment = SimpleFragment()
            loadFragment(simpleFragment)
        }

        another.setOnClickListener {
            val anotherFragment: Fragment = AnotherFragment()
            loadFragment(anotherFragment)
        }
    }

    /**
     * Fragments Transaction
     *
     * FragmentManager: responsible for managing fragments in activity.
     * It keeps track of the fragment lifecycle and handles transactions involving fragments
     *
     * FragmentTransaction: used to define a set of operations to be performed on fragments
     */
    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager

        //initiate a new fragment transaction
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        //replace the `Default FrameLayout` with the passed fragment
        fragmentTransaction.replace(R.id.fragments_activity_framelayout, fragment)

        //commit the transaction
        fragmentTransaction.commit()
    }
}