package com.adnroidlearningkts.firebase.authentication.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityJournalSignupBinding
import com.adnroidlearningkts.firebase.authentication.model.JournalUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class JournalSignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityJournalSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_journal_signup)
        binding = ActivityJournalSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.journalSignupBtn.setOnClickListener {
            createUserWithEmail()
        }
    }

    private fun createUserWithEmail() {
        val email: String = binding.journalSignupEmail.text.toString()
        val password: String = binding.journalSignupPassword.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(
                        baseContext,
                        "Create User Success.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    saveAndUpdateUserInfo()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }


    private fun saveAndUpdateUserInfo() {
        val user = auth.currentUser!!
        //set user displayName
        val profileUpdates = userProfileChangeRequest {
            displayName = binding.journalSignupUsername.text.toString().trim()
        }
        user.updateProfile(profileUpdates).addOnSuccessListener {
            JournalUser.setUserDetails(user.displayName, user.uid)
            startActivity(Intent(this, JournalActivity::class.java))
        }
    }
}