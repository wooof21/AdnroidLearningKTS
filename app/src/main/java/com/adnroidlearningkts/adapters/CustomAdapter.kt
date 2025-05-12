package com.adnroidlearningkts.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.adnroidlearningkts.R

/**
 * (private val context: Context, private val items: List<String>):
 *          - This is the primary constructor of the CustomAdapter class.
 */
class CustomAdapter(private val context: Context,
                    private val items: List<String>): BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    //return the data item associate with the specified position in data set
    //p0 -> position
    override fun getItem(p0: Int): Any {
        return items[p0]
    }

    //return a unique identifier for the item at the specified position
    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    /**
     * Return the View that display the data set at the specified position
     *
     * convertView: recycled view that can be reused to optimized performance
     */
    override fun getView(position: Int, convertView: View?, paraent: ViewGroup?): View {

        /**
         * as LayoutInflater: Because getSystemService() returns a generic Any? (nullable Object in Java),
         * we need to cast the result to the specific type we expect, which is LayoutInflater.
         * The `as` keyword in Kotlin performs this type cast.
         * If the cast fails (meaning the system service didn't return a LayoutInflater),
         * it will throw a ClassCastException at runtime.
         * In practice, this exception almost never happens for standard system services like
         * the layout inflater because the system guarantees the correct type.
         */
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view: View
        if(convertView == null) {
            //1. inflate a new view using layout inflater
            view = inflater.inflate(
                R.layout.adapter_custom_item_layout,
                paraent,
                false
            )
        } else {
            //if convertView is not null, reuse it
            view = convertView
        }

        //2. Bind data to view
        val item = getItem(position) as String
        val tv = view.findViewById<TextView>(R.id.adapters_custom_item_tv)
        tv.text = item

        return view
    }
}