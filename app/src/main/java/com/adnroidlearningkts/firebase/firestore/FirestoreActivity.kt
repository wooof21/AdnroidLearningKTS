package com.adnroidlearningkts.firebase.firestore

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.random.Random
import kotlin.text.get

//https://firebase.google.com/docs/firestore/quickstart
class FirestoreActivity : AppCompatActivity() {

    private var docIdToUpdateOrDelete: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_firestore)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tv: TextView = findViewById(R.id.firebase_firestore_tv)
        val tvRandom: TextView = findViewById(R.id.firebase_firestore_tv_random)

        //Initialize an instance of Cloud Firestore:
        val db = Firebase.firestore
        //create a collection of "Users"
        val usersCollection: CollectionReference = db.collection("Users")

        val writeBtn: Button = findViewById(R.id.firebase_firestore_write)
        writeBtn.setOnClickListener {
            writeToFirestore(usersCollection)
        }

        val readBtn: Button = findViewById(R.id.firebase_firestore_read)
        readBtn.setOnClickListener {
            readFromFirestore(usersCollection, tv, tvRandom)
        }

        val updateBtn: Button = findViewById(R.id.firebase_firestore_update)
        updateBtn.setOnClickListener {
            updateDocument(usersCollection)
        }

        val deleteBtn: Button = findViewById(R.id.firebase_firestore_delete)
        deleteBtn.setOnClickListener {
            deleteDocument(usersCollection)
        }

    }

    private fun writeToFirestore(colRef: CollectionReference) {

        //create documents - Documents in a collection can contain different sets of information
        val user1 = hashMapOf("firstname" to "Jack", "lastname" to "Reacher", "age" to 38)
        val user2 = hashMapOf("firstname" to "John", "middle" to "-", "lastname" to "Doe", "age" to 58)

        /**
         * addOnSuccessListener:  does not directly provide the data that was just written in the collection
         * within the success listener's lambda. The DocumentReference only gives
         * the location (path and ID) of the new document, not the content itself.
         *
         * If need to access the data just write:
         * Fetch the Document Again (Less Efficient but Guarantees Data from Firestore)
         */
        val successListener = object : OnSuccessListener<DocumentReference> {
            override fun onSuccess(docRef: DocumentReference?) {
                Toast.makeText(applicationContext, "Doc ID ${docRef?.id} Added", Toast.LENGTH_SHORT).show()
                //            // Fetch the document data using the document reference
//            docRef.get().addOnSuccessListener { documentSnapshot ->
//                if (documentSnapshot.exists()) {
//                    // Document data is available in the DocumentSnapshot
//                    val data = documentSnapshot.data
//                    val firstname = data?.get("firstname")
//                    val age = data?.get("age")
//
//                    Log.d("TAG", "Fetched added user: $firstname, Age: $age")
//                    // Use the fetched data
//                } else {
//                    Log.w("TAG", "Added document not found after getting!")
//                }
//            }
            }
        }

        //adding documents to collection
        colRef.add(user1).addOnSuccessListener(successListener)
        colRef.add(user2).addOnSuccessListener(successListener)
    }

    private fun readFromFirestore(colRef: CollectionReference, tv: TextView, tvRandom: TextView) {
        tv.text = ""
        tvRandom.text = ""
        val successListener =
            object : OnSuccessListener<QuerySnapshot> {
                override fun onSuccess(snapshot: QuerySnapshot?) {
                    Log.i("TAG", "Total Documents ${snapshot?.count()}")
                    if (snapshot != null) {
                        tv.append("---------All Documents-----------")
                        tv.append("\n")
                        tv.append("\n")
                        for (document in snapshot) {
                            tv.append(document.toString())
                            tv.append("\n")
                            tv.append("\n")
                            if(tvRandom.text.length == 0) {
                                docIdToUpdateOrDelete = document.id
                                tvRandom.append("---------Random Document-----------")
                                tvRandom.append("\n")
                                tvRandom.append("\n")
                                tvRandom.append("Doc ID: $docIdToUpdateOrDelete")
                                tvRandom.append("\n")
                                tvRandom.append(document.data.toString())
                            }
                        }
                    }
            }
        }

        //get all documents from specific collection
        colRef.get().addOnSuccessListener(successListener)

        //Or use .document(<docId>) if know the document id -> get specific document - DocumentReference
//        colRef.document(<docId>)
    }

    private fun updateDocument(colRef: CollectionReference) {
        if(docIdToUpdateOrDelete.isEmpty()) return

        val docRef = colRef.document(docIdToUpdateOrDelete)

        /**
         * By design - update() and set()
         * OnSuccessListener for that operation doesn't provide a reference to the
         * updated document itself, but rather a Void type, indicating that the operation
         * completed successfully but without returning any specific data about the modified document.
         *
         * for Efficiency: The server just needs to confirm that the update was applied
         * if need to access the Document after update: Use the existing DocumentReference
         */
        val successListener = object : OnSuccessListener<Void> {
            override fun onSuccess(p0: Void?) {
                Toast.makeText(applicationContext, "Doc Updated", Toast.LENGTH_SHORT).show()
//                // Now, fetch the document to get the updated data
//                docRef.get().addOnSuccessListener { documentSnapshot ->
//                    if (documentSnapshot.exists()) {
//                        // Document data is available in documentSnapshot
//                        val updatedAge = documentSnapshot.getLong("age") // Or get("age")
//                        Log.d("FirestoreUpdate", "Fetched updated age: $updatedAge")
//                        // Use the updated data here
//                    } else {
//                        Log.w("FirestoreUpdate", "Document not found after update!")
//                    }
//                }
            }
        }
        docRef.update("age", Random.nextInt(100)).addOnSuccessListener(successListener)
    }

    private fun deleteDocument(colRef: CollectionReference) {
        if(docIdToUpdateOrDelete.isEmpty()) return

        val docRef = colRef.document(docIdToUpdateOrDelete)

        //no reference to deleted document - document no longer exist
        val successListener = object : OnSuccessListener<Void> {
            override fun onSuccess(p0: Void?) {
                Toast.makeText(applicationContext, "Doc Deleted", Toast.LENGTH_SHORT).show()
            }
        }

        docRef.delete().addOnSuccessListener(successListener)

    }
}