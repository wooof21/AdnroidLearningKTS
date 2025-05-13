package com.adnroidlearningkts.paging.model.repo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.adnroidlearningkts.paging.model.apiinterface.PMovieInterface
import com.adnroidlearningkts.paging.model.pagingdatasource.MoviePagingDataSource
import com.adnroidlearningkts.paging.model.pojo.PMovie
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 3. Add Pager in Repository
 *
 * Pager is used to create PagingData which can be used by PagingDataAdapter to show paginated list.
 * Some important parameters:
 *
 *     PagingConfig — It is used to configure loading behaviour within a Pager.
 *          It is very important to use correct PagingConfig to improve memory usage, latency, etc.
 *     PageSize — It should be more than the number of visible items on the screens.
 *          For eg., if 4–5 items are visible on screen at one time then can have pageSize as 10–20.
 *
 *  * When create a Pager instance to set up the reactive stream, must provide the instance with
 *  * a PagingConfig configuration object and a function that tells Pager how to get an instance of the PagingSource implementation:
 */
@Singleton
class PMovieRepo @Inject constructor(private val api: PMovieInterface) {

    fun getPagedMovies(): LiveData<PagingData<PMovie>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 20, enablePlaceholders = false,
            initialLoadSize = 20, maxSize = 20 * 499),
        pagingSourceFactory = { MoviePagingDataSource(api) }
    ).liveData
}