package com.adnroidlearningkts.dependencyinjection.mvvm.view

import androidx.recyclerview.widget.DiffUtil
import com.adnroidlearningkts.dependencyinjection.mvvm.model.pojo.Category

/**
 * Used to efficiently update the RecyclerView when the data changes.
 * It is part of DiffUtil, which helps optimize list update instead of reloading everything
 */
class CategoryDiffCallback: DiffUtil.ItemCallback<Category>() {

    /**
     * Check if 2 Category items represent the same entity.
     * If the same Category appears in both old and new list,
     * DiffUtil wont reload the entire row, only updating its content if needed
     */
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.name == newItem.name
    }

    /**
     * Check if the actual content of an item has changed
     *
     * If Category's name remains the same but image changes, DiffUtil only updates the image
     * instead of reloading everything
     */
    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}