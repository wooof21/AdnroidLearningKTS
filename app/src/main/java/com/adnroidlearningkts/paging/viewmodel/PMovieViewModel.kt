package com.adnroidlearningkts.paging.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.adnroidlearningkts.paging.model.repo.PMovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * 3. Set up a stream of PagingData - Access PagingData in ViewModel
 *
 * Need a stream of paged data from the PagingSource implementation.
 * Set up the data stream in the `ViewModel`.
 *
 * The `Pager` class provides methods that expose a reactive stream of PagingData objects from a PagingSource.
 * The Paging library supports using several stream types, including Flow, LiveData, and the Flowable and Observable types from RxJava.
 *
 * cachedIn in `viewModelScope` provides performance improvements.
 *
 * cachedIn() operator makes the data stream shareable and caches the loaded data with the provided CoroutineScope.
 */
@HiltViewModel
class PMovieViewModel @Inject constructor(private val repo: PMovieRepo): ViewModel()  {

    val movies = repo.getPagedMovies().cachedIn(viewModelScope)
}