package com.adnroidlearningkts.firebase.authentication.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityJournalBinding
import com.adnroidlearningkts.firebase.authentication.model.JournalUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * Firestore & Storage & Authentication
 * Auth: https://firebase.google.com/docs/auth
 */
class JournalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJournalBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_journal)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_journal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = Firebase.auth

        binding.journalActivityCreateAccount.setOnClickListener {
            val intent = Intent(this, JournalSignupActivity::class.java)
            startActivity(intent)
        }

        binding.journalActivityLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = binding.journalActivityEmail.text.toString()
        val password = binding.journalActivityPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                val user = auth.currentUser!!
                //save user info in JournalUser singleton
                JournalUser.setUserDetails(user.displayName, user.uid)
                toJournalListPage()
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun toJournalListPage() {
        startActivity(Intent(this, JournalListActivity::class.java))
    }

    override fun onStart() {
        super.onStart()
        if(JournalUser.isUserAvailable()) {
            toJournalListPage()
        }
    }
}