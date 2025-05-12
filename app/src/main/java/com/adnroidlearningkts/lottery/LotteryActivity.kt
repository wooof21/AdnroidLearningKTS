package com.adnroidlearningkts.lottery

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R

class LotteryActivity : AppCompatActivity() {

    private lateinit var nameET: EditText
    private lateinit var generate: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lottery)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
    }

    private fun initView() {
        nameET = findViewById(R.id.lottery_name_et)
        generate = findViewById(R.id.lottery_generate_btn)

        generate.setOnClickListener{
            val name = nameET.text.toString()

            //Explicit Intent
            val intent: Intent = Intent(this, NumberResultActivity::class.java)
            intent.putExtra("username", name)
            startActivity(intent)
        }
    }
}