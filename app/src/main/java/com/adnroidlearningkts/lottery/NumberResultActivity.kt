package com.adnroidlearningkts.lottery

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R

class NumberResultActivity : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var lotteryNums: TextView
    private lateinit var share: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_number_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()

        val username = getUsername()
        val randomNums = generateUniqueRandomNumbers(6)

        lotteryNums.text = randomNums

        //Share the username and generated random numbers with Email app
        share.setOnClickListener{ shareWithEmail(username, randomNums) }
    }

    private fun initView() {
        title = findViewById(R.id.lottery_title)
        lotteryNums = findViewById(R.id.lottery_nums)
        share = findViewById(R.id.lottery_share)
    }

    private fun getUsername(): String {
        /**
         * Retrieve the extras that were added to the Intent
         *
         * ? - indicate the bundle can be null
         */

        val bundle:Bundle? = intent.extras
        return bundle?.getString("username").toString()
    }

    private fun generateUniqueRandomNumbers(count:Int): String {
        //Creates a mutable list containing all numbers in the range
        var end = 50
        if(count == 6) end = 49

        val allNums = (1..end).toMutableList()
        //Then shuffles a random number in the list
        allNums.shuffle()
        //Take the first count elements from the shuffled collection, which are guaranteed to be unique
        val picked =  allNums.take(count).sorted()
        //Convert the list of picked numbers to String by separator " "
        return picked.joinToString(" ")
    }

    private fun shareWithEmail(username:String, lotteryNums:String) {
        /**
         * Implicit Intent: specify an action to be performed and the system determines the
         * appropriate component to handle the action based on the available registered component
         * and their declared capabilities
         *
         * ACTION_SEND: predefined constant that represent the action of sending data
         *  - commonly used when want to share content(text or images) with other apps
         */

        val intent = Intent(Intent.ACTION_SEND)
        /**
         * Specify the type of data that the intent is dealing with
         * "text/plain" - indicate data is in HTML format
         * - commonly used when want to send HTML contents in emails or others when rich text is supported
         */
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Generated the lottery numbers for $username")
        intent.putExtra(Intent.EXTRA_TEXT, "Lottery Numbers: $lotteryNums")
        startActivity(intent)
    }
}