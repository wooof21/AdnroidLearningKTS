package com.adnroidlearningkts.dependencyinjection.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adnroidlearningkts.dependencyinjection.retrofit.model.pojo.Post
import com.adnroidlearningkts.dependencyinjection.retrofit.model.repository.PostRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(): ViewModel() {

    val posts = MutableLiveData<List<Post>>()

    /**
     * Not recommend to always use lazy injection.
     *
     * When to use:
     *      * The creation of the dependency is expensive (e.g., involves significant computation,
     *          file I/O, or network calls during its construction).
     *      * The dependency might not be used at all during the lifetime of the object that injects it.
     *      * Need to break a circular dependency where two classes depend on each other.
     *          Using lazy injection for one of the dependencies can break the cycle by deferring the creation.
     *
     * Why not always use lazy injection?
     *      * Readability: Standard injection is more straightforward and easier to read.
     *          Lazy injection adds a layer of indirection with the Lazy wrapper and the need to call .get().
     *      * Unnecessary Overhead: If a dependency is always needed and is cheap to create,
     *          using lazy injection adds a small, unnecessary overhead of creating the Lazy object and the initial .get() call.
     *      * Potential for Late Initialization Errors: While lazy injection prevents UninitializedPropertyAccessException
     *          in the sense that the Lazy object itself is always initialized,
     *          could still encounter issues if forget to call .get() before trying to use the actual dependency.
     */
    @Inject //lazy inject
    lateinit var repoProvider: dagger.Lazy<PostRepo>
    private val repo: PostRepo by lazy {
        repoProvider.get()
    }

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.getPosts()
            if(response.isSuccessful) {
                posts.postValue(response.body())
            } else {
                posts.postValue(emptyList())
            }
        }
    }
}