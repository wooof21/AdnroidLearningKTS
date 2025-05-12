package com.adnroidlearningkts.currencyconverter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adnroidlearningkts.R
import java.util.Locale

class CurrencyConverterActivity : AppCompatActivity() {

    private lateinit var currencyEt: EditText
    private lateinit var convertBtn: Button
    private lateinit var result: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_currency_converter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
    }

    private fun initView() {
        currencyEt = findViewById(R.id.currency_amt_et);
        convertBtn = findViewById(R.id.currency_convert_btn)
        result = findViewById(R.id.currency_result)

        convertBtn.setOnClickListener{
            val amt = currencyEt.text.toString().toDouble()
            val convertResult = amt * 1.4
            /**
             * Locale Specification: The String.format() function, when used without a Locale,
             * defaults to the system's default locale.
             * This can lead to unexpected formatting variations across different devices or regions.
             * For example, the decimal separator might be a period (.) in some locales (like the US)
             * and a comma (,) in others (like many European countries).
             * Since the code is explicitly dealing with Canadian dollars (CAD),
             * it's best practice to specify Locale.CANADA to ensure consistent formatting
             * regardless of the user's system settings.
             */
            result.text = String.format(Locale.CANADA, "%.2f CAD", convertResult)
        }
    }
}