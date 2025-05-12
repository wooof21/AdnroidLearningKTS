package com.adnroidlearningkts.databinding.quadraticequation.model

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.adnroidlearningkts.BR
import java.util.Locale
import kotlin.math.sqrt

data class Equation(var _a: String, var _b: String, var _c: String, var _result: String = ""): BaseObservable() {

    var a: String
        @Bindable get() = _a
        set(value) {
            _a = value
            notifyPropertyChanged(BR.a)
        }

    var b: String
        @Bindable get() = _b
        set(value) {
            _b = value
            notifyPropertyChanged(BR.b)
        }

    var c: String
        @Bindable get() = _c
        set(value) {
            _c = value
            notifyPropertyChanged(BR.c)
        }

    var result: String
        @Bindable get() = _result
        set(value) {
            _result = value
            notifyPropertyChanged(BR.result)
        }

    fun calcEquation(view: View) {
        val aD: Double = a.toDouble()
        val bD: Double = b.toDouble()
        val cD: Double = c.toDouble()

        //calculate the Discriminant
        val disc = (bD*bD) - (4*aD*cD)

        //find the solution or roots
        var r1: Double
        var r2: Double

        if(disc > 0) {
            r1 = -bD + sqrt(disc) / (2 * aD)
            r2 = -bD - sqrt(disc) / (2 * aD)

            result = String.format(Locale.getDefault(),"Root1: %.2f - Root2: %.2f", r1, r2)
        } else if(disc < 0) {
            result = "No Real Root"
        } else {
            r1 = -bD / (2 * aD)
            result = String.format(Locale.getDefault(),"One Real Root: %.2f", r1)
        }
    }
}