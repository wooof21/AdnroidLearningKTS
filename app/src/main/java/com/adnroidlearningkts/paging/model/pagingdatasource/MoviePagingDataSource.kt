package com.adnroidlearningkts.paging.model.pagingdatasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.adnroidlearningkts.paging.model.apiinterface.PMovieInterface
import com.adnroidlearningkts.paging.model.pojo.PMovie
import javax.inject.Inject

/**
 * 1. Define the PagingSource
 *
 * PagingSource is where to define the source from where the data will be fetched.
 * It returns PagingSource<key, value> where key is the identifier for the data, like page no. for a particular page.
 * Generally provide the data source into the PagingSource constructor and override two methods
 */
class MoviePagingDataSource @Inject constructor(private val api: PMovieInterface): PagingSource<Int, PMovie>() {

    /**
     * It is used to get the anchorPosition which basically tells where currently present and
     * the logic that how value of page changes when scrolls.
     * For e.g., page should increase by one when scrolls down and decrease when scrolls up.
     */
    override fun getRefreshKey(state: PagingState<Int, PMovie>): Int? {
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    /**
     * It receives one parameter as LoadParams<key> which is used to define which page currently requires.
     * It returns LoadResult<key, value> where key again refers to the identifier and value as actual data.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PMovie> {
        return try {
            val nextPageNumber = params.key ?: 1

            // Call your API to fetch data
            val response = api.getPopularMoviesByPaging(nextPageNumber)
            LoadResult.Page(
                data = response.body()!!.movies,
                // Handle the first page
                prevKey = if (nextPageNumber == 1) null else (nextPageNumber - 1),
                // Determine the next key, if at totalPages -> null, else +1
                nextKey = if (nextPageNumber == response.body()!!.totalPages) null else (nextPageNumber + 1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}