package com.adnroidlearningkts.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import com.adnroidlearningkts.R
import kotlinx.coroutines.launch
import retrofit2.Response


/**
 * https://jsonplaceholder.typicode.com/
 *
 * Free fake and reliable API for testing and prototyping.
 */
class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_retrofit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tv = findViewById<TextView>(R.id.retrofit_tv)

        val retrofit = RetrofitConfig.instance

        val apiService = retrofit.create(AlbumApiInterface::class.java)

        val getAllAlbumsLiveData: LiveData<Response<List<Album>>> =
            /**
             * liveData { ... }: the liveData coroutine builder.
             *
             *      * primary purpose is to create and manage a LiveData.
             *      It implicitly sets up a coroutine within a LiveDataScope to perform the work
             *      needed to produce values for that LiveData.
             *      The work within the liveData block is specifically focused on generating data to be emitted.
             *
             * emit(..): a special function available within the liveData builder. It takes the response object
             * and sets it as the value of the LiveData object being built.
             * Observers that are actively observing this LiveData will be notified of this new value.
             */
            liveData {
                val response1 = apiService.getAllAlbums()
                emit(response1)
            }

        getAllAlbumsLiveData.observe(this) {
            val albums = it.body()
            if(albums != null) {
                for(album in albums) {
//                    Log.i("TAG", album.toString())
//                    tv.append(album.toString())
//                    tv.append("\n")
                }
            }
        }

        val getAlbumsByUserIdLiveData: LiveData<Response<List<Album>>> =
            liveData {
                val response2 = apiService.getAlbumsByUserId(8)
                emit(response2)
            }
        getAlbumsByUserIdLiveData.observe(this) {
            val albums = it.body()
            if(albums != null) {
                for (album in albums) {
//                    tv.append(album.toString())
//                    tv.append("\n")
                }
            }
        }

        val getAlbumsByIdLiveData: LiveData<Response<Album>> =
            liveData {
                val response3 = apiService.getAlbumsById(8)
                emit(response3)
            }

        getAlbumsByIdLiveData.observe(this) {
            val album = it.body()
            if(album != null) {
                tv.append(album.toString())
                tv.append("\n")
            }
        }
    }
}