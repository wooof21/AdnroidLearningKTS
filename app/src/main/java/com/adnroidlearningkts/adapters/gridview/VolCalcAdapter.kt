package com.adnroidlearningkts.adapters.gridview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.adnroidlearningkts.R
import com.adnroidlearningkts.adapters.gridview.model.Shape

/**
 * ArrayAdapter:
 *  - constructor: to create an ArrayAdapter that will be responsible for adapting data
 *      of type grid item to the ListView or GridView
 *      - context
 *      - resourceId: the resource ID for a layout file that will be used to create a view
 *          for each item in the adapter
 *          * 0: since not using a predefined layout resource, instead,
 *              the custom view need to be inflated manually, use `0` as placeholder
 *      - shapes: data source
 */
class VolCalcAdapter(private val context: Context, private val shapes: List<Shape>) :
    ArrayAdapter<Shape>(context, 0, shapes) {


    override fun getCount(): Int {
        return shapes.size
    }

    override fun getItem(position: Int): Shape {
        return shapes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        val holder: ViewHolder
        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.volume_calc_item_layout, parent, false)

            //Create ViewHolder nad initialize the views
            holder = ViewHolder()
            holder.iv = view.findViewById(R.id.adapter_vol_calc_grid_item_image)
            holder.tv = view.findViewById(R.id.adapter_vol_calc_grid_item_text)

            /**
             * `tag` property of a view: is a general purpose mechanism that allows to associate
             * an arbitrary object with a view
             *  - can store any object
             *  - often employed as a convenient way to attach additional information to a view
             *
             * In context of ViewHolder pattern - `view.tag = holder`: used to associate the ViewHolder
             * object with a view being created or used
             *  - when view is recycled - `convertView != null` - the ViewHolder can be retrieved
             *      from the recycled views by using the `tag`, avoid to create a new ViewHolder
             *      and do the `findViewById` calls again
             */
            view.tag = holder
        } else {
            //reuse the existing ViewHolder
            holder = view?.tag as ViewHolder
        }

        //Bind data to views
        val shape = getItem(position)
        holder.iv.setImageResource(shape.img)
        holder.tv.text = shape.shapeName

        /**
         * `!!` - Non-Null Assertion Operator
         *  - used to assert that an expression is non-null and tells the compiler not to worry
         *      about nullability for the specific expression
         *  - also means that if the expression on the left side is null, the NPE will be thrown
         *      at runtime
         *
         *  `return view!!` - assume the `view` should never be null, if view become null for
         *      some unexpected reason, NPE will be thrown at runtime
         */
        return view!!
    }

    /**
     * Using ViewHolder design pattern: to improve the performance of ListView, GridView, RecycleView
     * by caching views for smoother scrolling.
     * ViewHolder serves as a container for the views of a single item in the gridView, reducing
     * the number of calls to `findViewById()`
     */
    private class ViewHolder {
            lateinit var iv: ImageView
            lateinit var tv: TextView
        }
}