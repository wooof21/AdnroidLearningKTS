package com.adnroidlearningkts.firebase.realtimedb

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
import com.google.firebase.Firebase
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

//https://firebase.google.com/docs/database/android/start
class RealtimeDBActivity : AppCompatActivity() {

    private lateinit var realtimeDb: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_realtime_dbactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Realtime database reference
        realtimeDb = Firebase.database.reference

        val tv: TextView = findViewById(R.id.firebase_realtimedb_tv)

        val simpleBtn: Button = findViewById(R.id.firebase_realtimedb_simple)
        simpleBtn.setOnClickListener {
            writeSimpleData(tv)
        }

        val customBtn: Button = findViewById(R.id.firebase_realtimedb_custom)
        customBtn.setOnClickListener {
            writeCustomObject(tv)
        }

        val listBtn: Button = findViewById(R.id.firebase_realtimedb_list)
        listBtn.setOnClickListener {
            writeListData(tv)
        }
    }

    private fun writeSimpleData(tv: TextView) {

        /**
         * write data to realtime DB
         *
         * `child()` - create a node in realtime database/can be used as key to a key-value pair
         */
        realtimeDb.child("simple").child("contacts").child("jack").setValue("jack@gmail.com")
        realtimeDb.child("simple").child("contacts").child("john").setValue("john@gmail.com")

        /**
         * read data from realtime DB
         */
        val readListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contact = snapshot.value
                tv.append(contact.toString())
                tv.append("\n")
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        realtimeDb.child("simple").addValueEventListener(readListener)
    }

    private fun writeCustomObject(tv: TextView) {
        val contact = Contact("custom_joe", "joe@gmail.com")

        realtimeDb.child("custom").child("contacts").setValue(contact)

        val readListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contact: Contact? = snapshot.getValue(Contact::class.java)
                tv.append(contact?.name)
                tv.append("\n")
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        realtimeDb.child("custom").child("contacts").addValueEventListener(readListener)
    }

    /**
     * write list data: use `push()` method
     *
     * push() will generate a unique key every time a new child is added to the specified Firebase reference.
     */
    private fun writeListData(tv: TextView) {
        val contact1 = Contact("list_nick", "nick@gmail.com")
        val contact2 = Contact("list_ali", "ali@gmail.com")

        realtimeDb.child("list").child("contacts").push().setValue(contact1)
        realtimeDb.child("list").child("contacts").push().setValue(contact2)

        /**
         * When working with lists, your application should listen for child events rather than the value events used for single objects.
         *
         * Child events are triggered in response to specific operations that happen to the children
         * of a node from an operation such as a new child added through the push() method or a
         * child being updated through the updateChildren() method. Each of these together can be
         * useful for listening to changes to a specific node in a database.
         */
        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                // A new contact has been added, add it to the displayed list
                val contact = dataSnapshot.getValue(Contact::class.java)
                val key = dataSnapshot.key
                Toast.makeText(applicationContext, "New Contact for $key Added", Toast.LENGTH_SHORT).show()
                // ...
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
                // A contact has changed, use the key to determine if displaying this
                // contact and if so displayed the changed contact.
                val newContact = dataSnapshot.getValue(Contact::class.java)
                val key = dataSnapshot.key
                Toast.makeText(applicationContext, "Contact Changed for $key", Toast.LENGTH_SHORT).show()
                // ...
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                // A contact has changed, use the key to determine if displaying this
                // contact and if so remove it.
                val contactRemoved = dataSnapshot.getValue(Contact::class.java)
                val key = dataSnapshot.key
                Toast.makeText(applicationContext, "Contact Removed for $key", Toast.LENGTH_SHORT).show()
                // ...
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {

                // A contact has changed position, use the key to determine if
                // displaying this contact and if so move it.
                val contactMoved = dataSnapshot.getValue(Contact::class.java)
                val key = dataSnapshot.key
                Toast.makeText(applicationContext, "Contact Moved for $key", Toast.LENGTH_SHORT).show()
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
//        realtimeDb.child("list").child("contacts").addChildEventListener(childEventListener)

        /**
         * While using a ChildEventListener is the recommended way to read lists of data,
         * there are situations where attaching a ValueEventListener to a list reference is useful.
         *
         * Attaching a ValueEventListener to a list of data will return the entire list of data as a single DataSnapshot,
         * which you can then loop over to access individual children.
         */

        val valueListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                /**
                 * Asynchronous Nature of Data Loading: size may appear wrong (off by one) from actual size
                 *
                 * The most fundamental way to deal with the asynchronous nature is to embrace it
                 * and rely on the listeners to provide you with the most up-to-date data.
                 * When you attach a ValueEventListener or ChildEventListener, Firebase will
                 * automatically send you updates whenever the data changes on the server.
                 * If you write data and then immediately see an incorrect size in the
                 * ValueEventListener's onDataChange, it's likely because the listener received
                 * the snapshot before the write operation was fully processed and propagated to
                 * the client. Firebase will send another update to your listener once the write
                 * is confirmed and the data is available. Instead of immediately checking
                 * the size after writing, wait for the ValueEventListener to fire again with
                 * the updated data. Your onDataChange method will be called again
                 * when Firebase synchronizes the new data to your client.
                 *
                 */
//                Toast.makeText(applicationContext, "Size: ${snapshot.children.count()}", Toast.LENGTH_SHORT).show()
                Log.i("Size", "${snapshot.children.count()}")
                for (data in snapshot.children) {
                    Log.i("keys", data.key.toString())
//                    Toast.makeText(applicationContext, "$data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        realtimeDb.child("list").child("contacts").addValueEventListener(valueListener)
    }
}