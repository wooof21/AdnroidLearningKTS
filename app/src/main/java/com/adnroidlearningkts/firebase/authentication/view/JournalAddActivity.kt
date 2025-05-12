package com.adnroidlearningkts.firebase.authentication.view

import android.app.ComponentCaller
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityJournalAddBinding
import com.adnroidlearningkts.firebase.authentication.model.Journal
import com.adnroidlearningkts.firebase.authentication.model.JournalUser
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class JournalAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJournalAddBinding

    private var currentUserId: String = ""
    private var currentUsername: String = ""

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: StorageReference
    private lateinit var collectionRef: CollectionReference

    private lateinit var imageUri: Uri

    /**
     * Register a request to start an activity for result
     *
     * The ActivityResultRegistry requires that you register your activity for a result before
     * the activity reaches the STARTED state. This is because the system needs to be aware of
     * the pending activity result request and how to deliver the result back to your activity
     * when it's ready. If you register while the activity is in RESUMED state, it can lead to
     * unpredictable behavior and the system might not be able to properly handle the activity result.
     */
    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            imageUri = uri
            // Set the image to your ImageView
            binding.journalAddPostImageview.setImageURI(uri)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_journal_add)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_journal_add)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = Firebase.auth

        db = Firebase.firestore
        collectionRef = db.collection("Journals")

        storage = Firebase.storage.reference

        binding.apply {
            journalAddProgressbar.visibility = View.GONE
            if(JournalUser.isUserAvailable()) {
                currentUserId = JournalUser.userId.toString()
                currentUsername = JournalUser.username.toString()
                journalAddPostUsername.text = currentUsername
            }
        }

        binding.journalAddCameraBtn.setOnClickListener {
            selectImageFromGallery()
        }

        binding.journalAddSaveBtn.setOnClickListener {
            saveJournal()
        }

    }

    private fun selectImageFromGallery() {
        // Register for a single item result

        pickImage.launch("image/*")
    }

    private fun saveJournal() {
        val title: String = binding.journalAddPostTitleEt.text.toString().trim()
        val thoughts: String = binding.journalAddPostThoughtEt.text.toString().trim()

        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(thoughts)
            && imageUri != null) {
            binding.journalAddProgressbar.visibility = View.VISIBLE
            //Image saving path: .../journal_images/<image_name>
            val filePath: StorageReference = storage.child("journal_images")
                .child("image_${Timestamp.now().seconds}")

            //Uploading Image to Firebase Storage
            filePath.putFile(imageUri).addOnSuccessListener {
                //get file download url and save url to Firebase Firestore
                filePath.downloadUrl.addOnSuccessListener {
                    val imageUrl = it.toString()
                    val timestamp: Timestamp = Timestamp.now()
                    val journal: Journal = Journal(
                        title, thoughts,
                        imageUrl, currentUserId,
                        currentUsername,timestamp
                    )
                    //add Journal to Journal Collection
                    collectionRef.add(journal).addOnSuccessListener {
                        binding.journalAddProgressbar.visibility = View.GONE
                        startActivity(Intent(this, JournalListActivity::class.java))
                        finish()
                    }.addOnFailureListener {
                        binding.journalAddProgressbar.visibility = View.GONE
                        Toast.makeText(this, "Journal Post Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            Toast.makeText(this, "Title/Thoughts/Image Cannot Be Empty", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}