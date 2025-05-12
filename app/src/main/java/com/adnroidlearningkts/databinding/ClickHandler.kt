package com.adnroidlearningkts.databinding

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.adnroidlearningkts.databinding.quadraticequation.QuadraticEquationActivity

class ClickHandler(private val context: Context) {

    fun dataBindingClickFunCall(view: View, name: String) {
        Toast.makeText(context, "$name Clicked", Toast.LENGTH_SHORT).show()
    }

    fun startQuadraticEquActivity(view: View) {
        val intent = Intent(context, QuadraticEquationActivity::class.java)
        context.startActivity(intent)
    }
}