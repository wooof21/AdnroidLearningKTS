package com.adnroidlearningkts.adapters.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.adnroidlearningkts.R
import com.adnroidlearningkts.adapters.listview.model.Planet

class PlanetsAdapter(private val context: Context, private val planets: List<Planet>): BaseAdapter() {

    override fun getCount(): Int {
        return planets.size
    }

    override fun getItem(p0: Int): Any {
        return planets[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, paraent: ViewGroup?): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

//        val view: View
//        if(convertView == null) {
//            view = inflater.inflate(
//                R.layout.planets_item_layout,
//                paraent,
//                false)
//        } else {
//            view = convertView
//        }

        /**
         * ?: - elvis operator in Kotlin
         *      - is a concise way to handle nullability and provide a default value when an expression evaluates to null.
         * syntax: result = expression ?: defaultValue
         * if expression != null, then result = expression
         * if expression == null, then result = defaultValue
         */
        val view = convertView ?: inflater.inflate(
            R.layout.planets_item_layout,
            paraent,
            false)

        val planet = getItem(position) as Planet

        val iv = view.findViewById<ImageView>(R.id.planets_item_img)
        val name = view.findViewById<TextView>(R.id.planets_item_name)
        val moon = view.findViewById<TextView>(R.id.planets_item_moon_count)

        iv.setImageResource(planet.img)
        name.text = planet.planetName
        moon.text = planet.moonCount

        //Handle click event
        view.setOnClickListener {
            Toast.makeText(context, "Clicked On ${planet.planetName}", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}