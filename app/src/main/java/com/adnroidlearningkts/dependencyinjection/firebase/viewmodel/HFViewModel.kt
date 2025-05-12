package com.adnroidlearningkts.dependencyinjection.firebase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adnroidlearningkts.dependencyinjection.firebase.repository.DataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * @HiltViewModel: tells Hilt that this ViewModel is part of Dependency Graph,
 *  Hilt automatically generate the required code to make the ViewModel injectable
 *
 *  Without the annotation, Hilt cannot create or manage the ViewModel
 */
@HiltViewModel
class HFViewModel @Inject constructor(
//    private val repo: DataRepo): ViewModel(){
    ): ViewModel() {

    /**
     * To Lazy inject other type of classes:
     *      * dagger.Lazy<DataRepo>: This is a special type provided by Dagger (and used by Hilt)
     *          that wraps the dependency (DataRepo).
     *          - When inject a dagger.Lazy, Hilt provides an instance of this Lazy wrapper
     *          immediately when the containing class (Activity in this case) is created.
     *          However, the actual DataRepo instance is not created at this point.
     *
     *      * private val dataRepo: DataRepo by lazy { dataRepoProvider.get() }:
     *          This uses Kotlin's standard by lazy() delegate. This delegate ensures that the code
     *          inside the lambda { ... } is executed only on the first access of the `dataRepo` property.
     *          - Inside the lambda, `dataRepoProvider.get()` is called. This is the point where
     *              the actual DataRepo instance is created (if it hasn't been already) and returned by the dagger.Lazy provider.
     */
    @Inject lateinit var dataRepoProvider: dagger.Lazy<DataRepo>
    private val repo: DataRepo by lazy {
        dataRepoProvider.get()
    }

    fun getData(): LiveData<List<String>> = repo.fetchData("hilt")
}