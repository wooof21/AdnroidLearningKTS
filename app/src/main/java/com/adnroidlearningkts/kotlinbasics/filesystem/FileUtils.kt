package com.adnroidlearningkts.kotlinbasics.filesystem

import android.content.Context
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception


/**
 * Write and Read data to/from the file
 */


const val FILE_NAME = "file_name.dat" //.txt .bat

fun writeData(items: SnapshotStateList<String>, context: Context) {

    val fos: FileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
    val oos = ObjectOutputStream(fos)
    //convert SnapshotStateList to a serializable array list
    val itemList = ArrayList<String>()
    itemList.addAll(items)

    //write to file
    oos.writeObject(itemList)

    //close the file
    oos.close()
}


fun readData(context: Context): SnapshotStateList<String> {
    var itemList : ArrayList<String>

    try {
        //when the file is not exist, will throw an FileNotFoundException
        val fis = context.openFileInput(FILE_NAME)
        val ois = ObjectInputStream(fis)
        itemList = ois.readObject() as ArrayList<String>
    } catch (e: FileNotFoundException) {
        itemList = ArrayList()
    }


    //convert array list to SnapshotStateList
    val items = SnapshotStateList<String>()
    items.addAll(itemList)

    return items
}


