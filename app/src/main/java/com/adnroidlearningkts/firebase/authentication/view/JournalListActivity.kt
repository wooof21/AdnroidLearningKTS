package com.adnroidlearningkts.firebase.authentication.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adnroidlearningkts.R
import com.adnroidlearningkts.databinding.ActivityJournalListBinding
import com.adnroidlearningkts.firebase.authentication.model.Journal
import com.adnroidlearningkts.firebase.authentication.model.JournalUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class JournalListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJournalListBinding

    //Firebase References
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var collectionRef: CollectionReference
    //https://firebase.google.com/docs/storage/android/start
    private lateinit var storage: StorageReference


    private lateinit var journals: MutableList<Journal>
    private lateinit var adapter: JournalAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_journal_list)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_journal_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = Firebase.auth

        db = Firebase.firestore
        collectionRef = db.collection("Journals")

        storage = Firebase.storage.reference


        binding.journalListRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.journalListRecyclerview.setHasFixedSize(true)

        /**
         * Posts(journals) List
         *
         * arrayListOf(): creates a new, mutable list, specifically an ArrayList.
         * listOf(): Creates an immutable list.
         */
        journals = arrayListOf()

        adapter = JournalAdapter(journals)

        binding.journalListRecyclerview.adapter = adapter
    }

    //Get all posts
    override fun onStart() {
        super.onStart()

        //https://firebase.google.com/docs/firestore/query-data/queries
        collectionRef.whereEqualTo("userId", JournalUser.userId)
            .get()
            .addOnSuccessListener {
                if(!it.isEmpty) {
                    it.forEach {
                        //convert QueryDocumentSnapshot to Journal Object
                        val journal = it.toObject(Journal::class.java)
                        journals.add(journal)
                    }

                    //update recycler view
                    if(journals.isEmpty()) {
                        binding.journalListNoPost.visibility = View.VISIBLE
                        binding.journalListRecyclerview.visibility = View.GONE
                    } else {
                        binding.journalListNoPost.visibility = View.GONE
                        binding.journalListRecyclerview.visibility = View.VISIBLE
                        adapter.notifyDataSetChanged()
                    }
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.journal_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.journal_menu_add -> startActivity(Intent(this, JournalAddActivity::class.java))
            R.id.journal_menu_signout -> {
                auth.signOut()
                startActivity(Intent(this, JournalActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}